FROM openjdk:8
# 集成的maven Dockerfile和maven打的jar包 都会复制到 target/docker目录下
COPY example-config-1.0-SNAPSHOT.jar example-config.jar
EXPOSE 8001
ENTRYPOINT java -jar example-config.jar