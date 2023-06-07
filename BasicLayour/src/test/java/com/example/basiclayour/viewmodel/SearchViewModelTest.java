package com.example.basiclayour.viewmodel;

import com.example.basiclayour.service.SearchService;
import com.example.basiclayour.service.TourService;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchViewModelTest {

    SearchService searchService;

    TourService tourService;

    SearchViewModel searchViewModel;

    @Test
    public void searchTest()
    {
        //Arrange
        searchService = mock(SearchService.class);
        tourService = mock(TourService.class);
        searchViewModel = new SearchViewModel(tourService, searchService);


        //Act

        searchViewModel = new SearchViewModel(
                tourService, searchService
        );

        searchViewModel.setString1("Test");

        searchViewModel.searchTour();

        //Assert

        assertEquals("Test", searchViewModel.getString1());

    }

    @Test
    public void searchTest2()
    {
        //Arrange
        searchService = mock(SearchService.class);
        tourService = mock(TourService.class);
        searchViewModel = new SearchViewModel(tourService, searchService);


        //Act

        searchViewModel = new SearchViewModel(
                tourService, searchService
        );

        searchViewModel.setString1("");

        searchViewModel.searchTour();

        //Assert

        assertEquals("", searchViewModel.getString1());

    }

}