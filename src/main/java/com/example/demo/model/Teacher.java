package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nauczyciele")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "imie")
    @NotBlank(message = "Imię jest wymagane")
    @Size(min = 2, max = 50)
    private String firstName;

    @Column(name = "nazwisko")
    @NotBlank(message = "Nazwisko jest wymagane")
    @Size(min = 2, max = 50)
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subject> subjects = new ArrayList<>();
}
