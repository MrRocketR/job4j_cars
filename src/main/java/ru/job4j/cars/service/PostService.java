package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> showAllPosts() {
        return postRepository.showAllPosts();
    }

    public void createPost(Post post) {
  //      post.setStatus("на продаже");
        post.setCreated(LocalDateTime.now());
        postRepository.createPost(post);
    }



    public void updatePost(Post post, int id) {
        postRepository.updatePost(post, id);
    }

    public Optional<Post> findById(int id) {
       return postRepository.findById(id);
    }

    public void delete(int id) {
        postRepository.delete(id);
    }


    public List<Post> showNewPosts() {
     return postRepository.showNewPostsByTime();
    }

}
