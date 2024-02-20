package hanhae.inky0.springnewsfeed.controller;

import hanhae.inky0.springnewsfeed.dto.ArticleCreateRequest;
import hanhae.inky0.springnewsfeed.dto.ArticleResponse;
import hanhae.inky0.springnewsfeed.dto.CustomUserDetails;
import hanhae.inky0.springnewsfeed.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<Void> createArticle(@RequestBody ArticleCreateRequest request, @AuthenticationPrincipal CustomUserDetails userDetails){

        articleService.createArticle(request, userDetails);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    //@AuthenticationPrincipal : 토큰 가져오자
    public ResponseEntity<Void> deleteArticle(@PathVariable Long productId, @AuthenticationPrincipal CustomUserDetails userDetails ){

        articleService.deleteArticle(productId, userDetails);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateArticle(
            @PathVariable Long productId,
            @RequestBody ArticleCreateRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails ){

        articleService.updateArticle(productId, request, userDetails);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ArticleResponse> readArticle(@PathVariable Long productId){

        ArticleResponse articleResponse = articleService.readArticle(productId);

        return ResponseEntity.ok()
                .body(articleResponse);
    }

    @GetMapping
    public ResponseEntity<List<ArticleResponse>> readArticleList() {
        List<ArticleResponse> articleResponses = articleService.readArticleList();

        return ResponseEntity.ok()
                .body(articleResponses);
    }
}
