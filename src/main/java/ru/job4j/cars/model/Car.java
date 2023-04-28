package ru.job4j.cars.model;

import lombok.*;
import ru.job4j.cars.model.enums.Body;
import ru.job4j.cars.model.enums.Engine;
import ru.job4j.cars.model.enums.Transmission;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cars")
@EqualsAndHashCode
@Setter
@Getter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;


    @OneToOne(mappedBy = "car", fetch = FetchType.LAZY)
    private Post post;

    @Enumerated(EnumType.STRING)
    @Column(name = "engine_type")
    private Engine engine;

    @Enumerated(EnumType.STRING)
    @Column(name = "transmission_type")
    private Transmission transmission;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_type")
    private Body body;


}
