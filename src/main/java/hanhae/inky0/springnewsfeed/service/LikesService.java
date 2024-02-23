package hanhae.inky0.springnewsfeed.service;


import hanhae.inky0.springnewsfeed.dto.CustomUserDetails;
import hanhae.inky0.springnewsfeed.entity.Article;
import hanhae.inky0.springnewsfeed.entity.Likes;
import hanhae.inky0.springnewsfeed.entity.UserEntity;
import hanhae.inky0.springnewsfeed.repository.ArticleRepository;
import hanhae.inky0.springnewsfeed.repository.LikesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class LikesService {
    private final LikesRepository likesRepository;
    private final ArticleRepository articleRepository;

    public void upDownLike(Long articleId, CustomUserDetails userDetails) {

        UserEntity user = userDetails.getUserEntity();
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다"));

        if (!likesRepository.existsByUserIdAndArticle(user.getId(), article)) {
            // 호출되면 article에 있는 count 증가
            article.setLikeCount(article.getLikeCount()+1);
            // likesRepository에 memberId 값이랑 boardId값 저장해버림
            likesRepository.save(new Likes(user, article));
        }
        else {
            article.setLikeCount(article.getLikeCount()-1);
            likesRepository.deleteByUserIdAndArticle(user.getId(),article);
        }
        //boardRepository.save(board); //@Transactional 사용하니깐 더티 체킹으로 필요없어짐
    }
}
