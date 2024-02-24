package hanhae.inky0.springnewsfeed.controller;

import hanhae.inky0.springnewsfeed.dto.CustomUserDetails;
import hanhae.inky0.springnewsfeed.service.ArticleLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles/{articleId}/articlelikes")
public class ArticleLikesController {
    private final ArticleLikesService articleLikesService;

    @PostMapping
    public ResponseEntity<Void> upDownLike(@PathVariable("articleId")Long articleId,
                                           @AuthenticationPrincipal CustomUserDetails userDetails ) {

        //게시물id랑 사용자 추가
        articleLikesService.upDownLike(articleId,userDetails);
        return ResponseEntity.ok().build();
    }
}
