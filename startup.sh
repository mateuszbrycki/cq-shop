echo "------------------------ CLOSING OLD SESSIONS -------------------------------"
screen -ls | grep Detached | cut -d. -f1 | awk '{print $1}' | xargs kill

echo "------------------------ IS ZOOKEEPER RUNNING? ------------------------------"
lsof -nP -i4TCP:2181 | grep LISTEN;

echo "------------------------ IS KAFKA RUNNING? ----------------------------------"
lsof -nP -i4TCP:9092 | grep LISTEN;

echo "------------------------ STARTING ZOOKEEPER SERVER --------------------------"
screen -d -m -S "zookeeper-server" bash -c "kafka/bin/zookeeper-server-start.sh kafka/config/zookeeper.properties";

echo "------------------------ STARTING KAFKA SERVER ------------------------------"
screen -d -m -S "kafka-server" bash -c "kafka/bin/kafka-server-start.sh kafka/config/server.properties";

echo "------------------------ STARTING CONFIG SERVER -----------------------------"
screen -d -m -S "config-server" bash -c "cd config-server; ./startup.sh";

echo "------------------------ STARTING EUREKA SERVER -----------------------------"
screen -d -m -S "eureka-server" bash -c "cd eureka-server; ./startup.sh";

echo "------------------------ STARTING EXAMPLE1 ----------------------------------"
screen -d -m -S "example1" bash -c "cd example1; ./startup.sh";

echo "------------------------ STARTING EXAMPLE2 ----------------------------------"
screen -d -m -S "example2" bash -c "cd example2; ./startup.sh";
