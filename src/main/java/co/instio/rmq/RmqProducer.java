package co.instio.rmq;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RmqProducer {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMqConfig rabbitMqConfig;

    public void sendMessage(String message){
        rabbitTemplate.convertAndSend(rabbitMqConfig.EXCHANGE_NAME,rabbitMqConfig.ROUTING_KEY,message);

    }

}
