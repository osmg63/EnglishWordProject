package com.example.english.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Component
public class SelfPingTask {

    @Scheduled(fixedRate = 5 * 60 * 1000) // 10 dakikada bir
    public void pingSelf() {
        try {
            URL url = new URL("https://englishwordproject.onrender.com/swagger-ui/index.html#/ ");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
