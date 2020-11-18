package org.ejb.sample.servlet.helper;

import java.io.FileWriter;

public class FileUtility {

    void generateReport(String content) throws Exception {
        FileWriter fileWriter = new FileWriter("~/Downloads/report.csv");
        fileWriter.write(content);
        fileWriter.close();
    }
}
