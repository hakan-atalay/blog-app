package com.anproject.BlogApp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(columnDefinition = "TEXT", name = "contents")
    private String contents;
    @Column(name = "photos")
    private String photos;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "news")
    private List<Paraphrase> paraphrases = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "categories_news",
            joinColumns = @JoinColumn(name = "news_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "news")
    private List<Approval> approvals = new ArrayList<>();

    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;
        return "/news-photos/" + id + "/" + photos;
    }

}
