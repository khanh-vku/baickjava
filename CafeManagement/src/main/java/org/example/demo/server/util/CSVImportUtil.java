package org.example.demo.server.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVImportUtil {

    public static List<String[]> importFile(String filePath) {

        List<String[]> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");

                list.add(data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}