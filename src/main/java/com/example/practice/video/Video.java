package com.example.practice.video;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoId;
    public Date publishedAt;
    public String channelId;
    public String title;
    public String description;
    //    public Thumbnails thumbnails;
    public String channelTitle;
    public String categoryId;
    public String liveBroadcastContent;
    public String defaultLanguage;
    //    public Localized localized;
    public String defaultAudioLanguage;
    public ArrayList<String> tags;
}
