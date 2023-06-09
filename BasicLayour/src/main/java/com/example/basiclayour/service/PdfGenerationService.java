package com.example.basiclayour.service;

import java.io.IOException;

public interface PdfGenerationService {
    // tour report information of a single tour + all associated tour logs

    void tourReport() throws IOException;

    // summarize report => average time // average distance // rating over all associated tour logs
    void summarizeReport() throws IOException;

    public void setTourToPdf(String test);
}
