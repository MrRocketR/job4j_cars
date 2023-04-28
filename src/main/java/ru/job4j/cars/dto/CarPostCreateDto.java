package ru.job4j.cars.dto;


import lombok.*;

import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarPostCreateDto {


    /**
     * DTO для модели Post
     */
    private String postDescription;
    private String postPrice;
    private byte[] postPhoto;

    /**
     * DTO для модели Car и Engine
     */

    private String carName;
    private String carEngine;
    private String carBody;
    private String carTransmission;

    /**
     * DTO для модели User
     */

    private int userId;

}
