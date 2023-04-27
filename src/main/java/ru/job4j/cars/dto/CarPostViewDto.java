package ru.job4j.cars.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarPostViewDto {


    /**
     * DTO для модели Post
     */
    private int postId;
    private String postDescription;
    private LocalDateTime postCreated;
    private String postPrice;
    private boolean postStatus;
    private byte[] postPhoto;

    /**
     * DTO для модели Car и Engine
     */

    private int carId;
    private String carName;
    private String carEngine;
    private String carBody;
    private String carTransmission;

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
                + ", carEngineName='" + carEngine + '\''
                + ", userName='" + userName + '\''
                + ", userId=" + userId + '}';
    }
}
