package com.javable.daleks.controllers;

import com.javable.daleks.Settings;
import com.javable.daleks.interfaces.IControllerFxmlBased;
import com.javable.daleks.logic.ViewManager;
import com.javable.daleks.models.GameMapSettings;
import com.javable.daleks.service.ServiceManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

public class LevelSelectController implements IControllerFxmlBased{
    private ObservableList<GameMapSettings> levels;
    public LevelSelectController() {
        ServiceManager serviceManager = new ServiceManager();

        levels = FXCollections.observableArrayList(
                serviceManager.GetAllLevels()
        );
    }

    @FXML
    private TableView<GameMapSettings> levelTable;
    @FXML
    private TableColumn<GameMapSettings, String> levelNameCol;
    @FXML
    private TableColumn<GameMapSettings, Integer>  mapSizeCol, daleksCountCol;
    @FXML
    private TextField levelNameInput, mapSizeInput, daleksCountInput;
    @FXML
    public void initialize() {
        levelNameCol.setCellValueFactory(new PropertyValueFactory<>("levelName"));
        mapSizeCol.setCellValueFactory(new PropertyValueFactory<>("gridCount"));
        daleksCountCol.setCellValueFactory(new PropertyValueFactory<>("daleksCount"));

        levelNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        mapSizeCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        daleksCountCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        levelTable.setItems(levels);
    }

    @FXML
    private void addButtonClicked() {
        GameMapSettings newLevel = new GameMapSettings(
                Integer.parseInt(mapSizeInput.getText()),
                Integer.parseInt(daleksCountInput.getText()),
                levelNameInput.getText()
        );
        levels.add(newLevel);
    }

    @FXML
    private void removeButtonClicked() {
        GameMapSettings selectedLevel = levelTable.getSelectionModel().getSelectedItem();
        levels.remove(selectedLevel);
    }

    @FXML
    public void GoBackButton() {
        ViewManager.SetScene(Settings.MainView);
    }

    @Override
    public String GetViewPath() {
        return Settings.LevelSelectView;
    }
}
