package com.uhuibao.file;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建人: lirf
 * 创建时间:  2018/12/11 9:29
 * 功能介绍: 读取日志并写到
 */
public class LogFile {

    public static void main(String[] args) {
        System.out.println("----------------main开始---------------");
        String rootPath = "E:\\big\\web_log";
        List<String> list = new ArrayList<>();
        try {
            Map<String, String> mapDate = initCache(rootPath);

            File fileAll = new File(rootPath + "\\temLog");
            String[] fileList = fileAll.list();
            for (int i = 0; i < fileList.length; i++) {
                String fileName = fileList[i];
                File file = new File(rootPath + "\\temLog\\" + fileName);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String tempString = null;
                while ((tempString = reader.readLine()) != null) {
//                    System.out.println(tempString);
                    if (StringUtils.isNotEmpty(tempString)) {
                        String str[] = tempString.split(" ");
                        if (str[0].length() == 10) {
//                            System.out.println(str[0]);
                            mapDate.put(str[0], str[0]);
                        }
                    }
                    list.add(tempString);
                }
                reader.close();
            }

            for (Map.Entry<String, String> m : mapDate.entrySet()) {
                System.out.println(m.getKey());
            }

//            wirtLog(list, "2018-12-11");
            System.out.println("----------------main结束---------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 已经写过的日志<date,date>
     *
     * @return
     */
    public static Map<String, String> initCache(String rootPath) {
        Map<String, String> map = new HashMap<>();
        try {
            Map<String, String> hasWrite = new HashMap<>();//已经写过的日志<date,date>
            File file = new File(rootPath + "\\cache.log");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                map.put(tempString, tempString);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    private static void wirtLog(List<String> list, String date) {
        System.out.println("----------------wirtLog开始---------------" + date);
        try {
            String fileName = "catalina.out";
            String path = "E:\\big\\log\\";
            File filePath = new File(path);
            if (!filePath.exists()) {
//                System.out.println("创建目录");
                filePath.mkdirs();
            }
            File file = new File(path + fileName);
            System.out.println(file);
            if (!file.exists()) {
//                System.out.println("创建目录文件");
                file.createNewFile();
            }
            BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i) + System.getProperty("line.separator");
//                writer.write(str.getBytes("GBK"));
                writer.write(str.getBytes("UTF-8"));
                writer.flush();
                System.out.println("line--->" + i);
                Thread.sleep(1000);
            }
            writer.close();
            System.out.println("----------------wirtLog结束---------------" + date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
