package com.example.basiclayour.service;

import java.io.IOException;
import java.util.Arrays;
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

    private final TourLogService tourLogService;

    public String tourToPdf;

    public iTextPdfGenerationService(TourService tourService, TourLogService tourLogService){
        this.tourService = tourService;
        this.tourLogService = tourLogService;
    }

    @Override
    public void tourReport() throws IOException {

        String selectedTour = getTourToPdf();

        System.out.println("Test" + selectedTour);

        List<String> oneTour = tourService.getTourInformation(selectedTour);

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
        ImageData data = ImageDataFactory.create("mapCollection/" + StringFrom + "-to-" + StringTo + ".jpg");
        Image image = new Image(data);
        image.scaleAbsolute(400, 400);
        document.add(image);

        // Set Header of tour-logs
        Paragraph tourLogsHeader = new Paragraph("Tour Logs:")
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(32)
                .setBold();

        document.add(tourLogsHeader);



        // Add TourLog Information - geht bis jetzt nur mit einem TourLog
        List<String> tourLogs = tourLogService.getTourLogInformation(selectedTour);

        System.out.println("FFFFFFFFFFFFFFFFF: " + tourLogs.toString());


        if(tourLogs.isEmpty() == false){

            for(int i = 0; i < tourLogs.size(); i++){

                String tourLogsString = Arrays.toString(new String[]{tourLogs.get(i)});
                String tourLogsStringSplit[] = tourLogsString.split("\\|");

                // Date & Time
                String dateTimeString = tourLogsStringSplit[0];
                String finaldateTimeString[] = dateTimeString.split("T");
                String dateString[] = finaldateTimeString[0].split("\\[");

                document.add(new Paragraph("Date: " + dateString[1]));
                document.add(new Paragraph("Time: " + finaldateTimeString[1].substring(0,5)));

                // Comment
                document.add(new Paragraph("Comment: " + finaldateTimeString[1].substring(5,finaldateTimeString[1].length())));

                // Difficulty
                String difficultyString = tourLogsStringSplit[1];
                String finaldifficultyString = convertDifficultyToString(difficultyString);
                document.add(new Paragraph("Difficulty: " + finaldifficultyString));

                // TotalTime
                String totalTimeString = tourLogsStringSplit[2];
                totalTimeString += " minutes";
                document.add(new Paragraph("Total Time: " + totalTimeString));

                // Rating
                String ratingString = tourLogsStringSplit[3];
                String finalRatingString = convertRatingToString(ratingString);
                document.add(new Paragraph("Rating: " + finalRatingString));

                document.add(new Paragraph("------------------------"));

                //System.out.println(tourLogs);
                // [2023-05-30T22:23|1|1342.3667|5|com.example.basiclayour.model.Tour@39f2d2a7]
            }
            document.add(new Paragraph("--END OF FILE--"));

        } else {
            document.add(new Paragraph("No TourLogs found :("));
        }

        //Close document
        document.close();
        System.out.println("PDF Created");
    }

    @Override
    public void summarizeReport(){

    }

    public String getTourToPdf() {
        return tourToPdf;
    }

    @Override
    public void setTourToPdf(String tourToPdf) {
        this.tourToPdf = tourToPdf;
    }

    public static String convertRatingToString(String rating){
        switch (rating.toLowerCase()){
            case "1":
                return "1 Star (*)";
            case "2":
                return "2 Stars (**)";
            case "3":
                return "3 Stars (***)";
            case "4":
                return "4 Stars (****)";
            case "5":
                return "5 Stars (*****)";
            default:
                return "No rating found";
        }
    }

    public String convertDifficultyToString(String difficulty){
        switch (difficulty.toLowerCase()){
            case "1":
                return "Beginner";
            case "2":
                return "Easy";
            case "3":
                return "Medium";
            case "4":
                return "Hard";
            case "5":
                return "extreme";
            default:
                return "No difficulty found";
        }
    }
}
