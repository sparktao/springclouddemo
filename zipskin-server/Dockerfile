FROM java:8
ADD zipkin-server-2.19.2-exec.jar zipkin.jar
EXPOSE 9411
ENTRYPOINT ["java", "-jar", "/zipkin.jar"]