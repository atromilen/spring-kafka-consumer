# spring-kafka-consumer
> Kafka consumer using Spring boot and Spring-kafka dependency.<br/><br/>
> **To get the full picture of Kafka messaging system (produce events to Kafka -> consume events from Kafka), check out 
> the sibling project [spring-kafka-producer](https://github.com/atromilen/spring-kafka-producer) and try to play with 
> a kafka producer features!**
> 
> _Coded by [atromilen](https://github.com/atromilen)_

## About technology

**Spring boot**

Responsible for creating a Stand-alone application that just run in any environment, using all Spring framework benefits.

**Spring-kafka**

This Spring module allow us to interact with Kafka broker, providing us a **Template** as a high-level abstraction for send
or consume messages to Kafka topics. Also, provides support for Message-driven POJOs with **@KafkaListener** annotations and
a Listener container. Features and documentation can be found [here](https://spring.io/projects/spring-kafka).

**Makefile**

All the commands related to application running or gradle tasks were automated using makefiles. This is a convenience 
and easy way to start all the infrastructure and build tasks entering only one command instead to follow several steps 
to run the application. You can find documentation related to makefiles [here](https://makefiletutorial.com/).

## Prerequisites
1. **Java 11** is the base language used to code this application, and you will need to install the jdk 11 to run the app.
2. [spring-kafka-producer](https://github.com/atromilen/spring-kafka-producer) project need to be cloned in your local
machine and the application needs to be running in order to test the indirect communication between 2 microservices
through Kafka messaging. Follow the instructions written in README file to start the spring-kafka-producer app.
**Note: all the docker containers services are managed in the spring-kafka-producer project and isn't responsibility of
this project.**
3. **Make** is required to run the makefile that builds and executes the application. This may be part of some Unix based
OS or can be installed through package managers such as apt-get or be part of development tools like Xcode in mac osx. 
In Windows OS you can install it through some package manager like [Scoop](https://scoop.sh/) or 
[Chocolatey](https://chocolatey.org/).

## Getting Started

1. Assuming that you've cloned the project [spring-kafka-producer](https://github.com/atromilen/spring-kafka-producer), 
start that project following the instructions detailed in the project's README. Keep the terminal window (running the 
**producer app**) visible before to continue with the next step.

2. Start this Spring boot application entering in terminal **make start** (shortcut for ./gradlew bootRun). Similar to
previous step, keep this terminal window (running the **consumer app**) visible before to continue with the next step.

3. Now you have the 2 project running independently of each other (consumer and producer) and the unique communication 
point is the Kafka broker. <br/><br/>Send a record to Kafka through the REST API provided by the Producer app. Here, you have an example of payload cURL 
(execute it in the console or import it in Postman):
```bash
curl --location --request POST 'localhost:8081/messages/save' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "email@email.com",
    "message_body": "First record! sent from app spring-kafka-producer that'\''s running separately!"
}'
```
**It's time to check the results!**

Look at the producer app terminal. You should see something like this, meaning the event was sent successfully.
```bash
2022-12-14 00:59:27.237  INFO 13988 --- [ad | producer-1] c.a.s.k.p.service.KafkaProducerService   : Message has been written successfully to topic messages-for-emailing into partition 1 with key msg-882fcd75-cc83-4169-a5fe-e0e864f90323.
```
And now look at the consumer app terminal. You will see the event sent via REST API to producer being read by the consumer:
```bash
2022-12-14 00:59:27.238  INFO 31368 --- [ntainer#0-0-C-1] .k.c.s.MessageForEmailingConsumerService : Read new message: Event(date=2022-12-14T03:59:27.232293400Z, data={email=email@email.com, message_body=First record! sent from app spring-kafka-producer that's running separately!})
```

## Changes & Features History

### [Initial Commit](https://github.com/atromilen/spring-kafka-consumer/commit/255574afda957476f042289ac770ffaf144530ef)
Very first version of this consumer, reading topic records and printing its content in console. The implementation is 
using the feature **@KafkaListener** provided by the library _spring-kafka_.<br/>
All configurations are done correctly to consume events sent by the sibling project **spring-kafka-producer**.
