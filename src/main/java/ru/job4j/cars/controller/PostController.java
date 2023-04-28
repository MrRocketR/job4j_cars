package ru.job4j.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.dto.CarPostCreateDto;
import ru.job4j.cars.dto.CarPostUpdateDto;
import ru.job4j.cars.dto.CarPostViewDto;
import ru.job4j.cars.model.User;
import ru.job4j.cars.model.enums.Body;
import ru.job4j.cars.model.enums.Engine;
import ru.job4j.cars.model.enums.Transmission;
import ru.job4j.cars.service.dtoservices.DtoCreateService;
import ru.job4j.cars.service.dtoservices.DtoUpdateService;
import ru.job4j.cars.service.dtoservices.DtoViewService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/posts")
public class PostController {

    private final DtoViewService dtoViewService;
    private final DtoCreateService dtoCreateService;
    private final DtoUpdateService dtoUpdateService;

    public PostController(DtoViewService dtoViewService,
                          DtoCreateService dtoCreateService,
                          DtoUpdateService dtoUpdateService) {
        this.dtoViewService = dtoViewService;
        this.dtoCreateService = dtoCreateService;
        this.dtoUpdateService = dtoUpdateService;
    }


    @GetMapping("All")
    public String postsPageAll(Model model) {
        List<CarPostViewDto> dtoList = dtoViewService.showAllPosts();
        model.addAttribute("dtoList", dtoList);
        return "posts/list";
    }



    @GetMapping("ByNew")
    public String postsPageWithNew(Model model) {
        List<CarPostViewDto> dtoList = dtoViewService.showActualPosts();
        model.addAttribute("dtoList", dtoList);
        return "posts/list";
    }


    @GetMapping("myPosts")
    public String myPostsPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<CarPostViewDto> dtoList = dtoViewService.showMyPostsByUser(user.getId());
        model.addAttribute("dtoList", dtoList);
        return "posts/myPosts";
    }


    @GetMapping("createPostPage")
    public String createPostPage(Model model) {
        List<String> engines = Arrays.stream(Engine.values())
                .map(Engine::getEngine).toList();
        model.addAttribute("engines", engines);
        List<String> bodies = Arrays.stream(Body.values())
                .map(Body::getBody).toList();
        model.addAttribute("bodies", bodies);
        List<String> transmission = Arrays.stream(Transmission.values())
                .map(Transmission::getTransmission).toList();
        model.addAttribute("transmissions", transmission);
        return "posts/createPost";
    }


    @PostMapping("createPost")
    public String createPost(@ModelAttribute("dto") CarPostCreateDto carPostCreateDto,
                             @RequestParam("file") MultipartFile file,
                             HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        carPostCreateDto.setPostPhoto(file.getBytes());
        carPostCreateDto.setUserId(user.getId());
        dtoCreateService.createPost(carPostCreateDto);
        return "redirect:/posts/All";
    }

    @GetMapping("updatePost/{postId}")
    public String updatePostPage(Model model, @PathVariable("postId") Integer postId,
                                 HttpSession session) {
        List<String> engines = Arrays.stream(Engine.values())
                .map(Engine::getEngine).toList();
        model.addAttribute("engines", engines);
        List<String> bodies = Arrays.stream(Body.values())
                .map(Body::getBody).toList();
        model.addAttribute("bodies", bodies);
        List<String> transmission = Arrays.stream(Transmission.values())
                .map(Transmission::getTransmission).toList();
        model.addAttribute("transmissions", transmission);
        CarPostViewDto viewDto = dtoViewService.showPostById(postId);
        Integer carId = viewDto.getCarId();
        session.setAttribute("carId", carId);
        model.addAttribute("viewDto", viewDto);
        session.setAttribute("postId", postId);
        Map<Boolean, String> statuses =
                Map.of(Boolean.TRUE, "Продано", Boolean.FALSE, "На продаже");
        model.addAttribute("statuses", statuses.values());
        return "posts/updatePost";
    }


    @PostMapping("updateAction")
    public String updatePost(@ModelAttribute("dto") CarPostUpdateDto carPostUpdateDto,
                             @RequestParam("file") MultipartFile file,
                             HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        Integer postId = (Integer) session.getAttribute("postId");
        Integer carId = (Integer) session.getAttribute("carId");
        carPostUpdateDto.setUserId(user.getId());
        carPostUpdateDto.setPostPhoto(file.getBytes());
        carPostUpdateDto.setPostId(postId);
        carPostUpdateDto.setCarId(carId);
        dtoUpdateService.update(carPostUpdateDto);
        return "redirect:/posts/myPosts";
    }

}