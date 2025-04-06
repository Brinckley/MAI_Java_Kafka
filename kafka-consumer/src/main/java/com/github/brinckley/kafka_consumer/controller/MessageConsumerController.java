package com.github.brinckley.kafka_consumer.controller;

import com.github.brinckley.kafka_consumer.business.KafkaConsumerBusiness;
import com.github.brinckley.kafka_consumer.model.KafkaMessage;
import com.github.brinckley.kafka_consumer.model.KafkaResponseMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for user to communicate with kafka
 */
@Slf4j
@RestController
@RequestMapping("kafka")
@RequiredArgsConstructor
public class MessageConsumerController {
    private final KafkaConsumerBusiness kafkaBusiness;

    @GetMapping("/consume")
    public ResponseEntity<KafkaResponseMessageDto> fetchMessage() {
        KafkaMessage kafkaMessage = kafkaBusiness.consumeLatestMessage();
        KafkaResponseMessageDto kafkaResponseMessageDto = KafkaResponseMessageDto.from(kafkaMessage);

        return ResponseEntity.ok()
                .body(kafkaResponseMessageDto);
    }
}
