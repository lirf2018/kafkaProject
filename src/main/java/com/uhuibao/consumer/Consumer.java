package com.uhuibao.consumer;

import com.uhuibao.kafkautil.KafkaProperties;
import com.uhuibao.kafkautil.ShutdownableThread;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * 创建人: lirf
 * 创建时间:  2018/10/18 10:57
 * 功能介绍:
 */
public class Consumer extends ShutdownableThread {
    private final KafkaConsumer<Integer, String> consumer;
    private final String topic;

    public Consumer(String topic) {
        super("KafkaConsumerExample", false);
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKA_SERVER_URL + ":" + KafkaProperties.KAFKA_SERVER_PORT);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "aa1");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        //1.创建消费者
        consumer = new KafkaConsumer<>(props);
        this.topic = topic;
    }

    public void doWork() {
        try {
            //2.订阅Topic
            //创建一个只包含单个元素的列表，Topic的名字叫作customerCountries
            consumer.subscribe(Collections.singletonList(this.topic));
            //支持正则表达式，订阅所有与test相关的Topic
            //consumer.subscribe("test.*");

            //3.轮询
            //消息轮询是消费者的核心API，通过一个简单的轮询向服务器请求数据，一旦消费者订阅了Topic，轮询就会处理所欲的细节，包括群组协调、partition再均衡、发送心跳
            //以及获取数据，开发者只要处理从partition返回的数据即可。
            while (true) {//消费者是一个长期运行的程序，通过持续轮询向Kafka请求数据。在其他线程中调用consumer.wakeup()可以退出循环
                //在100ms内等待Kafka的broker返回数据.超市参数指定poll在多久之后可以返回，不管有没有可用的数据都要返回
//                Thread.sleep(1000);
                ConsumerRecords<Integer, String> records = consumer.poll(100);
                for (ConsumerRecord<Integer, String> record : records) {
                    System.out.println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //退出应用程序前使用close方法关闭消费者，网络连接和socket也会随之关闭，并立即触发一次再均衡
            consumer.close();
        }
    }

    @Override
    public void execute() {
        doWork();
    }
}
