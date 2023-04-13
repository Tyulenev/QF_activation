package com.QSystems.quick_flow_registration.Additional;

import org.unix4j.Unix4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class OSread {
    public static String getOSName() {
        return System.getProperty("os.name");
    }

    public static boolean isWindows() {
        if (getOSName().startsWith("Windows")) return true;
        else return false;
    }

    public static boolean isLinux() {
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

    public static String getMac() {
        String result = null;

        if (isWindows()) {
            result = "winMac123";
        } else if (isLinux()) {
            String command
                    = "ip address show";
//                    = "ip address show | grep ether";
//                    = "ip address show | grep ether | gawk '{print $2}'";
            String macAddr = "";
            try {
                Process SerialNumberProcess
                        = Runtime.getRuntime().exec(command);
                InputStreamReader ISR = new InputStreamReader(
                        SerialNumberProcess.getInputStream());
                BufferedReader br = new BufferedReader(ISR);
//                macAddr = br.readLine().trim();
                Thread.sleep(100);
                while (br.ready()) {
                    String readedLine = br.readLine();
                    if (readedLine.contains("ether")) {
                        String[] arrStr = readedLine.split("ether");
                        macAddr +=  arrStr[1].trim().substring(0,17) + " ";
                    }
                }
                SerialNumberProcess.waitFor();
                br.close();
            }
            catch (Exception e) {
                e.printStackTrace();
//                macAddr = null;
                macAddr = "Mac address was not considered";
            }
            result = macAddr;
        }
        return result;
    }

    public static String getDataForRegistration() {
        return getMatherBoardNumber() + getMac();
//        return getMac();
    }
}
