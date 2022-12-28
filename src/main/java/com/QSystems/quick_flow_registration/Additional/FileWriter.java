package com.QSystems.quick_flow_registration.Additional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class FileWriter {
    public static void writeFile(String writeStr, String newFileName) throws IOException {
//        String strToWriteToFile = OSread.getMatherBoardNumber();
        Path mainPath = Paths.get("results");
        if (!Files.exists(mainPath))
        {
            Files.createDirectory(mainPath);
        }
        Date date = new Date();
//        String newFileName_old = date.getTime() + "_QFlicence.txt";
//        String newFileName = "_QFLicence.txt";
        Path newfilePath = Paths.get(mainPath + "\\" + newFileName);
        if (!Files.exists(newfilePath))
            Files.createFile(newfilePath);
        else {
            Files.delete(newfilePath);
            Files.createFile(newfilePath);
        }
        byte[] outData = writeStr.getBytes();
        Files.write(newfilePath, outData);
    }
}
