package com.example.app.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by cdy on 2016/2/3.
 */
public class DimenTool {

    public static void gen() {

        File file = new File("D:/myworkspace/FirstDemo/app/src/main/res/values/dimens.xml");

        BufferedReader reader = null;
        StringBuilder sw240 = new StringBuilder();
        StringBuilder sw480 = new StringBuilder();
        StringBuilder sw600 = new StringBuilder();
        StringBuilder sw720 = new StringBuilder();
        StringBuilder sw800 = new StringBuilder();
        StringBuilder w820 = new StringBuilder();

        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            int line = 1;

            while ((tempString = reader.readLine()) != null) {
                if (tempString.contains("</dimen>")) {
                    //tempString = tempString.replaceAll(" ", "");
                    String start = tempString.substring(0, tempString.indexOf(">") + 1);
                    String end = tempString.substring(tempString.lastIndexOf("<") - 2);
                    Double num = Double.parseDouble
                            (tempString.substring(tempString.indexOf(">") + 1,
                                    tempString.indexOf("</dimen>") - 2));

                    sw240.append(start).append(num * 0.75).append(end).append("\r\n");

                    sw480.append(start).append(num * 1.5).append(end).append("\r\n");

                    sw600.append(start).append(num * 1.87).append(end).append("\r\n");

                    sw720.append(start).append(num * 2.25).append(end).append("\r\n");

                    sw800.append(start).append(num * 2.5).append(end).append("\r\n");

                    w820.append(start).append(num * 2.56).append(end).append("\r\n");
                } else {
                    sw240.append(tempString).append("");
                    sw480.append(tempString).append("");
                    sw600.append(tempString).append("");
                    sw720.append(tempString).append("");
                    sw800.append(tempString).append("");
                    w820.append(tempString).append("");
                }
                line++;
            }
            reader.close();
            System.out.println("<!--  sw240 -->");

            System.out.println(sw240);

            System.out.println("<!--  sw480 -->");

            System.out.println(sw480);

            System.out.println("<!--  sw600 -->");

            System.out.println(sw600);

            System.out.println("<!--  sw720 -->");

            System.out.println(sw720);

            System.out.println("<!--  sw800 -->");

            System.out.println(sw800);

            String sw240file = "D:/myworkspace/FirstDemo/app/src/main/res/values-sw240dp-land/dimens.xml";
            String sw480file = "D:/myworkspace/FirstDemo/app/src/main/res/values-sw480dp-land/dimens.xml";
            String sw600file = "D:/myworkspace/FirstDemo/app/src/main/res/values-sw600dp-land/dimens.xml";
            String sw720file = "D:/myworkspace/FirstDemo/app/src/main/res/values-sw720dp-land/dimens.xml";
            String sw800file = "D:/myworkspace/FirstDemo/app/src/main/res/values-sw800dp-land/dimens.xml";
            String w820file = "D:/myworkspace/FirstDemo/app/src/main/res/values-w820dp/dimens.xml";

            writeFile(sw240file, sw240.toString());
            writeFile(sw480file, sw480.toString());
            writeFile(sw600file, sw600.toString());
            writeFile(sw720file, sw720.toString());
            writeFile(sw800file, sw800.toString());
            writeFile(w820file, w820.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void writeFile(String file, String text) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
    }

    public static void main(String[] args) {
        gen();
    }
}