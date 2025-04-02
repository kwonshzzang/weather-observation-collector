package kr.co.kwonshzzang.weatherobservationcollector.controller;

import kr.co.kwonshzzang.weatherobservationcollector.dto.WeatherObservation;
import kr.co.kwonshzzang.weatherobservationcollector.service.DataCollectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DataCollectorController {
    private final DataCollectService dataCollectService;

    @PostMapping("/weather/observation")
    public void addWeatherObserveValues(@RequestBody WeatherObservation weatherObservation) {
        dataCollectService.doService(weatherObservation);
    }
}
