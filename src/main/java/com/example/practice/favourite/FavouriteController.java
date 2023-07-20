package com.example.practice.favourite;

import com.example.practice.video.VideoDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/videoFavourite")
@AllArgsConstructor
public class FavouriteController {
    private FavouriteService favouriteService;
    @GetMapping("/getAllVideos")
    public ResponseEntity<?> getAllVideos() {
        return favouriteService.getAllVideos();
    }
    @GetMapping("/videoFavourite")
    public ResponseEntity<?> videoFavourite(@RequestBody @Validated VideoDTO videoDTO) {
        return favouriteService.videoFavourite(videoDTO);
    }
}