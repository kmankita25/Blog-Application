package com.blog.application.bog_app_api.serviceImpl;

import com.blog.application.bog_app_api.entity.Category;
import com.blog.application.bog_app_api.entity.Post;
import com.blog.application.bog_app_api.entity.User;
import com.blog.application.bog_app_api.exceptions.ResourceNotFoundException;
import com.blog.application.bog_app_api.payload.PostDto;
import com.blog.application.bog_app_api.payload.PostResponse;
import com.blog.application.bog_app_api.repositories.CategoryRepo;
import com.blog.application.bog_app_api.repositories.PostRepo;
import com.blog.application.bog_app_api.repositories.UserRepo;
import com.blog.application.bog_app_api.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override////19 video
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        Post post=this.modelMapper.map(postDto,Post.class);
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","user Id",userId));
        Category category=this.categoryRepo.findById((categoryId)).orElseThrow(()->new ResourceNotFoundException("category","category Id",categoryId));
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost=this.postRepo.save(post);
        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId",postId));
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setTitle(postDto.getTitle());
        Post updatedpost=this.postRepo.save(post);
        return this.modelMapper.map(updatedpost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
       Post post= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId",postId));
       this.postRepo.delete(post);

    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        Sort sort=null;
        if(sortDir.equalsIgnoreCase("asc"))
        {
            sort=Sort.by(sortBy).ascending();
        }
        else{
            Sort.by(sortBy).descending();
        }
        Pageable p= PageRequest.of(pageNumber,pageSize, sort);
        Page<Post> page=this.postRepo.findAll(p);
        List<Post> allPosts=page.getContent();
        List<PostDto>postDtos=allPosts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(page.getNumber());
        postResponse.setPageSize(page.getSize());
        postResponse.setTotalPages(page.getTotalPages());
        postResponse.setTotalElement((int) page.getTotalElements());
        postResponse.setLastPage(page.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId",postId));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","categoryId",categoryId));
        List<Post>posts=this.postRepo.findByCategory(category);
        List<PostDto>postDtos=posts.stream().map((post )-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {

        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","userId",userId));
        List<Post>posts=this.postRepo.findByUser(user);
        List<PostDto>postDtos=posts.stream().map((post )-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> postDtoList=this.postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtos=postDtoList.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
