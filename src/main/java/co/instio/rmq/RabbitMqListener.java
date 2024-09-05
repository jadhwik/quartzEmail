package co.instio.rmq;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqListener {
    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME)
    public void receiveMessage(String message){
        System.out.println(message);
    }

}
