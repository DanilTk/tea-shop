package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
public class Client {
    private Long id;
    private String name;
    private String surname;
    private LocalDateTime creationTimestamp;
    private LocalDateTime deletionTimestamp;

    public Client(Long id,
                  String name,
                  String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Client(String name,
                  String surname) {

        this.name = name;
        this.surname = surname;
        this.creationTimestamp = LocalDateTime.now();
    }
}