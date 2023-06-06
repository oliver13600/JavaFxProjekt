package com.example.basiclayour.viewmodel;

import com.example.basiclayour.service.RouteService;
import com.example.basiclayour.service.TourService;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class AddTourViewModelTest {
    private AddTourViewModel viewModel;

    @Before
    public void setUp() {
        // Create an instance of AddTourViewModel with mock dependencies
        RouteService routeService = mock(RouteService.class);
        TourService tourService = mock(TourService.class);
        viewModel = new AddTourViewModel(routeService, tourService);
    }

    @Test
    public void testAddTour_WithEmptyFields_ShouldSetErrorMessage() {
        // Arrange
        viewModel.setTourName("");
        viewModel.setString1("");
        viewModel.setString2("");

        // Act
        viewModel.addTour("Car");

        // Assert
        assertEquals("Please fill out all TextBoxes", viewModel.getOutput());
    }


    @Test
    public void testGetChoiceBoxInputs_ShouldReturnCorrectList() {
        // Act
        ObservableList<String> choiceBoxInputs = viewModel.getChoiceBoxInputs();

        // Assert
        assertNotNull(choiceBoxInputs);
        assertEquals(3, choiceBoxInputs.size());
        assertEquals("Car", choiceBoxInputs.get(0));
        assertEquals("Bicycle", choiceBoxInputs.get(1));
        assertEquals("Walking", choiceBoxInputs.get(2));
    }

    @Test
    public void testSetDefaultValue_ShouldReturnCar() {
        // Act
        String defaultValue = viewModel.setDefaultValue();

        // Assert
        assertEquals("Car", defaultValue);
    }

    @Test
    public void testGetSanitizedString_ShouldRemoveSpecialCharacters() {
        // Arrange
        String input = "Hello#World123!";

        // Act
        String sanitizedString = viewModel.getSanitizedString(input);

        // Assert
        assertEquals("HelloWorld123", sanitizedString);
    }

}