package ru.job4j.cars.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cars")
@EqualsAndHashCode
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @OneToOne(mappedBy = "car", fetch = FetchType.LAZY)
    private Post post;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", engine=" + engine.getName()
                + ", post=" + post.getDescription() + '}';
    }
}
