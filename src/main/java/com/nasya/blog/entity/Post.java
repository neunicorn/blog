package com.nasya.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.math.BigInteger;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String body;

    @Column(unique = true)
    private String slug;

    @Column(name = "is_published")
    private boolean isPublished;

    @Column(name= "is_deleted")
    private boolean isDeleted;

    @Column(name = "created_at")
    private BigInteger createdAt;

    @Column(name = "updated_at")
    private BigInteger updatedAt;

    @Column(name = "published_at")
    private BigInteger publishedAt;

    @Column(name="comment_count")
    private Integer commentCount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post", orphanRemoval = true)
    @JsonIgnore
    private List<Comment> comments;
}
