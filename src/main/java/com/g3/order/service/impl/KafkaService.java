package com.g3.order.service.impl;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.google.gson.Gson;

public class KafkaService {

  public static String messageConstructor(Object o){
    return new Gson().toJson(o);
  }

  public static void sendMessage(String key, String value) throws InterruptedException, ExecutionException {
    KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties());
    ProducerRecord<String, String> record = new ProducerRecord<String, String>(System.getenv("KAFKA_TOPIC"), key,
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

  private static Properties properties() {
    Properties properties = new Properties();
    properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, System.getenv("KAFKA_HOST"));
    properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    return properties;
  }
}
