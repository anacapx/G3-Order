package com.g3.order;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KeyValue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.g3.order.controller.dto.OrderDTO;
import com.g3.order.model.Order;
import com.g3.order.service.impl.KafkaService;
import com.g3.order.model.User;

@SpringBootTest
class OrderApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testProducerKafka() throws InterruptedException, ExecutionException {
		final StringSerializer stringSerializer = new StringSerializer();
		final MockProducer<String, String> mockProducer = new MockProducer<>(true, stringSerializer, stringSerializer);
		final String topic = System.getenv("KAFKA_TEST_TOPIC");
		final KafkaService producer = new KafkaService(mockProducer, topic);

		User user = new User("Username", "123", "recipient@email.com");
		Order o = new Order(Long.valueOf(1), 178.9, "Uma lista de produtos");

		producer.sendMessage("1", KafkaService.messageConstructor(new OrderDTO(o, user)));

		SimpleDateFormat formater = new SimpleDateFormat("MMM d, yyyy, h:mm:ss a");
		String valueExpected = "{\"id\":1,\"user\":{\"name\":\"Username\",\"phone\":\"123\",\"email\":\"recipient@email.com\"},\"value\":178.9,\"products\":\"Uma lista de produtos\",\"date\":"
				+ "\"" + formater.format(new Timestamp(System.currentTimeMillis())) + "\",\"status\":\"PENDING\"}";
		KeyValue<String, String> kvExpected = new KeyValue<String, String>("1", valueExpected);

		final KeyValue<String, String> kvActual = this.toKeyValue(mockProducer.history().get(0));

		assertEquals(kvExpected, kvActual);
	}

	private KeyValue<String, String> toKeyValue(final ProducerRecord<String, String> producerRecord) {
		return KeyValue.pair(producerRecord.key(), producerRecord.value());
	}

}
