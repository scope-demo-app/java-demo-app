package com.undefinedlabs.scope.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.undefinedlabs.scope.model.Geoposition;
import com.undefinedlabs.scope.model.Restaurant;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeopositionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeopositionService.class);

    private final OkHttpClient httpClient = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Geoposition getGeoposition(final Restaurant restaurant) {
        try {
            final Request request = new Request.Builder().url("https://nominatim.openstreetmap.org/search/?format=json&street=807 8th Ave&city=New york&postelcode=10079").build();
            final Response response = httpClient.newCall(request).execute();

            final List<Geoposition> geopositions = response.body() != null ? objectMapper.readValue(response.body().string(), new TypeReference<List<Geoposition>>() {}) : new ArrayList<>();
            return geopositions.isEmpty() ? Geoposition.EMPTY : geopositions.get(0);
        } catch (Exception e) {
            LOGGER.error("Could not get geoposition.", e);
            return Geoposition.EMPTY;
        }
    }

}
