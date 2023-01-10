package com.QSystems.quick_flow_registration.Additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OSread {
    public static String getOSName() {
        return System.getProperty("os.name");
    }

    private static boolean isWindows() {
        if (getOSName().startsWith("Windows")) return true;
        else return false;
    }

    private static boolean isLinux() {
//        if (getOSName().startsWith("Linux") || getOSName().startsWith("Unix")) return true;
//        else return false;
        String OS = getOSName();
        return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
    }


    public static String getMatherBoardNumber() {
        String result = null;
        String motherBoardNumber = null;
        if (isWindows()) {
            try
            {
                Process p = Runtime.getRuntime().exec("wmic baseboard get serialnumber");
                BufferedReader input
                        = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while ((line = input.readLine()) != null)
                {
                    motherBoardNumber += line;
                }
                if (motherBoardNumber.equalsIgnoreCase(" ")) {
                    System.out.println("Result is empty");
                }
                input.close();
            } catch (IOException ex)
            {  ex.printStackTrace(); }
            String[] testStrArr = motherBoardNumber.split(" ");
            for (String str:testStrArr) {
                if (str.startsWith("/"))
                    result = str;
            }
        } else if (isLinux()) {
            String command
                    = "sudo dmidecode -s baseboard-serial-number";
            String serialNumber = null;
            try {
                Process SerialNumberProcess
                        = Runtime.getRuntime().exec(command);
                InputStreamReader ISR = new InputStreamReader(
                        SerialNumberProcess.getInputStream());
                BufferedReader br = new BufferedReader(ISR);
                serialNumber = br.readLine().trim();
                SerialNumberProcess.waitFor();
                br.close();
                result = serialNumber;
            }
            catch (Exception e) {
                e.printStackTrace();
                serialNumber = null;
            }
        } else System.out.println("Unknown operating system");
        return result;
    }
}
