package hanhae.inky0.springnewsfeed.dto;

import hanhae.inky0.springnewsfeed.entity.Article;
import hanhae.inky0.springnewsfeed.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponse {

    private final String content;

    public CommentResponse(Comment comment) {
        this.content = comment.getContent();
    }
}
