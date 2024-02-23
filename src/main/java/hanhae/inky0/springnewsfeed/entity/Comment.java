package hanhae.inky0.springnewsfeed.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId") //사실 여기에 nullable="false"이 들어가야 함
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId")
    private Article article;

    @Builder
    public Comment(String content, UserEntity user, Article article) {
        this.content = content;
        this.user = user;
        this.article = article;
    }

    public void update(String content) {
        this.content = content;
    }

    public Boolean isOwner(UserEntity user){
        return this.user.getId().equals(user.getId());
    }

}
