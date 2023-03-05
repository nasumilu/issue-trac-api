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

    public void setName(String name) {
        this.name = name;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean hasParent() {
        return null != this.parent;
    }

    public Optional<Category> getParent() {
        return Optional.ofNullable(parent);
    }

    public void setParent(Category category) {
        this.parent = category;
    }

    public Category setGeoid(String geoid) {
        this.geoid = geoid;
        return this;
    }

    @Override
    public String getGeoid() { return this.geoid; }
}