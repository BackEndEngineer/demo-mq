package com.config;

import lombok.Data;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@Component
@ConfigurationProperties(locations = "classpath:mq_config.yaml", prefix = "mq")
public class MqConfig implements Serializable {

    @Autowired
    private ApplicationContext applicationContext;

    private List<DemoQueue> queues;

    private List<DemoExchange> exchanges;

    private List<DemoBind> binds;

    @Data
    public static class DemoQueue {

        private String name;

        private Boolean durable;
    }

    @Data
    public static class DemoExchange {
        private String name;
    }

    @Data
    public static class DemoBind {
        private String queue;

        private List<BindToExchange> exchanges;
    }

    @Data
    public static class BindToExchange{
        private String exchange;
    }

    @PostConstruct
    public void init(){
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)applicationContext.getAutowireCapableBeanFactory();

        Map<String , Queue> queueMap = new HashMap<>();
        Queue registerQueue;
        for(DemoQueue queue : this.queues){
            registerQueue = new Queue(queue.getName() , queue.getDurable());

            queueMap.put(queue.getName() , registerQueue);
            beanFactory.registerSingleton(queue.getName() , registerQueue);
        }

        Map<String , TopicExchange> exchangeMap = new HashMap<>();
        TopicExchange registerExchange;
        for(DemoExchange exchange : this.exchanges){
            registerExchange = new TopicExchange(exchange.getName());

            exchangeMap.put(exchange.getName() , registerExchange);
            beanFactory.registerSingleton(exchange.getName() , registerExchange);
        }

        //绑定exchange与queue关系
        Binding binding;
        int id=0;
        for(DemoBind bind : binds){
            for(BindToExchange bindToExchange : bind.getExchanges()){
                binding = BindingBuilder.bind(queueMap.get(bind.getQueue())).to(exchangeMap.get(bindToExchange.getExchange())).with(bind.getQueue());

                beanFactory.registerSingleton("bind_exchange_queue"+(id++), binding);
            }
        }
    }
}
