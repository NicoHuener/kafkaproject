echo The producer is starting...
@echo off
docker-compose up -d
start cmd.exe /k "echo The consumer is starting... &start-consumer.bat"
docker exec -it kafka_kafka_1 kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic streams-plaintext-input
start /min cmd.exe /k "docker exec -it kafka_kafka_1 kafka-run-class org.apache.kafka.streams.examples.wordcount.WordCountDemo"
cls
@echo on
docker exec -it kafka_kafka_1 kafka-console-producer --broker-list localhost:9092 --topic streams-plaintext-input