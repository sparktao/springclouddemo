FROM java:8
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
  && echo 'Asia/Shanghai' > /etc/timezone
ADD event-service-0.0.1-SNAPSHOT.jar event.jar
ENTRYPOINT ["java","-jar","event.jar"]