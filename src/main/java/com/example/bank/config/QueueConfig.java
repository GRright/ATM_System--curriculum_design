//package com.example.bank.config;
//
//import com.example.bank.constants.QueueEnum;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.CustomExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class QueueConfig {
//    @Bean
//    CustomExchange customExchange() {
//        Map<String, Object> args = new HashMap<>();
//        args.put("x-delayed-type", "direct");
//        //属性参数 交换机名称 交换机类型 是否持久化 是否自动删除 配置参数
//        return new CustomExchange(QueueEnum.QUEUE_ORDER_CANCEL.getExchange(), "x-delayed-message", true, false, args);
//    }
//
//    /**
//     * 定义对应的消费队列
//     *
//     * @return org.springframework.amqp.core.Queue
//     */
//    @Bean
//    public Queue queue() {
//        //属性参数 队列名称 是否持久化
//        return new Queue(QueueEnum.QUEUE_ORDER_CANCEL.getName(), true);
//    }
//
//
//    /**
//     * 交换机和队列绑定一起
//     *
//     * @return org.springframework.amqp.core.Binding
//     */
//    @Bean
//    public Binding binding() {
//        return BindingBuilder
//                .bind(queue())
//                .to(customExchange())
//                .with(QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey())
//                .noargs();
//    }
//}
