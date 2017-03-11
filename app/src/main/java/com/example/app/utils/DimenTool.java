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

        File file = new File("./app/src/main/res/values/dimens.xml");
        BufferedReader reader = null;
        StringBuilder sw200 = new StringBuilder();
        StringBuilder sw320 = new StringBuilder();
        StringBuilder sw360 = new StringBuilder();
        StringBuilder sw380 = new StringBuilder();
        StringBuilder sw410 = new StringBuilder();
        StringBuilder sw480 = new StringBuilder();
        StringBuilder sw600 = new StringBuilder();
        StringBuilder sw720 = new StringBuilder();
        StringBuilder sw760 = new StringBuilder();
        StringBuilder sw800 = new StringBuilder();
        StringBuilder sw900 = new StringBuilder();
        StringBuilder sw1024 = new StringBuilder();
        StringBuilder sw1280 = new StringBuilder();

        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            int line = 1;

            while ((tempString = reader.readLine()) != null) {

                if (tempString.contains("</dimen>")) {
                    //tempString = tempString.replaceAll(" ", "");
                    String start = tempString.substring(0, tempString.indexOf(">") + 1);
                    String end = tempString.substring(tempString.lastIndexOf("<") - 2);
                    float num = Float.valueOf(tempString.substring(tempString.indexOf(">") + 1, tempString.indexOf("</dimen>") - 2));

                    //根据UI画布大小比例进行换算，UI图相对分辨率为1334x750

                    sw200.append(start).append((int) Math.round(num * 0.53334)).append(end).append("\n");
                    sw320.append(start).append((int) Math.round(num * 0.85333)).append(end).append("\n");
                    sw360.append(start).append((int) Math.round(num * 0.96)).append(end).append("\n");

                    sw380.append(start).append((int) Math.round(num * 1.01333)).append(end).append("\n");
                    sw410.append(start).append((int) Math.round(num * 1.09333)).append(end).append("\n");

                    sw480.append(start).append((int) Math.round(num * 1.28)).append(end).append("\n");

                    sw600.append(start).append((int) Math.round(num * 1.6)).append(end).append("\n");
                    sw720.append(start).append((int) Math.round(num * 1.92)).append(end).append("\n");

                    sw760.append(start).append((int) Math.round(num * 2.02667)).append(end).append("\n");
                    sw800.append(start).append((int) Math.round(num * 2.13333)).append(end).append("\n");
                    sw900.append(start).append((int) Math.round(num * 2.4)).append(end).append("\n");

                    sw1024.append(start).append((int) Math.round(num * 2.73066)).append(end).append("\n");
                    sw1280.append(start).append((int) Math.round(num * 3.41333)).append(end).append("\n");


                } else{
                    sw200.append(tempString).append("\n");
                    sw320.append(tempString).append("\n");
                    sw360.append(tempString).append("\n");
                    sw380.append(tempString).append("\n");
                    sw410.append(tempString).append("\n");
                    sw480.append(tempString).append("\n");
                    sw600.append(tempString).append("\n");
                    sw720.append(tempString).append("\n");

                    sw760.append(tempString).append("\n");
                    sw800.append(tempString).append("\n");
                    sw900.append(tempString).append("\n");

                    sw1024.append(tempString).append("\n");
                    sw1280.append(tempString).append("\n");
                }
                line++;
            }
            reader.close();
            System.out.println("<!--  sw480 -->");
            System.out.println(sw480);
            System.out.println("<!--  sw600 -->");
            System.out.println(sw600);

            System.out.println("<!--  sw720 -->");
            System.out.println(sw720);
            System.out.println("<!--  sw1024 -->");
            System.out.println(sw1024);

            String sw200file = "./app/src/main/res/values-sw200dp/dimens.xml";
            String sw320file = "./app/src/main/res/values-sw320dp/dimens.xml";
            String sw360file = "./app/src/main/res/values-sw360dp/dimens.xml";
            String sw380file = "./app/src/main/res/values-sw380dp/dimens.xml";
            String sw410file = "./app/src/main/res/values-sw410dp/dimens.xml";
            String sw480file = "./app/src/main/res/values-sw480dp/dimens.xml";
            String sw600file = "./app/src/main/res/values-sw600dp/dimens.xml";
            String sw720file = "./app/src/main/res/values-sw720dp/dimens.xml";

            String sw760file = "./app/src/main/res/values-sw760dp/dimens.xml";
            String sw800file = "./app/src/main/res/values-sw800dp/dimens.xml";
            String sw900file = "./app/src/main/res/values-sw900dp/dimens.xml";

            String sw1024file = "./app/src/main/res/values-sw1024dp/dimens.xml";
            String sw1280file = "./app/src/main/res/values-sw1280dp/dimens.xml";

            writeFile(sw200file, sw200.toString());
            writeFile(sw320file, sw320.toString());
            writeFile(sw360file, sw360.toString());
            writeFile(sw380file, sw380.toString());
            writeFile(sw410file, sw410.toString());
            writeFile(sw480file, sw480.toString());
            writeFile(sw600file, sw600.toString());
            writeFile(sw720file, sw720.toString());

            writeFile(sw760file, sw760.toString());
            writeFile(sw800file, sw800.toString());
            writeFile(sw900file, sw900.toString());

            writeFile(sw1024file, sw1024.toString());
            writeFile(sw1280file, sw1280.toString());
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