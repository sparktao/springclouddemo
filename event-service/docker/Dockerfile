FROM java:8
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
  && echo 'Asia/Shanghai' > /etc/timezone
ADD event-service-0.0.1-SNAPSHOT.jar /
ADD run.sh /
ENV JAVA_OPTS=""
ENV EUREKA_HOST "10.0.11.134"
ENV SERVER_HOST "10.0.11.134"
ENV SERVER_PORT "8071"
ENV MYSQL_CONNECTSTRING "10.0.11.134:3306"
ENV MYSQL_USER "root"
ENV MYSQL_PASSWORD "12345"
EXPOSE 8071
ENTRYPOINT ["sh", "-c", "/run.sh"]