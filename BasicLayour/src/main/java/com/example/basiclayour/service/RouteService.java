package com.example.basiclayour.service;

import com.example.basiclayour.dto.Route;

public interface RouteService {
    Route getRoute(String from, String to);

    void saveMap(String sessionId, String filename);
}
