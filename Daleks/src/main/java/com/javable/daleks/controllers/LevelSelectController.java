package com.javable.daleks.controllers;

import com.javable.daleks.DaleksApp;
import com.javable.daleks.Settings;
import com.javable.daleks.interfaces.IControllerFxmlBased;
import com.javable.daleks.logic.ViewManager;
import com.javable.daleks.models.ApiResponse;
import com.javable.daleks.models.Level;
import com.javable.daleks.logic.ServiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;

public class LevelSelectController implements IControllerFxmlBased{


    private ObservableList<Level> levels;
    private FilteredList<Level> filteredLevels;
    private final ServiceManager serviceManager;

    public LevelSelectController() {
        serviceManager = new ServiceManager();

        levels = FXCollections.observableArrayList(
                serviceManager.GetAllUserLevels()
        );

    }

    @FXML
    private TableView<Level> levelTable;
    @FXML
    private TableColumn<Level, String> levelNameCol;
    @FXML
    private TableColumn<Level, Integer>  mapSizeCol, daleksCountCol;
    @FXML
    private TextField levelNameInput, mapSizeInput, daleksCountInput;
    @FXML
    private Text errorText;
    @FXML
    private Button addButton, removeButton, playButton, backButton;
    @FXML
    public void initialize() {
        levelNameCol.setCellValueFactory(new PropertyValueFactory<>("LevelName"));
        mapSizeCol.setCellValueFactory(new PropertyValueFactory<>("GridCount"));
        daleksCountCol.setCellValueFactory(new PropertyValueFactory<>("GridCount"));

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
        Level selectedLevel = levelTable.getSelectionModel().getSelectedItem();
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
            filteredLevels = filteredLevels.filtered(l -> l.GridCount == mapSize);
        } catch (NumberFormatException ignored) {}

        // TODO implement filter
        /* try{
            int daleksCount = Integer.parseInt(daleksCountInput.getText());
            filteredLevels = filteredLevels.filtered(l -> l.getDaleksCount() == daleksCount);
        } catch (NumberFormatException ignored) {} */

        filteredLevels = filteredLevels.filtered(l -> l.LevelName.contains(levelNameInput.getText()));

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

        Level newLevel = new Level(
                Integer.parseInt(mapSizeInput.getText()),
                Integer.parseInt(daleksCountInput.getText()),
                levelNameInput.getText()
        );
        //TODO: Jakaś prosta walidacja by się przydała

        ApiResponse response = serviceManager.UploadLevel(newLevel);
        if(response.Code != 0){
            errorText.setText(response.Description);
        } else {
            errorText.setText("");
            levels.add(newLevel);
            levelNameInput.clear();
            mapSizeInput.clear();
            daleksCountInput.clear();
        }


        this.onInputChanged();

    }

    @FXML
    private void removeButtonClicked() {
        Level selectedLevel = levelTable.getSelectionModel().getSelectedItem();

        ApiResponse response = serviceManager.DeleteLevel(selectedLevel.LevelName);
        if(response.Code != 0){
            errorText.setText(response.Description);
        } else {
            errorText.setText("");
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
        Level selectedLevel = levelTable.getSelectionModel().getSelectedItem();
        DaleksApp.GetMainController().startGame(selectedLevel);
    }

    @Override
    public String GetViewPath() {
        return Settings.LevelSelectView;
    }
}
