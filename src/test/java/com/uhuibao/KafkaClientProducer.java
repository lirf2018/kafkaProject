package com.uhuibao;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import javax.swing.plaf.SliderUI;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;

/**
 * @功能名称
 * @作者 lirongfan
 * @时间 2016年10月24日 下午5:30:44
 */
public class KafkaClientProducer {

    private static Logger log = Logger.getLogger(KafkaClientProducer.class);

    /**
     * @param hosts      kafka集群地址
     * @param clientName 生产者名称
     * @param msg        消息json
     * @param topic      主题名称
     * @return
     */
    public static boolean sendMsgToKafka(String topic, String hosts, String clientName, String msg) {
        try {
            log.info("topic=" + topic + ";hosts=" + hosts + ";clientName=" + clientName + ";msg=" + msg);
            Properties props = new Properties();
            props.put("bootstrap.servers", hosts);
            // props.put("bootstrap.servers", "h4:9092");
            props.put("acks", "all");// ack方式，all，会等所有的commit最慢的方式
            props.put("retries", 0);// 失败是否重试，设置会有可能产生重复数据
            props.put("batch.size", 16384);// 对于每个partition的batch buffer大小
            props.put("linger.ms", 1);// 等多久，如果buffer没满，比如设为1，即消息发送会多1ms的延迟，如果buffer没满
            props.put("client.id", clientName);
            props.put("buffer.memory", 33554432); // 整个producer可以用于buffer的内存大小
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            KafkaProducer<Integer, String> producer = new KafkaProducer<>(props);
            producer.send(new ProducerRecord<Integer, String>(topic, msg)).get();
            return true;
        } catch (InterruptedException e) {
            log.info(e.getStackTrace());
            e.printStackTrace();
        } catch (ExecutionException e) {
            log.info(e.getStackTrace());
            e.printStackTrace();
        }

        return false;
    }

    ;

    public static void main(String[] args) {
        String topic = "test";
//		String topic = "notice";
//		String hosts = "192.168.2.99:9092";
//		String hosts = "192.168.3.37:9092";
        String hosts = "14.152.106.109:9092";
        String clientName = "order_sys";
        for (int i = 0; i < 1; i++) {
            String msg = "订单系统测试消息" + System.currentTimeMillis();
            KafkaClientProducer.sendMsgToKafka(topic, hosts, clientName, msg);
        }
    }
}
