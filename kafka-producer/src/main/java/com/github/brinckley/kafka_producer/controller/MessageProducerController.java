package com.github.brinckley.kafka_producer.controller;

import com.github.brinckley.kafka_producer.business.KafkaProducerBusiness;
import com.github.brinckley.kafka_producer.model.KafkaMessage;
import com.github.brinckley.kafka_producer.model.dto.MessageRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller to handle incoming requests for message production
 */
@Slf4j
@RestController
@RequestMapping("kafka")
@RequiredArgsConstructor
public class MessageProducerController {
    private final KafkaProducerBusiness kafkaBusiness;

    @PostMapping("/produce")
    public void produceMessage(@Validated @RequestBody MessageRequestDto messageRequestDto) {
        log.info("Message received to the endpoint /produce with content {}", messageRequestDto);

        KafkaMessage kafkaMessage = KafkaMessage.builder().message(messageRequestDto.getMessage()).build();
        kafkaBusiness.handleMessage(kafkaMessage);
    }
}
