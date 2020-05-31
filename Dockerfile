FROM openjdk:11
COPY ./target/*.jar /home/courses.jar
ENTRYPOINT java -jar /home/courses.jar --spring.profiles.active=prod