package org.example.demo.server.service;

import org.example.demo.model.Employee;
import org.example.demo.server.dao.EmployeeDAO;

import java.util.List;

public class EmployeeService {

    private final EmployeeDAO dao = new EmployeeDAO();

    public List<Employee> getAllEmployees() {
        return dao.findAll();
    }

    public Employee getEmployeeById(int employeeId) {
        return dao.findById(employeeId);
    }

    public boolean addEmployee(Employee employee) {
        return dao.add(employee);
    }

    public boolean updateEmployee(Employee employee) {
        return dao.update(employee);
    }

    public boolean deleteEmployee(int employeeId) {
        return dao.delete(employeeId);
    }

    public List<Employee> searchEmployees(String keyword) {
        return dao.search(keyword);
    }
}