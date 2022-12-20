package com.sparta.skilledproject.comment.entity;

import com.sparta.skilledproject.comment.dto.CreateCommentDto;
import com.sparta.skilledproject.comment.dto.UpdateCommentDto;
import com.sparta.skilledproject.common.entity.BaseEntity;
import com.sparta.skilledproject.post.entity.Post;
import com.sparta.skilledproject.user.entity.User;
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
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(CreateCommentDto createCommentDto, User user , Post post) {
        this.content = createCommentDto.getContent();
        this.user = user;
        this.post = post;
    }

    public void updateComment(UpdateCommentDto requestUpdateCommentDto) {
        this.content = requestUpdateCommentDto.getContent();
    }
}
