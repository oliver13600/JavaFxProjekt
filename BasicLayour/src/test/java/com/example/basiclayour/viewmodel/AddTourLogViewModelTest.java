package com.example.basiclayour.viewmodel;

import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.model.Tour;
import com.example.basiclayour.service.*;
import com.example.basiclayour.view.ViewFactory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    public void testCheckTotalTimeIsValid_ValidTotalTime_ReturnsTrue() {
        viewModel = new AddTourLogViewModel(tourLogService, mapService);
        assertTrue(viewModel.checkTotalTimeIsValid("02:30:00"));
    }

    @Test
    public void testCheckTotalTimeIsValid_InvalidTotalTime_ReturnsFalse() {
        viewModel = new AddTourLogViewModel(tourLogService, mapService);
        assertFalse(viewModel.checkTotalTimeIsValid("2:30:00"));
        assertFalse(viewModel.checkTotalTimeIsValid("02:30"));
        assertFalse(viewModel.checkTotalTimeIsValid("02:30:00:00"));
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

    @Test
    public void testGetRatingChoiceBoxInput_ReturnsCorrectList() {
        viewModel = new AddTourLogViewModel(tourLogService, mapService);
        ObservableList<String> expectedList = viewModel.getRatingChoiceBoxInput();

        assertEquals(5, expectedList.size());
        assertEquals("1 ★", expectedList.get(0));
        assertEquals("2 ★★", expectedList.get(1));
        assertEquals("3 ★★★", expectedList.get(2));
        assertEquals("4 ★★★★", expectedList.get(3));
        assertEquals("5 ★★★★★", expectedList.get(4));
    }

    @Test
    public void testGetDifficultyChoiceBoxInput_ReturnsCorrectList() {
        viewModel = new AddTourLogViewModel(tourLogService, mapService);
        ObservableList<String> expectedList = viewModel.getDifficultyChoiceBoxInput();

        assertEquals(5, expectedList.size());
        assertEquals("Beginner", expectedList.get(0));
        assertEquals("Easy", expectedList.get(1));
        assertEquals("Normal", expectedList.get(2));
        assertEquals("Hard", expectedList.get(3));
        assertEquals("Extreme", expectedList.get(4));
    }

}