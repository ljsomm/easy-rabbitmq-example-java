import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import domain.User;

public class Receiver {

    public static void main(String[] args) {
        ConnectionFactory connFactory = new ConnectionFactory();
        connFactory.setHost("localhost");
        connFactory.setUsername("root");
        connFactory.setPassword("rootRoot");
        try {
            Connection conn = connFactory.newConnection();
            Channel channel = conn.createChannel();
            channel.basicConsume("user", false,
                (metadata, data) -> {
                    System.out.println(new ObjectMapper().readValue(data.getBody(), User.class).getName());
                    channel.basicAck(data.getEnvelope().getDeliveryTag(), true);
                }, (err) -> {
                    System.out.println(err);
                }
            );
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
