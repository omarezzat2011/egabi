package com.omarezzat.university.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    int level;
    String nationalId;
    @ManyToOne
    @JoinColumn( name = "faculty_id")
    Faculty faculty;

}
