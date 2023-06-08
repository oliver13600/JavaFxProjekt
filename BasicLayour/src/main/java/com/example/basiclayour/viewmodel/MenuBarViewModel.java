package com.example.basiclayour.viewmodel;

import com.example.basiclayour.service.PdfGenerationService;
import com.example.basiclayour.service.TourService;

import java.io.IOException;

public class MenuBarViewModel {

    private final PdfGenerationService pdfGenerationService;

    public MenuBarViewModel(PdfGenerationService pdfGenerationService){
        this.pdfGenerationService = pdfGenerationService;
    }

    public void generatePdf(){
        try {
            pdfGenerationService.tourReport();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
