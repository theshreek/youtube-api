package com.example.practice.favourite;

import com.example.practice.entities.User;
import com.example.practice.exceptions.CustomMessage;
import com.example.practice.video.Video;
import com.example.practice.video.VideoDTO;
import com.example.practice.video.VideoRepository;
import com.example.practice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FavouriteService {
    private UserRepository userRepository;
    private VideoRepository videoRepository;
    private FavouriteRepository favouriteRepository;

    public ResponseEntity<?> getAllVideos() {
        List<Video> videos = videoRepository.findAll();
        Collection<Favourite> favourites = favouriteRepository.findAllByUser(userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get());
        List<VideoDTO> videoDTOS = new ArrayList<>();
        for (Video video : videos) {
            VideoDTO videoDTO = new VideoDTO();
            videoDTO.setVideoId(video.getVideoId());
            videoDTO.setSelected(false);
            for (Favourite favourite : favourites) {
                if (favourite.getVideo().getVideoId().equals(video.getVideoId())) {
                    videoDTO.setSelected(true);
                    break;
                }
            }
            videoDTOS.add(videoDTO);
        }
        return ResponseEntity.ok().body(videoDTOS);
    }

    public ResponseEntity<?> getAllFavourite() {
        List<Video> videos = videoRepository.findAll();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).get();

        Collection<Favourite> favourites = favouriteRepository.findAllByUser(user);
        List<VideoDTO> videoDTOS = new ArrayList<>();

        for (Video video : videos) {
            VideoDTO videoDTO = new VideoDTO();
            videoDTO.setVideoId(videoDTO.getVideoId());
            videoDTO.setSelected(false);
            for (Favourite favourite : favourites) {
                if (favourite.getVideo().getVideoId().equals(video.getVideoId())) {
                    videoDTO.setSelected(true);
                    break;
                }
                videoDTOS.add(videoDTO);
            }
        }
        List<VideoDTO> videoDTOS1 = videoDTOS.stream().filter(VideoDTO::isSelected).toList();
        return ResponseEntity.ok().body(videoDTOS1);
    }

    public ResponseEntity<?> videoFavourite(VideoDTO videoDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).get();
        Optional<Video> videoOptional = videoRepository.findById(videoDTO.getVideoId());
        if (videoOptional.isEmpty())
            return ResponseEntity.badRequest().body(new CustomMessage("Video not found !!!"));
        Collection<Favourite> favourites = favouriteRepository.findAllByUser(user);
        if (videoDTO.isSelected()) {
            for (Favourite favourite : favourites) {
                if (favourite.getVideo().getVideoId().equals(videoDTO.getVideoId())) {
                    return ResponseEntity.ok().body(new CustomMessage("Video favourite already exists"));
                }
            }
            Favourite favourite = new Favourite();
            favourite.setUser(user);
            favourite.setVideo(videoOptional.get());
            favouriteRepository.save(favourite);
            return ResponseEntity.ok().body(new CustomMessage("Video favourite added"));
        } else {
            for (Favourite favourite : favourites) {
                if (favourite.getVideo().getVideoId().equals(videoDTO.getVideoId())) {
                    favouriteRepository.delete(favourite);
                    return ResponseEntity.ok().body(new CustomMessage("Video favourite deleted"));
                }
            }
        }
        return ResponseEntity.ok().body(new CustomMessage("Video favourite not found"));
    }
}