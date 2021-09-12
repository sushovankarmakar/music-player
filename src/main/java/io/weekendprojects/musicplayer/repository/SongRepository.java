package io.weekendprojects.musicplayer.repository;

import io.weekendprojects.musicplayer.model.Song;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends MongoRepository<Song, Long> {

}
