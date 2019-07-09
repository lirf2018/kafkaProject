package com.uhuibao;

import com.uhuibao.util.HttpsRequest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建人: lirf
 * 创建时间:  2018/12/25 15:03
 * 功能介绍:  日志 配合 flume收集--->flume集中分发--->kafka和hdfs
 */
public class TimesLog {

    public static void main(String[] args) {
        try {

            List<String> logList = new ArrayList<>();

            File file = new File("C:\\Users\\usersLi\\Desktop\\logchain\\catalina.2018-12-13.out");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                logList.add(tempString);
            }
            reader.close();

            for (int i = 0; i < logList.size(); i++) {
                String log = logList.get(i);
                System.out.println(log);
                Thread.sleep(1000);
                HttpsRequest.httpsPost1("http://172.16.9.129:28081/rs_store_inf/testLog", log);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
