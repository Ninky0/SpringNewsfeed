package hanhae.inky0.springnewsfeed.controller;

import hanhae.inky0.springnewsfeed.dto.CustomUserDetails;
import hanhae.inky0.springnewsfeed.service.CommentLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles/{articleId}/commentlikes/{commentId}")
public class CommentLikesController {
    private final CommentLikesService commentLikesService;

    @PostMapping
    public ResponseEntity<Void> upDownLike(@PathVariable("articleId")Long articleId,
                                           @AuthenticationPrincipal CustomUserDetails userDetails,
                                           @PathVariable("commentId")Long commentId) {

        //게시물id랑 사용자 추가
        commentLikesService.upDownLike(articleId,userDetails,commentId);
        return ResponseEntity.ok().build();
    }
}
