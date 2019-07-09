package com.uhuibao.app;


import com.uhuibao.kafkautil.KafkaProperties;
import com.uhuibao.producer.Producer;

/**
 * 创建人: lirf
 * 创建时间:  2018/10/18 16:08
 * 功能介绍:
 */
public class KafkaProducerDemo {

    public static void main(String[] args) {
        boolean isAsync = args.length == 0 || !args[0].trim().equalsIgnoreCase("sync");
        Producer producerThread = new Producer(KafkaProperties.TOPIC, isAsync);
        producerThread.start();

    }
}
