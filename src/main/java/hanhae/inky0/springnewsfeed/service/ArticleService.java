package hanhae.inky0.springnewsfeed.service;

import hanhae.inky0.springnewsfeed.dto.ArticleCreateRequest;
import hanhae.inky0.springnewsfeed.dto.ArticleResponse;
import hanhae.inky0.springnewsfeed.dto.CustomUserDetails;
import hanhae.inky0.springnewsfeed.entity.Article;
import hanhae.inky0.springnewsfeed.entity.UserEntity;
import hanhae.inky0.springnewsfeed.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j // 오류날때 디버깅할때 로그찍어보자ㅏ
public class ArticleService {
    private final ArticleRepository articleRepository;

    public void createArticle(ArticleCreateRequest request, CustomUserDetails userDetails){
        UserEntity user = userDetails.getUserEntity();
        log.info("ArticleService : {} ", user.toString());
        Article article = Article.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(user)
                .build();

        articleRepository.save(article);
    }

    public void deleteArticle(Long articleId, CustomUserDetails userDetails){
        UserEntity user = userDetails.getUserEntity();
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException());

        //권한 확인
        checkPermission(article, user);

        //Todo: 게시글 연관관계 모두 삭제(사진, 좋아요, 댓글..)

        //게시글 삭제
        articleRepository.delete(article);
    }

    public void updateArticle(
            Long articleId,
            ArticleCreateRequest request,
            CustomUserDetails userDetails){
        UserEntity user = userDetails.getUserEntity();
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException());

        //권한 확인
        checkPermission(article, user);

        article.update(request.getTitle(), request.getContent());
        articleRepository.save(article);
    }

    public ArticleResponse readArticle(Long articleId){

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException());

        return new ArticleResponse(article);

    }

    public List<ArticleResponse> readArticleList() {
        // 게시글 목록 조회
        List<Article> articles = articleRepository.findAll();

        // 게시글 목록을 ArticleResponse DTO로 매핑
        List<ArticleResponse> articleResponses = articles.stream()
                .map(ArticleResponse::new)
                .collect(Collectors.toList());

        return articleResponses;
    }

    private void checkPermission(Article article, UserEntity user){

        if( !(article.isOwner(user) || user.isAdmin()) ){
            throw new IllegalStateException("해당 게시글에 권한이 없습니다.");
        }

    }
}
