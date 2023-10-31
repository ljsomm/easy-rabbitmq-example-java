# RabbitMQ Example for beginners

In this repository, I'm gonna show you an example of how to use RabbitMQ as message broker
between two process (you can abstract it for your API or microservice). 
But what exactly is RabbitMQ? <a href="https://www.rabbitmq.com/features.html">Read the official documentation.</a>

## How it works?

In this project you're going to see two classes in the <code>src/main/java</code> directory, which contains two classes

- Sender: The producer of the message broker.
- Receiver: The consumer of the message broker.

Firstly, you need to execute <code>docker-compose up -d</code> to start the rabbitmq container.
Execute the sender, cause in their source code will be declared the queue creation.
Secondly, run the receiver, if everything worked okay, you are going to see the messages sent from the Sender.

## Technologies (to run)

- JDK11
- Maven
- Docker