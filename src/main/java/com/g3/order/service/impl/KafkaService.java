package com.g3.order.service.impl;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.google.gson.Gson;

public class KafkaService {

  private final Producer<String, String> producer;
  final String outTopic;

  public KafkaService(final Producer<String, String> producer, final String topic) {
    this.producer = producer;
    this.outTopic = topic;
  }

  public static String messageConstructor(Object o) {
    return new Gson().toJson(o);
  }

  public void sendMessage(String key, String value) throws InterruptedException, ExecutionException {
    ProducerRecord<String, String> record = new ProducerRecord<String, String>(outTopic, key,
        value);

    Callback callback = (data, ex) -> {
      if (ex != null) {
        ex.printStackTrace();
        return;
      }
      System.out.println("Mensagem enviada para: " + data.topic() + " | partition: " + data.partition() + " | offset: "
          + data.offset());
    };

    producer.send(record, callback).get();
    producer.close();
  }

  public static Properties properties() {
    Properties properties = new Properties();
    properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, System.getenv("KAFKA_HOST"));
    properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    return properties;
  }
}
