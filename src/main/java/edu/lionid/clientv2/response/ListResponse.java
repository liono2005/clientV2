package edu.lionid.clientv2.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListResponse <T> extends BaseResponse{
    private List<T> data;

    public ListResponse(boolean success, String message, List <T> data) {
        super(success, message);
        this.data = data;
    }
}
