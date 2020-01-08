

1. 添加eureka-server注册服务
2. 添加order服务，调用product 服务使用了Ribbon客户端负载均衡
3. 添加zuul API网关，注册order和product 服务
4. 添加Slueth 监控支持，同时docker-compose.yml中加入zipkin可视化工具查看监控
5. docker 部署支持,添加docker-compose.yml和每个工程的Dockfile
6. order引入了申明式Feign调用Product方法
7. 添加Spring Cloud Gateway作为网关，注册order, product, event服务
8. 添加ELK(Elasticsearch+Kibana+Logstash)日志的支撑

启动
docker-compose up

更新
docker-compose build
docker-compose up -d

