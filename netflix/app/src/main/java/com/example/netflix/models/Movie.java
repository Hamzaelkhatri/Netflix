
/*
 * Create by Hamza elkhatri
 */

/*
 * Create by Hamza elkhatri
 */

package com.example.netflix.models;

public class Movie  {
    private String title;
    private String description;
    private String thumbnail;
    private String studio;
    private String rating;
    private String Cover;
    private int original=0;
    private int episode=0;
    private int season=0;
    private String title_ep;
    private String streamibgLink;

    public String getTitle_ep() {
        return title_ep;
    }

    public void setTitle_ep(String title_ep) {
        this.title_ep = title_ep;
    }

    public Movie(String title, String description, String thumbnail, String cover, String streamibgLink, int original, int episode, int season ,int time)
    {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        Cover = cover;
        this.original = original;
        this.episode = episode;
        this.season = season;
        this.streamibgLink = streamibgLink;
        this.time = time;
    }

    public Movie(String title, String description, String thumbnail, String cover, int original, int episode, int season, String streamibgLink, String title_ep)
    {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.Cover = cover;
        this.original = original;
        this.episode = episode;
        this.season = season;
        this.streamibgLink = streamibgLink;
        this.coverPhoto = coverPhoto;
        this.title_ep=title_ep;
    }

    private int time;

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(int coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public int getOriginal() {
        return original;
    }

    public void setOriginal(int original) {
        this.original = original;
    }

    public Movie(String title, String description, String thumbnail, String cover, String streamibgLink, int time, int original)
    {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        Cover = cover;
        this.streamibgLink = streamibgLink;
        this.time = time;
        this.original=original;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    int coverPhoto;

    public Movie(String title, String description, String thumbnail, String cover, String streamibgLink,int original)
    {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        Cover = cover;
        this.streamibgLink = streamibgLink;
        this.original=original;
    }

    public String getCover() {
        return Cover;
    }

    public void setCover(String cover) {
        Cover = cover;
    }


    public Movie(String title, String thumbnail, int coverPhoto) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.coverPhoto = coverPhoto;
    }

    public Movie(String title, String thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getStudio() {
        return studio;
    }

    public String getRating() {
        return rating;
    }

    public String getStreamibgLink() {
        return streamibgLink;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setStreamibgLink(String streamibgLink) {
        this.streamibgLink = streamibgLink;
    }
}
