package com.example.basiclayour.service;

import java.io.IOException;
import java.util.List;


import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class iTextPdfGenerationService implements PdfGenerationService {

    private final TourService tourService;

    public iTextPdfGenerationService(TourService tourService){
        this.tourService = tourService;
    }

    @Override
    public void tourReport(String tourName) throws IOException {

        List<String> oneTour = tourService.getTourInformation("Graz-to-Wien");

        // Tour: Test83459 From Graz to Wien in 01:58:04 (193.8) transportation: Car
        String splitString[] = oneTour.toString().split(" ");
        String StringName = splitString[1];
        String StringFrom = splitString[3];
        String StringTo = splitString[5];
        String StringDuration = splitString[7];
        String StringDistance = splitString[8];
        String StringTransportation = splitString[10];
        StringTransportation = StringTransportation.replaceAll("]", "");

        // Name Document
        PdfDocument pdf = new PdfDocument(new PdfWriter("TourReport.pdf"));
        // Initialize document
        Document document = new Document(pdf);

        // Set Header for PDF
        Paragraph tourHeader = new Paragraph("Tour-Report:")
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(32)
                .setBold();

        document.add(tourHeader);

        // Set Header for TourName
        Paragraph tourNameHeader = new Paragraph(StringName)
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(26)
                .setBold();

        document.add(tourNameHeader);

        // Add TourInformation
        document.add(new Paragraph("From: " + StringFrom));
        document.add(new Paragraph("To : " + StringTo));
        document.add(new Paragraph("Duration: " + StringDuration));
        document.add(new Paragraph("Distance: " + StringDistance));
        document.add(new Paragraph("Transportation-Type: " + StringTransportation));

        // Set Image of tour
        ImageData data = ImageDataFactory.create("mapCollection/Graz-to-Wien.jpg");
        Image image = new Image(data);
        image.scaleAbsolute(400, 400);
        document.add(image);

        // Set Header of tour-logs
        Paragraph tourLogsHeader = new Paragraph("Tour Logs:")
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(32)
                .setBold();

        document.add(tourLogsHeader);


        // Add TourLog Information - work in progress......



        //Close document
        document.close();
        System.out.println("PDF Created");
    }

    @Override
    public void summarizeReport(){

    }
}
