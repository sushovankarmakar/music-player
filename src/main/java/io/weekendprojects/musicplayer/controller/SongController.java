package io.weekendprojects.musicplayer.controller;

import io.weekendprojects.musicplayer.service.StorageService;
import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/music-player")
public class SongController {

  private final StorageService storageService;

  @PostMapping("/songs")
  public ResponseEntity<String> uploadSong(@RequestParam("file") MultipartFile file) throws IOException {

    String responseMessage = storageService.uploadSongFile(file);

    return ResponseEntity.ok(responseMessage);
  }

  @GetMapping("/songs")
  public ResponseEntity<List<String>> getAllSongs() {
    return ResponseEntity.ok(storageService.getSongFileNames());
  }

}
