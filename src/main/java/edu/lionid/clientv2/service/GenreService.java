package edu.lionid.clientv2.service;

import com.google.gson.reflect.TypeToken;
import edu.lionid.clientv2.entity.GenreEntity;
import edu.lionid.clientv2.response.BaseResponse;
import edu.lionid.clientv2.response.DataResponse;
import edu.lionid.clientv2.response.ListResponse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.lang.reflect.Type;
public class GenreService {
    @Getter
    private ObservableList<GenreEntity> data = FXCollections.observableArrayList();
    private final HTTPService http = new HTTPService();
    JSONService service = new JSONService();
    ClientProperties properties = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<GenreEntity>>() {
    }.getType();
    private Type listType = new TypeToken<ListResponse<GenreEntity>>() {
    }.getType();

    public void getAll() {
        ListResponse<GenreEntity> data = new ListResponse<>();
        data = service.getObject(http.get(properties.getAllGenre()), listType);
        if (data.isSuccess()) {
            this.data.addAll(data.getData());
            this.data.forEach(System.out::println);
        } else {
            throw new RuntimeException(data.getMessage());
        }
    }

    public void add(GenreEntity data) {
        String temp = http.post(properties.getSaveGenre(), service.getJson(data));
        DataResponse<GenreEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.remove(data);
            this.data.add(response.getData());
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void update(GenreEntity data, GenreEntity selectionElement) {
        String temp = http.put(properties.getUpdateGenre(), service.getJson(data));
        DataResponse<GenreEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());

        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void delete(GenreEntity data) {
        String temp = http.delete(properties.getDeleteGenre(), data.getId());
        DataResponse<GenreEntity> response = service.getObject(temp, BaseResponse.class);
        if (response.isSuccess()) {
            this.data.remove(data);
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void findById(GenreEntity data) {
        String temp = http.get(properties.getFindByIdGenre()) + data.getId();
        DataResponse<GenreEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }
}