package com.example.basiclayour.viewmodel;

import com.example.basiclayour.service.MapService;
import com.example.basiclayour.service.PdfGenerationService;
import com.example.basiclayour.service.TourService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class MenuBarViewModel {

    private final PdfGenerationService pdfGenerationService;

    private static final Logger logger = LogManager.getLogger(MenuBarViewModel.class);

    public MenuBarViewModel(PdfGenerationService pdfGenerationService){
        this.pdfGenerationService = pdfGenerationService;
    }

    public void generatePdf(){
        try {
            pdfGenerationService.tourReport();
        } catch (IOException e) {
            logger.error("Error when creating TourReport-Pdf" + e);
        }
    }

    public void generateSummarizeReport(){
        try {
            pdfGenerationService.summarizeReport();
        } catch (IOException e) {
            logger.error("Error when creating SummarizeReport-Pdf" + e);
        }
    }
}
