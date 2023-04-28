package ru.job4j.cars.service.dtoservices;


import org.springframework.stereotype.Service;

import ru.job4j.cars.dto.CarPostUpdateDto;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import ru.job4j.cars.model.enums.Body;
import ru.job4j.cars.model.enums.Engine;
import ru.job4j.cars.model.enums.EnumMapper;
import ru.job4j.cars.model.enums.Transmission;
import ru.job4j.cars.service.CarService;
import ru.job4j.cars.service.PostService;
import ru.job4j.cars.service.UserService;

import java.util.Map;

@Service
public class DtoUpdateService {
    private final CarService carService;
    private final PostService postService;
    private final UserService userService;
    private final EnumMapper enumMapper;

    public DtoUpdateService(CarService carService,
                            PostService postService,
                            UserService userService,
                            EnumMapper enumMapper) {
        this.carService = carService;
        this.postService = postService;
        this.userService = userService;
        this.enumMapper = enumMapper;
    }

    private Post conventToPost(CarPostUpdateDto dto) {
        Map<String, Boolean> statuses =
                Map.of("Продано", Boolean.TRUE, "На продаже", Boolean.FALSE);
        Post post = Post.builder()
                .description(dto.getPostDescription())
                .photo(dto.getPostPhoto())
                .price(dto.getPostPrice())
                .status(statuses.get(dto.getPostStatus()))
                .id(dto.getPostId())
                .build();
        return post;
    }


    private User conventToUser(CarPostUpdateDto dto) {
        User user = userService.findById(dto.getUserId()).get();
        return user;

    }

    private Car conventToCar(CarPostUpdateDto dto) {
        Engine engine = enumMapper.getEngine(dto.getCarEngine());
        Transmission transmission = enumMapper.getTransmission(dto.getCarTransmission());
        Body body = enumMapper.getBody(dto.getCarBody());
        Car car = Car.builder()
                .name(dto.getCarName())
                .body(body)
                .engine(engine)
                .transmission(transmission)
                .id(dto.getCarId())
                .build();
        return car;

    }

    public void update(CarPostUpdateDto dto) {
        Post post = conventToPost(dto);
        Car car = conventToCar(dto);
        carService.updateCar(car, car.getId());
        postService.updatePost(post, post.getId());
    }

}
