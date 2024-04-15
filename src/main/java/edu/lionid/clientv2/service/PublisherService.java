package edu.lionid.clientv2.service;

import com.google.gson.reflect.TypeToken;
import edu.lionid.clientv2.entity.PublisherEntity;
import edu.lionid.clientv2.response.BaseResponse;
import edu.lionid.clientv2.response.DataResponse;
import edu.lionid.clientv2.response.ListResponse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.Comparator;

public class PublisherService {
    @Getter
    private ObservableList<PublisherEntity> data = FXCollections.observableArrayList();
    private final HTTPService http = new HTTPService();
    JSONService service = new JSONService();
    ClientProperties properties = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<PublisherEntity>>() {
    }.getType();
    private Type listType = new TypeToken<ListResponse<PublisherEntity>>() {
    }.getType();

    public void getAll() {
        ListResponse<PublisherEntity> data = new ListResponse<>();
        data = service.getObject(http.get(properties.getAllPublisher()), listType);
        if (data.isSuccess()) {
            this.data.addAll(data.getData());
            this.data.forEach(System.out::println);
        } else {
            throw new RuntimeException(data.getMessage());
        }
    }

    public void add(PublisherEntity data) {
        String temp = http.post(properties.getSavePublisher(), service.getJson(data));
        DataResponse<PublisherEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.remove(data);
            this.data.add(response.getData());
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void update(PublisherEntity after, PublisherEntity before) {
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(properties.getUpdatePublisher(), service.getJson(after));
        DataResponse<PublisherEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.remove(before);
            this.data.add(after);
            sort();
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    private void sort(){
        data.sort(Comparator.comparing(PublisherEntity::getTitle));
    }


    public void delete(PublisherEntity data) {
        String temp = http.delete(properties.getDeletePublisher(), data.getId());
        BaseResponse response = service.getObject(temp, BaseResponse.class);
        if (response.isSuccess()) {
            this.data.remove(data);
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void findById(PublisherEntity data) {
        String temp = http.get(properties.getFindByIdPublisher()) + data.getId();
        DataResponse<PublisherEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }
}