package com.memms.melodicle.repository;

import com.memms.melodicle.domain.entities.ArtistEntity;
import com.memms.melodicle.domain.entities.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, Long> {
    
}
