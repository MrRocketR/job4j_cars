package ru.job4j.cars.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "AUTO_USER")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Post> postList = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" + "id=" + id
                + ", login='" + login + '\''
                + ", password='" + password
                + '\'' + ", postList="
                + postList + '}';
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
