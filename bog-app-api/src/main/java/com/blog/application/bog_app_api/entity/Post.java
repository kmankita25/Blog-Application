package com.blog.application.bog_app_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @Column(name = "post_title",length = 100,nullable = false)
    private String title;
    @Column(length = 10000)
    private String content;
    private String imageName;
    private Date addedDate;
    @ManyToOne
    @JoinColumn(name = "category_Id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)

    private Set<Comment>comments=new HashSet<>();
}
