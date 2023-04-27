package ru.job4j.cars.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarPostUpdateDto {


    /**
     * DTO для модели Post
     */
    private int postId;
    private String postDescription;

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


    private int userId;


}