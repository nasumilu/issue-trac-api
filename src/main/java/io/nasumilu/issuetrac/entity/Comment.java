package io.nasumilu.issuetrac.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "issue_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(targetEntity = Issue.class, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "issue", nullable = false)
    private Issue issue;

    @Lob
    @Column(name = "comment")
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String value;

    public String getValue() {
        return this.value;
    }

    public Comment setComment(String value) {
        this.value = value;
        return this;
    }

    public Issue getIssue() {
        return issue;
    }

    public Comment setIssue(Issue issue) {
        this.issue = issue;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}