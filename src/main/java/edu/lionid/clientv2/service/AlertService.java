package edu.lionid.clientv2.service;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.io.StringWriter;

public class AlertService extends Application {
    public void showError(Exception e, String whatMistakeStr) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка!");
        alert.setHeaderText(whatMistakeStr);

        VBox dialogPaneContent = new VBox();

        Label label = new Label("Ошибки:");

        String stackTrace = this.getStackTrace(e);
        TextArea textArea = new TextArea();
        textArea.setText(stackTrace);

        dialogPaneContent.getChildren().addAll(label, textArea);

        // Set content for Dialog Pane
        alert.getDialogPane().setContent(dialogPaneContent);

        alert.showAndWait();
    }

    public void didntStart(Exception e) {
        String whatMistakeStr = "Скорее всего, произошёл отвал сервера. Или кто-то не удосужился его запустить. Какой ужас.";
        showError(e, whatMistakeStr);
    }

    public void addVoid(Exception e) {
        String whatMistakeStr = "Зачем добавлять пустоту? Странно, но делать это я отказываюсь.";
        showError(e, whatMistakeStr);
    }

    public void deleteVoid(Exception e) {
        String whatMistakeStr = "Зачем удалять пустоту? Странно, но делать это я отказываюсь.";
        showError(e, whatMistakeStr);
    }

    private void showAlertWithHeaderText(String whatMistakeStr) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        alert.setX(bounds.getMaxX() - 800);
        alert.setY(bounds.getMaxY() - 650);
        alert.setTitle("Achtung!");
        alert.setHeaderText("Ошибка ввода.");
        alert.setContentText(whatMistakeStr);

        alert.showAndWait();
    }
    public void incorrectInput() {
        String whatMistakeStr = "\nВ полях со звездочкой данные должны начинаться с заглавной буквы, воспринимается только кириллица.";
        showAlertWithHeaderText(whatMistakeStr);
    }

    @Override
    public void start(Stage stage) {
        Button button1 = new Button("Показать ошибку");

        button1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

            }
        });

        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);

        root.getChildren().addAll(button1);

        Scene scene = new Scene(root, 450, 250);
        stage.setTitle("JavaFX Error Alert (o7planning.org)");
        stage.setScene(scene);

        stage.show();

    }

    public String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String s = sw.toString();
        return s;
    }
}