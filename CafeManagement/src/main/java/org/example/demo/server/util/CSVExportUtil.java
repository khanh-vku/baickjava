package org.example.demo.server.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class CSVExportUtil {

    public static void export(List<String> lines, String filePath) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

            for (String line : lines) {

                if (line == null || line.trim().isEmpty()) continue;

                bw.write(line);
                bw.newLine();
            }

            bw.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}