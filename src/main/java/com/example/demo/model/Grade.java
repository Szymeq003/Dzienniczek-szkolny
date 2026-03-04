package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "oceny")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wartosc")
    @NotNull(message = "Wartość oceny jest wymagana")
    @Min(value = 1, message = "Ocena minimalna to 1")
    @Max(value = 6, message = "Ocena maksymalna to 6")
    private Integer value;

    @Column(name = "data")
    private LocalDate date = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "uczen_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "przedmiot_id")
    private Subject subject;
}
