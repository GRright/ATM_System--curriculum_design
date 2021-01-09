//package com.example.bank.rmService;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
//@Service
//@Slf4j
//public class MessageService {
//    @Resource
//    private RabbitTemplate rabbitTemplate;
//
//    public void Sender(){
//
//
////        rabbitTemplate.convertAndSend(
////                QueueEnum.QUEUE_ORDER_CANCEL.getExchange(),
////                QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey(),
////                "哈哈哈",
////                message -> {
////                    message.getMessageProperties().setDelay(30000);
////                    return message;
////                }
////                );
//
//    }
//}
