package edu.lionid.clientv2.controller;

import edu.lionid.clientv2.entity.CityEntity;
import edu.lionid.clientv2.service.AlertService;
import edu.lionid.clientv2.service.CityService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

public class AddCityController {

    @Getter
    @Setter
    private Optional<CityEntity> city;
    AlertService alertService = new AlertService();
    CityService service = new CityService();
    private boolean addFlag = true;


    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ListView<CityEntity> dataList;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField textTitle;


    // Actions

    @FXML
    private void initialize() {
        service.getAll();
        dataList.setItems(service.getData());
    }

    private CityEntity getSelectionElement() {
        CityEntity temp = dataList.getSelectionModel().getSelectedItem();
        return temp;
    }

    @FXML
    void addAction(ActionEvent event) {
        try {
            CityEntity city = new CityEntity();
            city.setTitle(textTitle.getText());
            if (addFlag) {
                service.add(city);
            } else {
                city.setId(getSelectionElement().getId());
                service.update(city, getSelectionElement());
            }
            textTitle.clear();
        } catch (Exception exception) {
            alertService.addVoid(exception);
        }
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
        addButton.setText("Добавить");
    }

    @FXML
    void cancelAction(ActionEvent event) {
        addFlag = true;
    }

    @FXML
    void deleteAction(ActionEvent event) {
        try {
            service.delete(getSelectionElement());
            textTitle.clear();
        } catch (Exception exception) {
            alertService.deleteVoid(exception);
        }
    }

    @FXML
    void onMouseClickDataList(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (event.getClickCount() == 2) {
                addFlag = false;
                CityEntity temp = getSelectionElement();
                textTitle.setText(temp.getTitle());
                addButton.setText("Изменить");
            }
        }
    }

    @FXML
    void editModeHandler(MouseEvent event) {
        dataList.editableProperty().setValue(false);
        textTitle.clear();
        addButton.setText("Добавить");
    }
}
