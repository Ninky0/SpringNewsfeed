package hanhae.inky0.springnewsfeed.repository;

import hanhae.inky0.springnewsfeed.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}