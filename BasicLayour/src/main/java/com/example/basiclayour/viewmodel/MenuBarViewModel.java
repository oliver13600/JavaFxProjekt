package com.example.basiclayour.viewmodel;

import com.example.basiclayour.event.Event;
import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.pdfGeneration.PdfGenerationService;
import com.example.basiclayour.service.MapService;

import java.io.IOException;

public class MenuBarViewModel {

    private final PdfGenerationService pdfGenerationService;

    public MenuBarViewModel(PdfGenerationService pdfGenerationService){
        this.pdfGenerationService = pdfGenerationService;
    }

    public void generatePdf(){
        System.out.println("Gernerting pdf");
        try {
            pdfGenerationService.tourReport("FART");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
