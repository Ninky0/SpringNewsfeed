package hanhae.inky0.springnewsfeed.repository;

import hanhae.inky0.springnewsfeed.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByArticleId(Long articleId);
}
