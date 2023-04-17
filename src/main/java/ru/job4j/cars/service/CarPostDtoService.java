package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.dto.CarPostDto;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarPostDtoService {

    private final PostService postService;
    private final UserService userService;
    private final EngineService engineService;
    private final CarService carService;


    public CarPostDtoService(PostService postService, UserService userService, EngineService engineService, CarService carService) {
        this.postService = postService;
        this.userService = userService;
        this.engineService = engineService;
        this.carService = carService;
    }

    public void createPost(Post post) {
        postService.createPost(post);
    }

    public void saveToDbFromDto(CarPostDto dto) {
        Engine engine = engineService.getEngineByName(dto.getCarEngineName());
        User user = userService.findById(dto.getUserId()).get();
        Car car = Car.builder().name(dto.getCarName()).engine(engine)
                .build();
        Post post = Post.builder().description(dto.getPostDescription())
                .price(dto.getPostPrice()).photo(dto.getPostPhoto()).car(car)
                .status("на продажу").created(LocalDateTime.now())
                .user(user).build();
        postService.createPost(post);
    }


    public void updatePost(CarPostDto dto, int postId) {
        Post post = Post.builder().description(dto.getPostDescription())
                .id(postId)
                .price(dto.getPostPrice())
                .status(dto.getPostStatus())
                .photo(dto.getPostPhoto()).build();
        Engine engine = engineService.getEngineByName(dto.getCarEngineName());
        Car car = Car.builder()
                .id(dto.getCarId())
                .name(dto.getCarName())
                .engine(engine)
                .post(post).build();
        post.setCar(car);
        car.setPost(post);
        carService.updateCar(car, car.getId());
        postService.updatePost(post, dto.getPostId());
    }

    private CarPostDto buildDto(Post post) {
        CarPostDto dto = new CarPostDto().builder()
                .postId(post.getId())
                .postDescription(post.getDescription())
                .postCreated(post.getCreated())
                .postPrice(post.getPrice())
                .postStatus(post.getStatus())
                .postPhoto(post.getPhoto())
                .carId(post.getCar().getId())
                .carName(post.getCar().getName())
                .carEngineName(post.getCar().getEngine().getName())
                .userName(post.getUser().getLogin())
                .userId(post.getUser().getId())
                .build();
        return dto;
    }


    public List<Post> testedPost() {
        List<Post> postList = postService.showAllPosts();
        return postList;
    }


    public List<CarPostDto> showAllPosts() {
        List<Post> postList = postService.showAllPosts();
        List<CarPostDto> dtoList = postList.stream().
                map(post -> buildDto(post)).collect(Collectors.toList());
        return dtoList;
    }

    public List<CarPostDto> showActualPosts() {
        List<Post> postList = postService.showAllPosts();
        List<CarPostDto> dtoList = postList.stream().
                filter(post -> post.getStatus().equalsIgnoreCase("на продаже")).
                map(post -> buildDto(post)).collect(Collectors.toList());
        return dtoList;
    }


    public List<CarPostDto> showMyPostsByUserEntity(User user) {
        List<CarPostDto> dtoList = user.getPostList().stream().
                map(post -> buildDto(post)).collect(Collectors.toList());
        return dtoList;
    }


    public CarPostDto showPostById(int postId) {
        Post post = postService.findById(postId).get();
        CarPostDto dto = buildDto(post);
        return dto;
    }


}
