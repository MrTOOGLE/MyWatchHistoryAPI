package com.mywatchhistoryapi.entity;

import jakarta.persistence.*;

import java.util.Arrays;

/**
 * Сущность для фильмов/сериалов
 */
@Entity
@Table(name = "media_content")
public class TV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "content_type")
    private ContentType contentType;

    @Column(name = "title")
    private String title;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "my_rating")
    private Double myRating;

    @Column(name = "critics_rating")
    private Double criticsRating;

    @Lob
    @Column(name = "image_data")
    private byte[] imageData;

    public TV() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Double getMyRating() {
        return myRating;
    }

    public void setMyRating(Double myRating) {
        this.myRating = myRating;
    }

    public Double getCriticsRating() {
        return criticsRating;
    }

    public void setCriticsRating(Double criticsRating) {
        this.criticsRating = criticsRating;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    @Override
    public String toString() {
        return "TV{" +
                "id=" + id +
                ", contentType=" + contentType +
                ", title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", myRating=" + myRating +
                ", criticsRating=" + criticsRating +
                ", imageData=" + Arrays.toString(imageData) +
                '}';
    }
}
