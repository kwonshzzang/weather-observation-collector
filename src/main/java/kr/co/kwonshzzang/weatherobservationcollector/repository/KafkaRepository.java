package kr.co.kwonshzzang.weatherobservationcollector.repository;

import kr.co.kwonshzzang.model.avro.WeatherObserveValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class KafkaRepository {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String topic, List<WeatherObserveValue> observationValues) {
        for (WeatherObserveValue observeValue : observationValues) {
          send(topic, observeValue);
        }
    }


    public void send(String topic, WeatherObserveValue observeValue) {
        String key = observeValue.getBaseDate() + "|" + observeValue.getBaseTime() + "|" +
                observeValue.getCategory()  + "|" + observeValue.getNx() + "|" + observeValue.getNy();

        kafkaTemplate.send(topic, key, observeValue);
        writeLog(topic, key, observeValue);
    }


    private void writeLog(String topic, String key, Object value) {
        log.info("Produced event to topic = {}: key = {} value = {}", topic , key, value);
    }
}
