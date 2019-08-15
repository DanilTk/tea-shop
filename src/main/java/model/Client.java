package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
public class Client {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDateTime creationTimestamp;
    private LocalDateTime deletionTimestamp;

    public Client(Integer id,
                  String firstName,
                  String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Client(String firstName,
                  String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.creationTimestamp = LocalDateTime.now();
    }
}