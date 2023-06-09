package com.example.basiclayour.view;

import com.example.basiclayour.viewmodel.MenuBarViewModel;
import javafx.fxml.FXML;

public class MenuBarView {

    private final MenuBarViewModel menuBarViewModel;

    public MenuBarView(MenuBarViewModel menuBarViewModel) {
        this.menuBarViewModel = menuBarViewModel;
    }

    @FXML
    void tourReport(){menuBarViewModel.generatePdf();}

    @FXML
    void summarizeReport(){menuBarViewModel.generateSummarizeReport();}
}
