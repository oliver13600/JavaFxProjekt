package com.example.basiclayour.viewmodel;

import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.service.*;
import com.example.basiclayour.view.ViewFactory;
import javafx.collections.ObservableList;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;

public class AddTourLogViewModelTest {

    private AddTourLogViewModel viewModel;

    private TourLogService tourLogService;

    private EventAggregator eventAggregator;

    private TourService tourService;

    private MapService mapService;

    @Mock
    private ViewFactory viewFactory;

    @BeforeEach
    public void setUp() {
        mapService = new MapService(eventAggregator, tourService);
    }

    @Test
    public void testAddTourLog_AllMandatoriesFilledOut() {

        viewModel = mock(AddTourLogViewModel.class);
        // Set up the test data
        viewModel.setHours("2");
        viewModel.setMinutes("30");
        viewModel.setLogComment("Great tour");
        viewModel.setDifficultyChoiceBoxInput();
        viewModel.setTotalTime("3 hours");
        viewModel.setLogDate(LocalDate.now());
        String selectedRating = "5 ★★★★★";

        // Call the method
        viewModel.addTourLog(selectedRating, "TourTest");

        // Verify the output
        String expectedOutput = "Comment: Great tour Difficulty: Medium TotalTime: 3 hours";
        assertEquals(expectedOutput, viewModel.getOutput());

        // Verify the state of the properties after the method call
        assertEquals("", viewModel.getHours());
        assertEquals("", viewModel.getMinutes());
        assertEquals("", viewModel.getLogComment());
        assertEquals("", viewModel.getDifficultyChoiceBoxInput());
        assertEquals("", viewModel.getTotalTime());
        assertEquals(null, viewModel.getLogDate());
    }

    @Test
    public void testAddTourLog_MandatoryFieldsNotFilledOut() {
        viewModel = new AddTourLogViewModel(tourLogService, mapService);
        // Call the method without filling out mandatory fields
        viewModel.addTourLog("5 ★★★★★", "Medium");

        // Verify the output
        assertEquals("Please fill out all TextFields and pick a date", viewModel.getOutput());
    }

    @Test
    public void testGetRatingChoiceBoxInput() {
        viewModel = new AddTourLogViewModel(tourLogService, mapService);
        // Call the method
        ObservableList<String> ratingChoiceBoxInput = viewModel.getRatingChoiceBoxInput();

        // Verify the size of the returned list
        assertEquals(5, ratingChoiceBoxInput.size());
        // Verify the elements in the list
        assertEquals("1 ★", ratingChoiceBoxInput.get(0));
        assertEquals("2 ★★", ratingChoiceBoxInput.get(1));
        assertEquals("3 ★★★", ratingChoiceBoxInput.get(2));
        assertEquals("4 ★★★★", ratingChoiceBoxInput.get(3));
        assertEquals("5 ★★★★★", ratingChoiceBoxInput.get(4));
    }

    @Test
    public void testSetRatingChoiceBoxInput() {
        viewModel = new AddTourLogViewModel(tourLogService, mapService);
        // Call the method
        String rating = viewModel.setRatingChoiceBoxInput();

        // Verify the returned rating
        assertEquals("5 ★★★★★", rating);
    }

}