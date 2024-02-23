package hanhae.inky0.springnewsfeed.controller;

import hanhae.inky0.springnewsfeed.dto.*;
import hanhae.inky0.springnewsfeed.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article/{articleId}/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(
            @PathVariable Long articleId,
            @RequestBody CommentCreateRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
            ){

        commentService.createComment(articleId, request, userDetails);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    //@AuthenticationPrincipal : 토큰 가져오자
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ){

        commentService.deleteComment(commentId, userDetails);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentCreateRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails ){

        commentService.updateComment(commentId, request, userDetails);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CommentResponse>> readCommentList(@PathVariable Long articleId) {
        List<CommentResponse> commentResponses = commentService.readCommentList(articleId);

        return ResponseEntity.ok()
                .body(commentResponses);
    }

}
