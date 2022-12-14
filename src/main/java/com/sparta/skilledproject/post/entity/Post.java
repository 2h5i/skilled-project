package com.sparta.skilledproject.post.entity;

import com.sparta.skilledproject.post.dto.CreatePostDto;
import com.sparta.skilledproject.post.dto.UpdatePostDto;
import com.sparta.skilledproject.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    public Post(CreatePostDto createPostDto, User user) {
        this.title = createPostDto.getTitle();
        this.writer = createPostDto.getWriter();
        this.contents = createPostDto.getContents();
        this.password = createPostDto.getPassword();
        this.user = user;
    }

    public boolean isCorrectPassword(String password) {
        return this.password.equals(password);
    }

    public void update(UpdatePostDto updatePostDto) {
        this.title = updatePostDto.getTitle();
        this.writer = updatePostDto.getWriter();
        this.contents = updatePostDto.getContents();
    }

}
