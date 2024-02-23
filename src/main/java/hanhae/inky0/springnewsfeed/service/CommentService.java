package hanhae.inky0.springnewsfeed.service;

import hanhae.inky0.springnewsfeed.dto.CommentCreateRequest;
import hanhae.inky0.springnewsfeed.dto.CustomUserDetails;
import hanhae.inky0.springnewsfeed.entity.Article;
import hanhae.inky0.springnewsfeed.entity.Comment;
import hanhae.inky0.springnewsfeed.entity.UserEntity;
import hanhae.inky0.springnewsfeed.repository.ArticleRepository;
import hanhae.inky0.springnewsfeed.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public void createComment(
            Long articleId,
            CommentCreateRequest request,
            CustomUserDetails userDetails){

        UserEntity user = userDetails.getUserEntity();

        //Id를 통해 레파지토리에서 엔티티를 가져오는 방법
        // orElseThrow는 id에 해당하는 엔티티가 없는 경우 예외처리
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다"));


        log.info("CommentService : {} ", user.toString(), article.toString());
        Comment comment = Comment.builder()
                .content(request.getContent())
                .article(article)
                .user(user)
                .build();

        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId, CustomUserDetails userDetails){
        UserEntity user = userDetails.getUserEntity();

        //1. 댓글 가져오기
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글 없음"));

        //2. 권한 확인
        // 게시물 작성자와 댓글 작성자, 관리자만 수정 가능
        checkPermission(user, comment);

        //3. 댓글 삭제
        commentRepository.delete(comment);
    }

    public void updateComment(Long commentId, CommentCreateRequest request, CustomUserDetails userDetails) {
        UserEntity user = userDetails.getUserEntity();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글 없음"));

        //댓글 작성자와 관리자만 수정 가능
        checkPermission2(user, comment);

        comment.update(request.getContent());

        commentRepository.save(comment);

    }

    private void checkPermission(UserEntity user, Comment comment){
        Article article = comment.getArticle();
        if(!(comment.isOwner(user) || article.isOwner(user) || user.isAdmin())){
            throw new IllegalStateException("권한이 없습니다");
        }
    }

    private void checkPermission2(UserEntity user, Comment comment){
        Article article = comment.getArticle();
        if(!(comment.isOwner(user) || user.isAdmin())){
            throw new IllegalStateException("권한이 없습니다");
        }
    }

}
