package edu.lionid.clientv2.controller;

import edu.lionid.clientv2.MainApplication;
import edu.lionid.clientv2.entity.BookEntity;
import edu.lionid.clientv2.service.AlertService;
import edu.lionid.clientv2.service.BookService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Optional;

public class MainController {

    BookService service = new BookService();
    AlertService alertService = new AlertService();
    private Optional<BookEntity> book = Optional.empty();

    public void setBook(Optional<BookEntity> book) {
        try {
            this.book = book;
            if (book.isPresent()) {
                if (book.get().getId() != null)
                    service.update(book.get(), bookTable.getSelectionModel().getSelectedItem());
                else service.add(book.get());
            }
        } catch (Exception e) {
            alertService.addVoid(e);
        }
    }

    @FXML
    private TableView<BookEntity> bookTable;

    @FXML
    private TableColumn<BookEntity, String> columnAuthor;

    @FXML
    private TableColumn<BookEntity, String> columnGenre;

    @FXML
    private TableColumn<BookEntity, String> columnNumber;

    @FXML
    private TableColumn<BookEntity, String> columnPublisher;

    @FXML
    private TableColumn<BookEntity, String> columnTitle;

    // Actions
    @FXML
    private void initialize() {
        //получаем все книги с сервера
        service.getAll();
        //связываем поля таблицы со столбцами
        columnAuthor.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("author"));
        columnGenre.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("genre"));
        columnNumber.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("year"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("title"));
        columnPublisher.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("publisher"));
        bookTable.setItems(service.getData());
    }

    @FXML
    void addBookAction(ActionEvent event) {
        Optional<BookEntity> book = Optional.empty();
        MainApplication.showBookDialog(book);
    }

    @FXML
    void addOrChangeAuthorAction(ActionEvent event) {
        MainApplication.showDialog("add-author-view.fxml", "Работа с авторами");
    }

    @FXML
    void addOrChangeCityAction(ActionEvent event) {
        MainApplication.showDialog("add-city-view.fxml", "Работа с городами");
    }

    @FXML
    void addOrChangeGenreAction(ActionEvent event) {
        MainApplication.showDialog("add-genre-view.fxml", "Работа с жанрами");

    }

    @FXML
    void addOrChangePublisherAction(ActionEvent event) {
        MainApplication.showDialog("add-publisher-view.fxml", "Работа с издательствами");
    }

    @FXML
    void changeBookAction(ActionEvent event){
        Optional<BookEntity> book = Optional.of(getSelectionElement());
        MainApplication.showBookDialog(book);
    }

    private BookEntity getSelectionElement(){
        BookEntity temp = bookTable.getSelectionModel().getSelectedItem();
        return temp;
    }

    @FXML
    void closeAction(ActionEvent event) {
    }

    @FXML
    void deleteAuthorAction(ActionEvent event) {
    }

    @FXML
    void deleteBookAction(ActionEvent event) {
        service.delete(bookTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    void deleteCityAction(ActionEvent event) {
    }

    @FXML
    void deleteGenreAction(ActionEvent event) {
    }

    @FXML
    void deletePublisherAction(ActionEvent event) {
    }


}