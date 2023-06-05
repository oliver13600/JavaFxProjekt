package com.example.basiclayour.pdfGeneration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.basiclayour.model.Tour;
import com.example.basiclayour.repository.TourRepository;
import com.example.basiclayour.service.SearchService;
import com.example.basiclayour.service.TourService;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class PdfGenerationService implements PdfGenerator {

    private final TourService tourService;

    public PdfGenerationService(TourService tourService){
        this.tourService = tourService;
    }

    @Override
    public void tourReport(String tourName) throws IOException {

        List<String> oneTour = tourService.findAll();

        // muss beim tourservice ein funktion schreiben die alle daten von einer tour ausgibt + alle logs dann noch davon

        System.out.println(oneTour.toString());

        PdfDocument pdf = new PdfDocument(new PdfWriter("TourReport.pdf"));
        // Initialize document
        Document document = new Document(pdf);

        Paragraph tourHeader = new Paragraph("Tour-Report:")
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(32)
                .setBold();

        document.add(tourHeader);

        for (String string : oneTour) { // noch ändern
            document.add(new Paragraph(string.toString()));
        }

       // document.add(new Paragraph("This is a Test\n));

        ImageData data = ImageDataFactory.create("mapCollection/Graz-to-Wien.jpg");
        Image image = new Image(data);
        image.scaleAbsolute(400, 400);
        document.add(image);

        Paragraph tourLogsHeader = new Paragraph("Tour Logs:")
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(32)
                .setBold();

        document.add(tourLogsHeader);

        //Close document
        document.close();
        System.out.println("PDF Created");
    }

    @Override
    public void summarizeReport(){

    }
}
