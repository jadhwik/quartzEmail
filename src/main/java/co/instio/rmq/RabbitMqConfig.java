package co.instio.rmq;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMqConfig {

    public static final String QUEUE_NAME="sample.queue";
    public static final String EXCHANGE_NAME="sample.exchange";
    public static final String ROUTING_KEY="sample.routingKey";

    @Bean
    public Queue queue(){
        return new Queue(QUEUE_NAME,false);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue,TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
    }
}
