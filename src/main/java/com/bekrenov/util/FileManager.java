package com.bekrenov.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;

public class FileManager {

    public static String copyFile(String filePath, String toDir) throws IOException {
        String tempPath = (toDir + (new File(filePath)).getName());
        //System.out.println(new File(tempPath).getParent());
        Path fromPath = Paths.get(filePath);
        Path toPath = Paths.get(tempPath);
        Files.copy(fromPath, toPath);
        String newPath = (new File(tempPath)).getParent() + "\\" + createFileNameBasedOnCurrentTime() + "." +
                com.google.common.io.Files.getFileExtension(tempPath);
        renameFile(tempPath, newPath);
        return newPath;
    }

    public static boolean renameFile(String oldPath, String newPath) throws IOException {
        File oldFile = new File(oldPath);
        File newFile = new File(newPath);
        if(newFile.exists()){
            throw new IOException("file exists");
        }
        return oldFile.renameTo(newFile);
    }

    public static String createFileNameBasedOnCurrentTime(){
        return LocalDate.now().getYear()
                + LocalDate.now().getMonthValue()
                + LocalDate.now().getDayOfMonth() + "-"
                + LocalTime.now().getHour()
                + LocalTime.now().getMinute()
                + LocalTime.now().getSecond();
    }
}
