package io.nasumilu.issuetrac.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "media")
public class Media {

    public enum Mime {
        IMAGE_JPEG("image/jpeg"),
        IMAGE_PNG("image/png"),
        IMAGE_WEBP("image/webp");

        private final String VALUE;
        Mime(String value) {
            this.VALUE = value;
        }

        public String getValue() {
            return this.VALUE;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "mime", nullable = false)
    private Mime mime;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "issue", nullable = false)
    private Issue issue;

    public Mime getMime() {
        return mime;
    }

    public void setMime(Mime mime) {
        this.mime = mime;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}