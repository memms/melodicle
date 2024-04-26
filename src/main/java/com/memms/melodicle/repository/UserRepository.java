package com.memms.melodicle.repository;

import com.memms.melodicle.domain.entities.ArtistEntity;
import com.memms.melodicle.domain.entities.UserEntity;
import com.memms.melodicle.domain.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
