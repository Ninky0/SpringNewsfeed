package hanhae.inky0.springnewsfeed.repository;

import hanhae.inky0.springnewsfeed.entity.Article;
import hanhae.inky0.springnewsfeed.entity.Comment;
import hanhae.inky0.springnewsfeed.entity.CommentLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {
    //좋아요 누른 사람 목록에 있는지 없는지 검토
    boolean existsByUserIdAndArticleAndComment(Long userId, Article article, Comment comment);
    //삭제
    void deleteByUserIdAndArticleAndComment(Long userId,Article article, Comment comment);
}
