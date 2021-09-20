FROM openjdk:12-jdk-alpine
COPY miniweb.jar miniweb.jar
CMD ["java","-jar","miniweb.jar"]