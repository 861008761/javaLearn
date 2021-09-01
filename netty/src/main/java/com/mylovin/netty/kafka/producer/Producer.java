package com.mylovin.netty.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 参考文档《Java操作Kafka》
 * 地址：https://www.cnblogs.com/duanjt/p/10132116.html
 * <p>
 * 官方文档地址：http://kafka.apachecn.org/documentation.html#producerapi
 */
public class Producer {
    public static String topic = "tuzisir";//定义主题
    public static KafkaProducer<String, String> kafkaProducer;

    static {
        init();
    }

    private static void init() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");//kafka地址，多个地址用逗号分割
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaProducer = new KafkaProducer<>(props);
    }

    public void produce(String msg) {
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, msg);
        kafkaProducer.send(record);
        System.out.println("消息发送成功:" + msg);
    }

    public static void main(String[] args) throws InterruptedException {

    }
}
