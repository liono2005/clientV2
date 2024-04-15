package edu.lionid.clientv2.controller;

import edu.lionid.clientv2.entity.CityEntity;
import edu.lionid.clientv2.entity.PublisherEntity;
import edu.lionid.clientv2.service.AlertService;
import edu.lionid.clientv2.service.CityService;
import edu.lionid.clientv2.service.PublisherService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddPublisherController {

    private final CityService cityService = new CityService();
    private final PublisherService publisherService = new PublisherService();
    private boolean addFlag = true;

    AlertService alertService = new AlertService();

    @FXML
    private ComboBox<CityEntity> ComboBoxCity;

    @FXML
    private ListView<PublisherEntity> dataList;

    @FXML
    private TextField textTitle;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button deleteButton;


    // Actions
    @FXML
    private void initialize() {
        cityService.getAll();
        publisherService.getAll();
        dataList.setItems(publisherService.getData());
        ComboBoxCity.setItems(cityService.getData());
    }

    @FXML
    void addAction(ActionEvent event) {
        try {
            PublisherEntity publisher = new PublisherEntity();
            publisher.setTitle(textTitle.getText());
            publisher.setCity(ComboBoxCity.getSelectionModel().getSelectedItem());
            if (addFlag) {
                publisherService.add(publisher);
            } else {
                publisher.setId(getSelectionElement().getId());
                publisherService.update(publisher, getSelectionElement());
            }
            textTitle.clear();
        } catch (Exception exception) {
            alertService.addVoid(exception);
        }
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void cancelAction(ActionEvent event) {
        addFlag = true;
    }

    @FXML
    void deleteAction(ActionEvent event) {
        try {
            publisherService.delete(getSelectionElement());
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
                PublisherEntity temp = getSelectionElement();
                textTitle.setText(temp.getTitle());
                ComboBoxCity.getSelectionModel().select(temp.getCity());
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

    private PublisherEntity getSelectionElement() {
        PublisherEntity temp = dataList.getSelectionModel().getSelectedItem();
        return temp;
    }
}