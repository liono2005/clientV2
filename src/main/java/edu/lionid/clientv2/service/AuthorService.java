package edu.lionid.clientv2.service;

import com.google.gson.reflect.TypeToken;
import edu.lionid.clientv2.entity.AuthorEntity;
import edu.lionid.clientv2.response.BaseResponse;
import edu.lionid.clientv2.response.DataResponse;
import edu.lionid.clientv2.response.ListResponse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.Comparator;

public class AuthorService {
    @Getter
    private ObservableList<AuthorEntity> data = FXCollections.observableArrayList();
    private final HTTPService http = new HTTPService();
    JSONService service = new JSONService();
    ClientProperties properties = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<AuthorEntity>>() {

    }.getType(); //фиксируем тип DataResponce<BookEntity>

    private Type listType = new TypeToken<ListResponse<AuthorEntity>>() {

    }.getType(); //фиксируем тип DataResponce<BookEntity>

    public void getAll(){
        ListResponse<AuthorEntity> data=new ListResponse<>();
        data = service.getObject(http.get(properties.getAllAuthor()),listType);
        if (data.isSuccess()){
            this.data.addAll(data.getData());
            this.data.forEach(System.out::println);
        } else {
            throw new RuntimeException(data.getMessage());
        }
    }

    public void add(AuthorEntity data){
        String temp = http.post(properties.getSaveAuthor(), service.getJson(data));
        DataResponse<AuthorEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()){
            this.data.add(response.getData());

        }else{
            throw new RuntimeException(response.getMessage());
        }
    }
    private void sort() {
        data.sort(Comparator.comparing(AuthorEntity::getLastname));
    }
    public void update (AuthorEntity after, AuthorEntity before){
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(properties.getUpdateAuthor(),service.getJson(after));
        DataResponse<AuthorEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()){
            this.data.remove(before);
            this.data.add(after);
            sort();
        }else{
            throw new RuntimeException(response.getMessage());
        }
    }

    public void delete(AuthorEntity data){
        String temp = http.delete(properties.getDeleteAuthor(), data.getId());
        BaseResponse response = service.getObject(temp,BaseResponse.class);
        if (response.isSuccess()){
            this.data.remove(data);
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }


    public void findById(AuthorEntity data){
        String temp = http.get(properties.getFindByIdAuthor() + data.getId());
        DataResponse<AuthorEntity> respose = service.getObject(temp, dataType);
        if (respose.isSuccess()){
            this.data.add(respose.getData());

        }else{
            throw new RuntimeException(respose.getMessage());
        }
    }




}