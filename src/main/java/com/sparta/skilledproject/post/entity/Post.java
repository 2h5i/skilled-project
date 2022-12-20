package com.sparta.skilledproject.post.entity;

import com.sparta.skilledproject.comment.entity.Comment;
import com.sparta.skilledproject.common.entity.BaseEntity;
import com.sparta.skilledproject.post.dto.CreatePostDto;
import com.sparta.skilledproject.post.dto.UpdatePostDto;
import com.sparta.skilledproject.user.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @OneToMany(mappedBy = "post",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public Post(CreatePostDto createPostDto, User user) {
        this.title = createPostDto.getTitle();
        this.contents = createPostDto.getContents();
        this.user = user;
    }

    public void update(UpdatePostDto updatePostDto) {
        this.title = updatePostDto.getTitle();
        this.contents = updatePostDto.getContents();
    }

}
