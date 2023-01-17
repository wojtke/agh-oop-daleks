package com.javable.daleks.controllers;

import com.javable.daleks.DaleksApp;
import com.javable.daleks.Settings;
import com.javable.daleks.interfaces.IControllerFxmlBased;
import com.javable.daleks.logic.CampaignManager;
import com.javable.daleks.logic.ServiceManager;
import com.javable.daleks.logic.ViewManager;
import com.javable.daleks.models.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.io.FileNotFoundException;

public class CampaignController implements IControllerFxmlBased {
    private ObservableList<Level> levels;
    private FilteredList<Level> filteredLevels;
    private final ServiceManager serviceManager;

    public CampaignController() {
        serviceManager = new ServiceManager();
        levels = FXCollections.observableArrayList(
                serviceManager.getAllCampaignLevels()
        );
    }

    @FXML
    private TableView<Level> levelTable;
    @FXML
    private TableColumn<Level, String> levelNameCol;
    @FXML
    private TableColumn<Level, Integer>  mapSizeCol;
    @FXML
    private Button playButton, backButton, resetButton;
    @FXML
    public void initialize() {
        levelNameCol.setCellValueFactory(new PropertyValueFactory<>("levelName"));
        mapSizeCol.setCellValueFactory(new PropertyValueFactory<>("gridSize"));

        levelNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        mapSizeCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        levelTable.setItems(levels);
        levelTable.getSelectionModel().clearSelection();
        onSelectionChanged();
    }

    @FXML
    private void onSelectionChanged() {
        Level selectedLevel = levelTable.getSelectionModel().getSelectedItem();
        if (selectedLevel != null && selectedLevel.getCampainOrder() <= CampaignManager.GetCurrentMaxLv()) {
            playButton.setDisable(false);
        } else {
            playButton.setDisable(true);
        }
    }

    @FXML
    public void backButtonClicked() {
        ViewManager.setScene(Settings.MainView);
    }

    @FXML
    public void playButtonClicked() {
        Level selectedLevel = levelTable.getSelectionModel().getSelectedItem();
        try {
            DaleksApp.getMainController().startGame(selectedLevel);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getViewPath() {
        return Settings.CampaignView;
    }

    public void resetButtonClicked() {
        CampaignManager.ResetMaxCampaignLv();
    }
}
