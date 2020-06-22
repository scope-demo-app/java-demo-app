package com.undefinedlabs.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.undefinedlabs.app.model.Geoposition;
import com.undefinedlabs.app.model.Restaurant;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class GeopositionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeopositionService.class);

    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public GeopositionService() {
        this.httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .cache(null)
                .build();
    }

    public Geoposition getGeoposition(final Restaurant restaurant) {
        try {
            final Request request = new Request.Builder().url("https://maps.google.es").addHeader("Cache-Control", "no-cache").build();
            httpClient.newCall(request).execute().close();

            return Geoposition.FAKE;
        } catch (Exception e) {
            LOGGER.error("Could not get geoposition.", e);
            return Geoposition.EMPTY;
        }
    }

}
