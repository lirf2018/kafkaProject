package com.uhuibao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

/**
 * @功能名称
 * @作者 lirongfan
 * @时间 2016年10月20日 下午6:06:04
 */
public class Consumer1 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String topic = "topic-log";
        myConsumer(topic);
    }

    /**
     * 消费者
     */
    public static void myConsumer(String topic) {
        Properties props = new Properties();
        props.put("zookeeper.connect", "172.16.9.128:2181");
        props.put("group.id", "aa");
        props.put("enable.auto.commit", "true");//自动commit
        props.put("auto.commit.interval.ms", "1000"); //定时commit的周期
        props.put("session.timeout.ms", "30000");//consumer活性超时时间
        props.put("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        ConsumerConnector consumer = kafka.consumer.Consumer.createJavaConsumerConnector(new ConsumerConfig(props));
        System.out.println("--正在查询--");
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, 1); // 一次从主题中获取一个数据
        Map<String, List<KafkaStream<byte[], byte[]>>> messageStreams = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = messageStreams.get(topic).get(0);// 获取每次接收到的这个数据  
        ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
        while (iterator.hasNext()) {
            String message = new String(iterator.next().message());
            System.out.println("接收到: " + message);
            //处理自己业务
            doOwnsBusiness();
        }
    }

    private static void doOwnsBusiness() {
        System.out.println("---处理自己的业务");
    }
}
