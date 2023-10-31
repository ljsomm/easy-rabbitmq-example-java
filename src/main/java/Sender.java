import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import domain.User;

public class Sender {

    public static void main(String[] args) throws JsonProcessingException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //TODO: Improve this code putting all the properties in a isolated file (application.properties, for instance).
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("rootRoot");
        User user = new User();
        user.setName("Lucas Juan");
        byte[] message = new ObjectMapper().writeValueAsBytes(user);
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare("user", false, false, false, null);
            channel.confirmSelect();
            channel.addConfirmListener((v1, v2) -> {
                System.out.println("Message delivered!");
            }, (v1, v2) -> {
                System.out.println("Something went wrong!");
            });
            channel.basicPublish("", "user", null, message);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
