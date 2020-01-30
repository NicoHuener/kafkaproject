@echo off
docker exec -it kafka_kafka_1 kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic streams-wordcount-output --config cleanup.policy=compact
cls
@echo on
docker exec -it kafka_kafka_1 kafka-console-consumer --bootstrap-server localhost:9092 --topic streams-wordcount-output --formatter kafka.tools.DefaultMessageFormatter --property print.key=true --property print.value=true --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer