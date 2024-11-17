package com.blog.application.bog_app_api.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private Integer id;
    private String content;
}
