package ru.job4j.cars.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.cars.model.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarPostDto {


    /**
     * DTO для модели Post
     */
    private int postId;

    private String postDescription;
    private LocalDateTime postCreated;
    private String postPrice;
    private String postStatus;
    private byte[] postPhoto;

    /**
     * DTO для модели Car и Engine
     */

    private int carId;
    private String carName;
    private String carEngineName;

    /**
     * DTO для модели User
     */

    private String userName;
    private int userId;


    @Override
    public String toString() {
        return "CarPostDto{"
                + "postId= " + postId + '\''
                + "carId= " + carId + '\''
                + ", postDescription='" + postDescription + '\''
                + ", postPrice=" + postPrice
                + ", postStatus='" + postStatus + '\''
                + ", postPhoto=" + postPhoto.length
                + ", carName='" + carName + '\''
                + ", carEngineName='" + carEngineName + '\''
                + ", userName='" + userName + '\''
                + ", userId=" + userId + '}';
    }
}
