package ru.job4j.cars.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AUTO_POST")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private String description;
    private LocalDateTime created =  LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "auto_user_id")
    private User user;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private List<PriceHistory> priceHistoryList = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "subscription",
            joinColumns = { @JoinColumn(name = "s_post_id") },
            inverseJoinColumns = { @JoinColumn(name = "s_user_id") }
    )
    private List<User> subscriptions = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
