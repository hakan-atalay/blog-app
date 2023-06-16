package com.anproject.BlogApp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roleId;

    @OneToMany(mappedBy = "userId")
    private List<News> news = new ArrayList<>();

    @OneToMany(mappedBy = "userId")
    private List<Paraphrase> paraphrases = new ArrayList<>();

    @OneToMany(mappedBy = "userId")
    private List<Approval> approvals = new ArrayList<>();

    @OneToMany(mappedBy = "userId")
    private List<Slider> sliders = new ArrayList<>();
}
