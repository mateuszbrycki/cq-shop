echo "------------------------ CLOSING OLD SESSIONS -------------------------------"
screen -ls | grep Detached | cut -d. -f1 | awk '{print $1}' | xargs kill

echo "------------------------ STARTING ZOOKEEPER SERVER --------------------------"
screen -d -m -S "zookeeper-server" bash -c "kafka/bin/zookeeper-server-start.sh config/zookeeper.properties";

echo "------------------------ STARTING KAFKA SERVER ------------------------------"
screen -d -m -S "kafka-server" bash -c "kafka/bin/kafka-server-start.sh config/server.properties";
