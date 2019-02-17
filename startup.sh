#echo "------------------------ CLOSING OLD SESSIONS -------------------------------"
#screen -ls | grep Detached | cut -d. -f1 | awk '{print $1}' | xargs kill
#
#echo "------------------------ IS ZOOKEEPER RUNNING? ------------------------------"
#lsof -nP -i4TCP:2181 | grep LISTEN;
#
#echo "------------------------ IS KAFKA RUNNING? ----------------------------------"
#lsof -nP -i4TCP:9092 | grep LISTEN;
#
#echo "------------------------ STARTING ZOOKEEPER SERVER --------------------------"
#screen -d -m -S "zookeeper-server" bash -c "kafka/bin/zookeeper-server-start.sh kafka/config/zookeeper.properties";
#
#echo "------------------------ STARTING KAFKA SERVER ------------------------------"
#screen -d -m -S "kafka-server" bash -c "kafka/bin/kafka-server-start.sh kafka/config/server.properties";
#
#echo "------------------------ STARTING SCHEMA REGISTRY SERVER --------------------"
#screen -d -m -S "schema-registry-server" bash -c "confluent/bin/schema-registry-start ./confluent/etc/schema-registry/schema-registry.properties";
#
#echo "------------------------ STARTING CONFIG SERVER -----------------------------"
#screen -d -m -S "config-server" bash -c "cd config-server; ./startup.sh";
#
#echo "------------------------ STARTING EUREKA SERVER -----------------------------"
#screen -d -m -S "eureka-server" bash -c "cd eureka-server; ./startup.sh";
#
#echo "------------------------ STARTING API GATEWAY SERVICE  ---------------------"
#screen -d -m -S "api-gateway-service" bash -c "cd api-gateway-service; ./startup.sh";

echo "------------------------ STARTING API GATEWAY SERVICE  ----------------------"
screen -d -m -S "api-gateway-service" bash -c "cd api-gateway-service; ./startup.sh";

echo "------------------------ STARTING CART SERVICE  -----------------------------"
screen -d -m -S "cart-service" bash -c "cd cart-service; ./startup.sh";

echo "------------------------ STARTING LOGGING SERVICE  --------------------------"
screen -d -m -S "logging-service" bash -c "cd logging-service; ./startup.sh";

echo "------------------------ STARTING NOTIFICATION SERVICE  ---------------------"
screen -d -m -S "notification-service" bash -c "cd notification-service; ./startup.sh";

echo "------------------------ STARTING ORDER SERVICE  ----------------------------"
screen -d -m -S "order-service" bash -c "cd order-service; ./startup.sh";

echo "------------------------ STARTING USER MANAGEMENT SERVICE  ------------------"
screen -d -m -S "user-management-service" bash -c "cd user-management-service; ./startup.sh";

echo "------------------------ STARTING WAREHOUSE SERVICE  ------------------------"
screen -d -m -S "warehouse-service" bash -c "cd warehouse-service; ./startup.sh";
