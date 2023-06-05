package com.example.basiclayour.pdfGeneration;

import java.io.IOException;

public interface PdfGenerator {
    // tour report information of a single tour + all associated tour logs

    void tourReport(String tourName) throws IOException;

    // summarize report => average time // average distance // rating over all associated tour logs
    void summarizeReport();
}
