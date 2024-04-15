package edu.lionid.clientv2.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublisherEntity {
    private Long id;
    private String title;
    private CityEntity city;
    private List<BookEntity> books;

    @Override
    public String toString() {
        return title;
    }
}
