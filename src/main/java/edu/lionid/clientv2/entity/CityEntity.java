package edu.lionid.clientv2.entity;


import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityEntity {
    private Long id;
    private String title;
    private List<PublisherEntity> publisher;

    @Override
    public String toString() {
        return title;
    }
}
