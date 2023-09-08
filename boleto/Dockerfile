FROM openjdk:17-jdk-slim

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75"

WORKDIR /app

COPY build/boleto-0.0.1.jar /app/boleto.jar

# O comando para executar a aplicação
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar boleto.jar"]
