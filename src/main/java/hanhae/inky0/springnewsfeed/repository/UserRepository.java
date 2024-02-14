package hanhae.inky0.springnewsfeed.repository;

import hanhae.inky0.springnewsfeed.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Boolean existsByUsername(String username);

    UserEntity findByUsername(String username);
}
