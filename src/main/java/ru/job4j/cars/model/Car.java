package ru.job4j.cars.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @OneToOne(mappedBy = "car")
    private Post post;

    @ManyToMany
    @JoinTable(name = "history_owners",
            joinColumns = { @JoinColumn(name = "car_id") },
            inverseJoinColumns = { @JoinColumn(name = "driver_id") }
    )
    private List<Driver> drivers = new ArrayList<>();

}
