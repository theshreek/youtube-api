package com.example.practice.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDTO {
    @NotNull(message = "Please select a playlist to toggle")
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
    @NotNull(message = "You must select a value for the playlist")
    private boolean isSelected;
}