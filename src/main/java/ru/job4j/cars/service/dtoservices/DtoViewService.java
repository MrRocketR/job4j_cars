package ru.job4j.cars.service.dtoservices;

import org.springframework.stereotype.Service;
import ru.job4j.cars.dto.CarPostViewDto;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.CarService;
import ru.job4j.cars.service.PostService;
import ru.job4j.cars.service.UserService;


import java.util.List;

@Service

public class DtoViewService {

    private final PostService postService;
    private final UserService userService;
    private final CarService carService;

    public DtoViewService(PostService postService, UserService userService, CarService carService) {
        this.postService = postService;
        this.userService = userService;
        this.carService = carService;
    }

    private CarPostViewDto convertToDto(Post post) {
        Car car = post.getCar();
        User user = post.getUser();
        CarPostViewDto dto = CarPostViewDto.builder()
                .postId(post.getId())
                .postDescription(post.getDescription())
                .postCreated(post.getCreated())
                .postPhoto(post.getPhoto())
                .postStatus(post.isStatus())
                .postPrice(post.getPrice())
                .carName(car.getName())
                .carBody(car.getBody().getBody())
                .carTransmission(car.getTransmission().getTransmission())
                .carEngine(car.getEngine().getEngine())
                .carId(car.getId())
                .userName(user.getLogin())
                .userId(user.getId())
                .build();
        return dto;
    }


    public List<CarPostViewDto> showAllPosts() {
        List<Post> postList = postService.showAllPosts();
        return postList.stream()
                .map(this::convertToDto).toList();
    }

    public List<CarPostViewDto> showActualPosts() {
        List<Post> postList = postService.showAllPosts();
        List<CarPostViewDto> dtoList = postList.stream().
                filter(post -> !post.isStatus())
               .map(this::convertToDto).toList();
        return dtoList;
    }

    public List<CarPostViewDto> showMyPostsByUserEntity(User user) {
        List<CarPostViewDto> dtoList = user.getPostList().stream()
                .map(this::convertToDto).toList();
        return dtoList;
    }


    public CarPostViewDto showPostById(int postId) {
        Post post = postService.findById(postId).get();
        CarPostViewDto dto = convertToDto(post);
        return dto;
    }

}
