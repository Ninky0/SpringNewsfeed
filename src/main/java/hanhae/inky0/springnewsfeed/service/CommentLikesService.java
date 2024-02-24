package hanhae.inky0.springnewsfeed.service;

import hanhae.inky0.springnewsfeed.dto.CustomUserDetails;
import hanhae.inky0.springnewsfeed.entity.*;
import hanhae.inky0.springnewsfeed.repository.ArticleRepository;
import hanhae.inky0.springnewsfeed.repository.CommentLikesRepository;
import hanhae.inky0.springnewsfeed.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentLikesService {
    private final CommentLikesRepository commentLikesRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public void upDownLike(Long articleId, CustomUserDetails userDetails, Long commentId) {

        UserEntity user = userDetails.getUserEntity();
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다."));

        if (!commentLikesRepository.existsByUserIdAndArticleAndComment(user.getId(), article, comment)) {
            // 호출되면 article에 있는 count 증가
            comment.setLikeCount(comment.getLikeCount()+1);
            // articlelikesRepository에 memberId 값이랑 boardId값 저장해버림
            commentLikesRepository.save(new CommentLikes(user, article, comment));
        }
        else {
            comment.setLikeCount(comment.getLikeCount()-1);
            commentLikesRepository.deleteByUserIdAndArticleAndComment(user.getId(),article, comment);
        }
        //boardRepository.save(board); //@Transactional 사용하니깐 더티 체킹으로 필요없어짐
    }
}
