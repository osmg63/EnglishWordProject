package com.example.english.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SelfPingTask {

    @Scheduled(fixedRate = 5 * 60 * 100)
    public void pingSelf() {
        try {
            URL url = new URL("https://englishwordproject.onrender.com/swagger-ui/index.html#/ ");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            System.out.println(responseCode);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


}
