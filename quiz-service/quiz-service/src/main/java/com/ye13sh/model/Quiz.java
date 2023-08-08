package com.ye13sh.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data

public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

//    @ElementCollection
    private List<Integer> questionIds; // It creates different table in quiz database to store question-service data

}
