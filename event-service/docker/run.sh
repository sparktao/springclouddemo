java $JAVA_OPTS -jar /event-service-0.0.1-SNAPSHOT.jar \
    --eureka.host=$EUREKA_HOST --server.host=$SERVER_HOST --server.port=$SERVER_PORT  \
    --mysql.connectstring=$MYSQL_CONNECTSTRING --mysql.user=$MYSQL_USER --mysql.password=$MYSQL_PASSWORD