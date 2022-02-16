FROM openjdk:8
# 集成的maven Dockerfile和maven打的jar包 都会复制到 target/docker目录下
COPY example-eureka-1.0-SNAPSHOT.jar example-eureka.jar
ENTRYPOINT java -jar example-eureka.jar
