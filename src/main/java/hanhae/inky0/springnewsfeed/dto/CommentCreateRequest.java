package hanhae.inky0.springnewsfeed.dto;

import hanhae.inky0.springnewsfeed.entity.Article;
import hanhae.inky0.springnewsfeed.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentCreateRequest {
    private String content;

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
