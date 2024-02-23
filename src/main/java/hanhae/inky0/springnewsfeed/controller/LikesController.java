package hanhae.inky0.springnewsfeed.controller;

import hanhae.inky0.springnewsfeed.dto.CustomUserDetails;
import hanhae.inky0.springnewsfeed.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article/{articleId}/likes")
public class LikesController {
    private final LikesService likesService;

    @PostMapping
    public ResponseEntity<Void> upDownLike(@PathVariable("articleId")Long articleId,
                                           @AuthenticationPrincipal CustomUserDetails userDetails ) {

        //게시물id랑 사용자 추가
        likesService.upDownLike(articleId,userDetails);
        return ResponseEntity.ok().build();
    }
}
