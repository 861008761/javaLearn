package com.mylovin.rmq.task;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@Component
public class ScheduleTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTask.class);
    @Value("${rocketmq.consumer.topics}")
    private String topics;
    @Resource
    private DefaultMQProducer producer;

    @Scheduled(cron = "0/5 * * * * ? ") // 间隔5秒执行
    public void task() {
        String topic = "";
        String tag = "";
        String[] topicTagsArr = topics.split(";");
        for (String topicTags : topicTagsArr) {
            String[] topicTag = topicTags.split("~");
            topic = topicTag[0];
            tag = topicTag[1];
        }

        Message msg = new Message(topic, tag, "hello, world...".getBytes(StandardCharsets.UTF_8));
        try {
            SendResult result = producer.send(msg);
            if (result.getSendStatus() == SendStatus.SEND_OK) {
                LOGGER.info("消息发送成功...");
            }
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
