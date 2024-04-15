package edu.lionid.clientv2.service;

import com.google.gson.reflect.TypeToken;
import edu.lionid.clientv2.entity.BookEntity;
import edu.lionid.clientv2.response.BaseResponse;
import edu.lionid.clientv2.response.DataResponse;
import edu.lionid.clientv2.response.ListResponse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.lang.reflect.Type;

public class BookService {
    @Getter
    private ObservableList<BookEntity> data = FXCollections.observableArrayList();
    private final HTTPService http = new HTTPService();
    JSONService service = new JSONService();
    ClientProperties properties = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<BookEntity>>() {

    }.getType(); //фиксируем тип DataResponse<BookEntity>

    private Type listType = new TypeToken<ListResponse<BookEntity>>() {

    }.getType(); //фиксируем тип DataResponse<BookEntity>

    public void getAll() {
        ListResponse<BookEntity> data = new ListResponse<>();
        data = service.getObject(http.get(properties.getAllBook()), listType);
        if (data.isSuccess()) {
            this.data.addAll(data.getData());
            this.data.forEach(System.out::println);
        } else {
            throw new RuntimeException(data.getMessage());
        }
    }

    public void add(BookEntity data){
        String temp = http.post(properties.getSaveBook(), service.getJson(data));
        DataResponse<BookEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()){
            this.data.add(response.getData());

        }else{
            throw new RuntimeException(response.getMessage());
        }
    }


    public void update(BookEntity after, BookEntity before){
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(properties.getUpdateBook(), service.getJson(after));
        DataResponse<BookEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()){
            this.data.remove(before);
            this.data.add(after);
        }else{
            throw new RuntimeException(response.getMessage());
        }
    }

    public void delete(BookEntity data){
        String temp = http.delete(properties.getDeleteBook(), data.getId());
        BaseResponse response = service.getObject(temp,BaseResponse.class);
        if (response.isSuccess()){
            this.data.remove(data);
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }


    public void findById(BookEntity data){
        String temp = http.get(properties.getFindByIdBook() + data.getId());
        DataResponse<BookEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()){
            this.data.add(response.getData());

        }else{
            throw new RuntimeException(response.getMessage());
        }
    }



}