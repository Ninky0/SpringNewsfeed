package hanhae.inky0.springnewsfeed.dto;

import hanhae.inky0.springnewsfeed.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ArticleCreateRequest {
    private String title;

    private String content;

    private LocalDateTime createdAt;

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
