package org.example.demo.server.dao;

import org.example.demo.server.config.DBConnection;
import org.example.demo.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public List<Employee> findAll() {

        List<Employee> list = new ArrayList<>();

        String sql = "SELECT * FROM Employees";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Employee emp = new Employee();

                emp.setEmployeeId(rs.getInt("EmployeeID"));
                emp.setFullName(rs.getString("FullName"));
                emp.setGender(rs.getString("Gender"));
                emp.setPhone(rs.getString("Phone"));
                emp.setAddress(rs.getString("Address"));
                emp.setPosition(rs.getString("Position"));
                emp.setSalary(rs.getDouble("Salary"));

                list.add(emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }



    public boolean add(Employee emp) {

        String sql =
                "INSERT INTO Employees(FullName,Gender,Phone,Address,Position,Salary) VALUES(?,?,?,?,?,?)";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, emp.getFullName());
            ps.setString(2, emp.getGender());
            ps.setString(3, emp.getPhone());
            ps.setString(4, emp.getAddress());
            ps.setString(5, emp.getPosition());
            ps.setDouble(6, emp.getSalary());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Employee emp) {

        String sql =
                "UPDATE Employees SET FullName=?,Gender=?,Phone=?,Address=?,Position=?,Salary=? WHERE EmployeeID=?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, emp.getFullName());
            ps.setString(2, emp.getGender());
            ps.setString(3, emp.getPhone());
            ps.setString(4, emp.getAddress());
            ps.setString(5, emp.getPosition());
            ps.setDouble(6, emp.getSalary());
            ps.setInt(7, emp.getEmployeeId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int employeeId) {

        String sql =
                "DELETE FROM Employees WHERE EmployeeID=?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, employeeId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Employee findById(int employeeId) {

        String sql =
                "SELECT * FROM Employees WHERE EmployeeID=?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, employeeId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Employee emp = new Employee();

                    emp.setEmployeeId(rs.getInt("EmployeeID"));
                    emp.setFullName(rs.getString("FullName"));
                    emp.setGender(rs.getString("Gender"));
                    emp.setPhone(rs.getString("Phone"));
                    emp.setAddress(rs.getString("Address"));
                    emp.setPosition(rs.getString("Position"));
                    emp.setSalary(rs.getDouble("Salary"));

                    return emp;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Employee> search(String keyword) {

        List<Employee> list = new ArrayList<>();

        String sql =
                "SELECT * FROM Employees " +
                        "WHERE FullName LIKE ? " +
                        "OR Phone LIKE ? " +
                        "OR Position LIKE ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            String searchValue = "%" + keyword + "%";

            ps.setString(1, searchValue);
            ps.setString(2, searchValue);
            ps.setString(3, searchValue);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Employee emp = new Employee();

                    emp.setEmployeeId(rs.getInt("EmployeeID"));
                    emp.setFullName(rs.getString("FullName"));
                    emp.setGender(rs.getString("Gender"));
                    emp.setPhone(rs.getString("Phone"));
                    emp.setAddress(rs.getString("Address"));
                    emp.setPosition(rs.getString("Position"));
                    emp.setSalary(rs.getDouble("Salary"));

                    list.add(emp);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}