package com.github.brinckley.kafka_producer.controller;

import com.github.brinckley.kafka_producer.business.KafkaBusiness;
import com.github.brinckley.kafka_producer.model.KafkaMessage;
import com.github.brinckley.kafka_producer.model.dto.MessageRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("kafka")
@RequiredArgsConstructor
public class MessageController {
    private final KafkaBusiness kafkaBusiness;

    @PostMapping("/publish")
    public void publishMessage(@Validated MessageRequestDto messageRequestDto) {
        log.info("Message received to the endpoint /publish with content {}", messageRequestDto);

        KafkaMessage kafkaMessage = KafkaMessage.builder().message(messageRequestDto.getMessage()).build();
        kafkaBusiness.handleMessage(kafkaMessage);
    }
}
