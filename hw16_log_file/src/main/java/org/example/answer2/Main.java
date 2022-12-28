package org.example.answer2;

import org.example.answer2.entity.File;
import org.example.answer2.service.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final FileService fileService = new FileService();

    public static void main(String[] args) {
        Path path = Paths.get("H:\\maktab83\\hw16\\hw16_log_file\\src\\main\\java\\org\\example\\answer2\\File.txt");
        if (Files.exists(path)) {
            try {
                List<String> list = Files.readAllLines(path);
                for (String s : list) {
                    if (s != null && !s.equals("") && s.matches("^[^,]+,[^,]+,[^,]+,[^,]+,[^,]*$")) {
                        System.out.println(s);
                        List<String> str = Arrays.asList(s.split(","));
                        File file = new File(str.get(0), str.get(1), Timestamp.valueOf(str.get(2)).getTime(), Double.parseDouble(str.get(3)), str.get(4));
                        fileService.create(file);
                    } else {
                        System.out.println("File not matching with this pattern");
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("File does not exist...");
        }
    }
}
