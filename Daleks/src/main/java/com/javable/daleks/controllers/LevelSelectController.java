package com.javable.daleks.controllers;

import com.javable.daleks.DaleksApp;
import com.javable.daleks.Settings;
import com.javable.daleks.interfaces.IControllerFxmlBased;
import com.javable.daleks.logic.ViewManager;
import com.javable.daleks.models.GameMapSettings;
import com.javable.daleks.models.JsonResult;
import com.javable.daleks.service.ServiceManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

public class LevelSelectController implements IControllerFxmlBased{

    //TODO: Dodać wyszukiwanie po nazwie poziomu/parametrach
    private ObservableList<GameMapSettings> levels;
    private FilteredList<GameMapSettings> filteredLevels;
    private final ServiceManager serviceManager;

    public LevelSelectController() {
        serviceManager = new ServiceManager();

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
    private Button addButton, removeButton, playButton, backButton;
    @FXML
    public void initialize() {
        levelNameCol.setCellValueFactory(new PropertyValueFactory<>("levelName"));
        mapSizeCol.setCellValueFactory(new PropertyValueFactory<>("gridCount"));
        daleksCountCol.setCellValueFactory(new PropertyValueFactory<>("daleksCount"));

        levelNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        mapSizeCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        daleksCountCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        levelTable.setItems(levels);

        levelTable.getSelectionModel().clearSelection();
        onSelectionChanged();
        onInputChanged();
    }

    @FXML
    private void onSelectionChanged() {
        GameMapSettings selectedLevel = levelTable.getSelectionModel().getSelectedItem();
        if (selectedLevel != null) {
            removeButton.setDisable(false);
            playButton.setDisable(false);
        } else {
            removeButton.setDisable(true);
            playButton.setDisable(true);
        }
    }

    @FXML
    private void onInputChanged() {

        filteredLevels = levels.filtered(l -> true);

        try{
            int mapSize = Integer.parseInt(mapSizeInput.getText());
            filteredLevels = filteredLevels.filtered(l -> l.getGridCount() == mapSize);
        } catch (NumberFormatException ignored) {}

        try{
            int daleksCount = Integer.parseInt(daleksCountInput.getText());
            filteredLevels = filteredLevels.filtered(l -> l.getDaleksCount() == daleksCount);
        } catch (NumberFormatException ignored) {}

        filteredLevels = filteredLevels.filtered(l -> l.getLevelName().contains(levelNameInput.getText()));

        this.levelTable.setItems(filteredLevels);

        boolean isInputValid =
                !levelNameInput.getText().isBlank() &&
                        !mapSizeInput.getText().isBlank() &&
                        !daleksCountInput.getText().isBlank();
        addButton.setDisable(
                !filteredLevels.isEmpty() && !isInputValid
        );

        addButton.setText(filteredLevels.isEmpty() && !isInputValid ? "Generate" : "Add");

    }

    @FXML
    private void addButtonClicked() {

        GameMapSettings newLevel = new GameMapSettings(
                Integer.parseInt(mapSizeInput.getText()),
                Integer.parseInt(daleksCountInput.getText()),
                levelNameInput.getText()
        );
        //TODO: Jakaś prosta walidacja by się przydała

        JsonResult response = serviceManager.UploadLevel(newLevel);
        if(response.Code != 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while uploading level");
            alert.setContentText(response.Description);
            alert.showAndWait();
        } else {
            levels.add(newLevel);
            levelNameInput.clear();
            mapSizeInput.clear();
            daleksCountInput.clear();
        }


        this.onInputChanged();

    }

    @FXML
    private void removeButtonClicked() {
        GameMapSettings selectedLevel = levelTable.getSelectionModel().getSelectedItem();

        JsonResult response = serviceManager.DeleteLevel(selectedLevel.getLevelName());
        if(response.Code != 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while removing level");
            alert.setContentText(response.Description);
            alert.showAndWait();
        } else {
            levels.remove(selectedLevel);
        }
        levelTable.getSelectionModel().clearSelection();
        onInputChanged();
    }
    @FXML
    public void backButtonClicked() {
        ViewManager.SetScene(Settings.MainView);
    }

    @FXML
    public void playButtonClicked() {
        GameMapSettings selectedLevel = levelTable.getSelectionModel().getSelectedItem();
        DaleksApp.GetMainController().startGame(selectedLevel);
    }

    @Override
    public String GetViewPath() {
        return Settings.LevelSelectView;
    }
}
