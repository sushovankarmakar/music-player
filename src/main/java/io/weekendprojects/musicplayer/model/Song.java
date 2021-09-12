package io.weekendprojects.musicplayer.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Song {

  @Id
  private Long id;

  private String filename;

  private String title;
  private String artist;

  private boolean isFavorite;

}
