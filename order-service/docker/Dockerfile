FROM java:8
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
  && echo 'Asia/Shanghai' > /etc/timezone
ADD order-service-0.0.1-SNAPSHOT.jar /
ADD run.sh /
ENV JAVA_OPTS ""
ENV EUREKA_HOST "10.0.11.134"
ENV SERVER_HOST "10.0.11.134"
ENV SERVER_PORT "8091"
EXPOSE 8091
ENTRYPOINT ["sh", "-c", "/run.sh"]