package com.nasya.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String slug;
    @Column(name = "created_at")
    private BigInteger createdAt;

    @Column(name = "updated_at")
    private BigInteger updatedAt;

    private boolean isDeleted;

    @OneToMany(mappedBy = "category", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Post> post;
}
