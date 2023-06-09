package com.example.basiclayour.service;

import java.io.IOException;

import com.example.basiclayour.HelloApplication;
import com.example.basiclayour.model.Tour;
import com.example.basiclayour.model.TourLog;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class iTextPdfGenerationService implements PdfGenerationService {
    private static final Logger logger = LogManager.getLogger(iTextPdfGenerationService.class);
    private final TourService tourService;
    public String tourToPdf;

    public iTextPdfGenerationService(TourService tourService){
        this.tourService = tourService;
    }

    @Override
    public void tourReport() throws IOException {

        String selectedTour = getTourToPdf();

        if(selectedTour != null){
            logger.info("Creating Pdf for: " + selectedTour);
            Tour oneTour = tourService.getTourInformation(selectedTour);

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
            Paragraph tourNameHeader = new Paragraph(oneTour.getName())
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setFontSize(26)
                    .setBold();

            document.add(tourNameHeader);

            // Add TourInformation
            document.add(new Paragraph("From: " + oneTour.getFromStart()));
            document.add(new Paragraph("To: " + oneTour.getToFinish()));
            document.add(new Paragraph("Estimated Time: " + (int)(oneTour.getEstimatedTime() / 60) + " hours and " + Math.round(oneTour.getEstimatedTime() % 60) + " minutes"));
            document.add(new Paragraph("Distance: " + oneTour.getTourDistance() + " km"));
            document.add(new Paragraph("Transportation-Type: " + oneTour.getTransportType()));

            // Set Image of tour
            ImageData data = ImageDataFactory.create("mapCollection/" + oneTour.getFromStart() + "-to-" + oneTour.getToFinish() + ".jpg");
            Image image = new Image(data);
            image.scaleAbsolute(400, 400);
            document.add(image);

            // Set Header of tour-logs
            Paragraph tourLogsHeader = new Paragraph("Tour Logs:")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setFontSize(32)
                    .setBold();

            document.add(tourLogsHeader);

            // Tour Logs
            if(oneTour.getTourLogs().size() != 0){

                for(TourLog element : oneTour.getTourLogs()){

                    // Date & Time
                    document.add(new Paragraph("Date&Time: " + element.getDateTime()));

                    // Comment
                    document.add(new Paragraph("Comment: " + element.getComment()));

                    // Difficulty
                    document.add(new Paragraph("Difficulty: " + convertDifficultyToString(String.valueOf(element.getDifficulty()))));

                    // TotalTime
                    document.add(new Paragraph("Total Time: " + (int)(element.getTotalTime() / 60) + " hours and " + Math.round(element.getTotalTime() % 60) + " minutes"));

                    // Rating
                    document.add(new Paragraph("Rating: " + convertRatingToString(String.valueOf(element.getRating()))));

                    document.add(new Paragraph("------------------------"));
                }
                document.add(new Paragraph("--END OF FILE--"));

            } else {
                document.add(new Paragraph("No TourLogs found :("));
            }
            //Close document
            document.close();
            logger.info("SUCCESS - PDF Created for: " + selectedTour);
         } else {
            logger.error("No tour was selected");
        }
    }

    @Override
    public void summarizeReport(){

        // For each tour => avg time + distance + rating of all tourLogs



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
