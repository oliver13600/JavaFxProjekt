package com.example.basiclayour.service;

import com.example.basiclayour.dto.MapQuestApiResponse;
import com.example.basiclayour.dto.Route;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

public class MapQuestRouteService implements RouteService{
    private final ConfigurationService configurationService;
    private static final Logger logger = LogManager.getLogger(MapQuestRouteService.class);
    public MapQuestRouteService(ConfigurationService configurationService){
        this.configurationService = configurationService;
    }


    @Override
    public Route getRoute(String from, String to, String transportType) {
        String uri = "https://www.mapquestapi.com/directions/v2/route?";
        uri += "key=" + configurationService.getApiKey();
        uri += "&from=" + from;
        uri += "&to=" + to;
        uri += "&routeType=" + getRouteType(transportType);
        uri += "&unit=k";

        String responseJson = "";

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            responseJson = response.body();

            if (response.statusCode() >= 400) { // 500 = internal server error
                // handle error
                logger.error("MapquestApi not responding => StatusCode: " + response.statusCode());
            }

        } catch (URISyntaxException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        } catch (InterruptedException e) {
            logger.error(e);
        }

        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        MapQuestApiResponse apiResponse = null;

        try {
            apiResponse = mapper.readValue(
                    responseJson,
                    MapQuestApiResponse.class
            );
        } catch (JsonProcessingException e) {
            logger.error(e);
        }

        return apiResponse.getRoute();
    }

    @Override
    public void saveMap(String sessionId, String filename) {

        String uri = "https://www.mapquestapi.com/staticmap/v5/map?";
        uri += "key=" + configurationService.getApiKey();
        uri += "&session=" + sessionId;
        String filePath = "mapCollection/" + filename;

        try {
            ReadableByteChannel readableByteChannel =
                    Channels.newChannel((new URL(uri)).openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            FileChannel fileChannel = fileOutputStream.getChannel();

            fileChannel
                    .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);

            fileChannel.close();
            fileOutputStream.close();
            readableByteChannel.close();
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public String getRouteType(String choiceBoxSelection){
        if(choiceBoxSelection.equals("Car")){
            return "fastest";
        }
        if(choiceBoxSelection.equals("Bicycle")){
            return "bicycle";
        }
        if(choiceBoxSelection.equals("Walking")){
            return "pedestrian";
        }
        return "fastest";
    }

}
