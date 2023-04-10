package io.nasumilu.issuetrac.entity;

import jakarta.persistence.*;

import java.util.UUID;

/**
 * Base class for all things {@link Subjectable}.
 */
@MappedSuperclass
public abstract class AbstractSubjectable implements Subjectable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="sub", nullable = false)
    private UUID subject;

    public Long getId() {
        return id;
    }

    public Subjectable setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public UUID getSubject() {
        return this.subject;
    }

    public Subjectable setSubject(UUID subject) {
        this.subject = subject;
        return this;
    }

    public Subjectable setSubject(String subject) {
        return this.setSubject(UUID.fromString(subject));
    }

}
