package kr.co.kwonshzzang.weatherobservationcollector.service;

import kr.co.kwonshzzang.model.avro.WeatherObserveValue;
import kr.co.kwonshzzang.weatherobservationcollector.dto.WeatherObservation;
import kr.co.kwonshzzang.weatherobservationcollector.repository.KafkaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataCollectService {
    private final KafkaRepository kafkaRepository;

    @Value("${spring.kafka.template.default-topic}")
    private String defaultTopic;

    public void doService(WeatherObservation weatherObservation) {
        String resultCode = weatherObservation.getResponse().getHeader().getResultCode();
        if(!resultCode.equals("00")) return;

        List<WeatherObserveValue> observationValues
                = weatherObservation.getResponse().getBody().getItems().getItem()
                .stream().map(WeatherObservation.Item::toConverseData).toList();

        kafkaRepository.send(defaultTopic, observationValues);
    }
}
