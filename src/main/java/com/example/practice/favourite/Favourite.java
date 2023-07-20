package com.example.practice.favourite;

import com.example.practice.entities.User;
import com.example.practice.video.Video;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "favourite")
public class Favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favouriteId;
    @ManyToOne
    private User user;
    @ManyToOne
    private Video video;

}