package ru.job4j.cars.service.dtoservices;


import org.springframework.stereotype.Service;
import ru.job4j.cars.dto.CarPostCreateDto;
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

@Service
public class CreateService {

    private final EnumMapper enumMapper;

    private final PostService postService;
    private final CarService carService;
    private final UserService userService;

    public CreateService(EnumMapper enumMapper,
                         PostService postService,
                         CarService carService,
                         UserService userService) {
        this.enumMapper = enumMapper;
        this.postService = postService;
        this.carService = carService;
        this.userService = userService;
    }


    private Post conventToPost(CarPostCreateDto dto) {
        Post post = Post.builder()
                .description(dto.getPostDescription())
                .photo(dto.getPostPhoto())
                .price(dto.getPostPrice())
                .build();
        return post;
    }


    private User conventToUser(CarPostCreateDto dto) {
        User user = userService.findById(dto.getUserId()).get();
        return user;

    }

    private Car conventToCar(CarPostCreateDto dto) {
        Engine engine = enumMapper.getEngine(dto.getCarEngine());
        Transmission transmission = enumMapper.getTransmission(dto.getCarTransmission());
        Body body = enumMapper.getBody(dto.getCarBody());
        Car car = Car.builder()
                .name(dto.getCarName())
                .body(body)
                .engine(engine)
                .transmission(transmission)
                .build();
        return car;

    }

    public void createPost(CarPostCreateDto dto) {
        Post post = conventToPost(dto);
        Car car = conventToCar(dto);
        User user = conventToUser(dto);
        post.setCar(car);
        post.setUser(user);
        postService.createPost(post);
    }

}
