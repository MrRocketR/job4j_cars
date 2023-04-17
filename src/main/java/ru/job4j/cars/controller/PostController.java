package ru.job4j.cars.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.dto.CarPostDto;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class PostController {

    private final CarService carService;
    private final PostService postService;
    private final CarPostDtoService carPostDtoService;
    private final EngineService engineService;
    private final UserService userService;

    public PostController(CarService carService, PostService postService, CarPostDtoService carPostDtoService, EngineService engineService, UserService userService) {
        this.carService = carService;
        this.postService = postService;
        this.carPostDtoService = carPostDtoService;
        this.engineService = engineService;
        this.userService = userService;
    }


    @GetMapping("/postsAll")
    public String postsPageAll(Model model) {
        List<CarPostDto> dtoList = carPostDtoService.showAllPosts();
        model.addAttribute("dtoList", dtoList);
        return "posts";
    }


    @GetMapping("/postPhoto/{postId}")
    public ResponseEntity<Resource> download(@PathVariable("postId") Integer postId) {
        Post post = postService.findById(postId).get();
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(post.getPhoto().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(post.getPhoto()));
    }


    @GetMapping("/postsByNew")
    public String postsPageWithNew(Model model) {
        List<CarPostDto> dtoList = carPostDtoService.showActualPosts();
        model.addAttribute("dtoList", dtoList);
        return "posts";
    }


    @GetMapping("/myPosts")
    public String myPostsPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<CarPostDto> dtoList = carPostDtoService.showMyPostsByUserEntity(user);
        model.addAttribute("dtoList", dtoList);
        return "myPosts";
    }


    @GetMapping("/createPostPage")
    public String createPostPage(Model model) {
        List<Engine> engines = engineService.showEngines();
        model.addAttribute("engines", engines);
        return "createPost";
    }

    @PostMapping("/createPost")
    public String createPost(@RequestParam(name = "name-car") String nameCar,
                             @RequestParam("file") MultipartFile file,
                             @RequestParam(name = "name-engine") String nameEngine,
                             @RequestParam(name = "name-post") String namePost,
                             @RequestParam(name = "price") String price,
                             HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        CarPostDto dto = CarPostDto.builder().
                carName(nameCar).carEngineName(nameEngine)
                .postDescription(namePost)
                .postPrice(price)
                .userId(user.getId())
                .postPhoto(file.getBytes())
                .build();
        carPostDtoService.saveToDbFromDto(dto);
        return "redirect:/postsAll";
    }


    @GetMapping("/updatePost/{postId}")
    public String updatePostPage(Model model, @PathVariable("postId") Integer postId,
                                 HttpSession session) {
        List<Engine> engines = engineService.showEngines();
        List<String> statuses = new ArrayList<>(List.of("на продаже", "продано"));
        CarPostDto dto = carPostDtoService.showPostById(postId);
        Integer carId = dto.getCarId();
        model.addAttribute("dto", dto);
        model.addAttribute("engines", engines);
        model.addAttribute("statuses", statuses);
        session.setAttribute("postId", postId);
        session.setAttribute("carId", carId);
        return "updatePost";
    }


    @PostMapping("/update")
    public String updatePost(@RequestParam(name = "name-car") String nameCar,
                             @RequestParam("file") MultipartFile file,
                             @RequestParam(name = "name-engine") String nameEngine,
                             @RequestParam(name = "name-post") String namePost,
                             @RequestParam(name = "status-post") String statusPost,
                             @RequestParam(name = "price") String price,
                             HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        Integer postId = (Integer) session.getAttribute("postId");
        Integer carId = (Integer) session.getAttribute("carId");
        CarPostDto dto = CarPostDto.builder().
                postId(postId)
                .carId(carId)
                .carName(nameCar).carEngineName(nameEngine)
                .postDescription(namePost)
                .postPrice(price)
                .userId(user.getId())
                .postPhoto(file.getBytes())
                .postStatus(statusPost)
                .postPhoto(file.getBytes())
                .build();
        carPostDtoService.updatePost(dto, dto.getPostId());
        return "redirect:/myPosts";
    }

}