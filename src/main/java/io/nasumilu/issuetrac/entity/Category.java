package io.nasumilu.issuetrac.entity;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "category")
public class Category implements GeoidAware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "geoid", length = 16, nullable = false)
    private String geoid;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "parent")
    private Category parent;

    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }

    public Category setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }

    public Optional<Category> getParent() {
        return Optional.ofNullable(parent);
    }

    public Category setParent(Category category) {
        this.parent = category;
        return this;
    }

    public Category setGeoid(String geoid) {
        this.geoid = geoid;
        return this;
    }

    @Override
    public String getGeoid() { return this.geoid; }
}