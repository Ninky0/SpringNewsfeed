package hanhae.inky0.springnewsfeed.repository;

import hanhae.inky0.springnewsfeed.dto.CustomUserDetails;
import hanhae.inky0.springnewsfeed.entity.Article;
import hanhae.inky0.springnewsfeed.entity.Likes;
import hanhae.inky0.springnewsfeed.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    //좋아요 누른 사람 목록에 있는지 없는지 검토
    boolean existsByUserIdAndArticle(Long userId, Article article);
    //삭제
    void deleteByUserIdAndArticle(Long userId, Article article);
}
