package com.g3.order.service.impl;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

public class KafkaProducerApp {

  public static void produce(String key, String value) {
    final Properties props = KafkaService.properties();
    final String topic = System.getenv("KAFKA_TOPIC");
    final Producer<String, String> producer = new KafkaProducer<>(props);
    final KafkaService producerService = new KafkaService(producer, topic);

    try {
      producerService.sendMessage(key, value);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
