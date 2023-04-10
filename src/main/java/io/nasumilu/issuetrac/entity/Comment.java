package io.nasumilu.issuetrac.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "issue_comment")
public class Comment extends AbstractSubjectable {


    @ManyToOne(targetEntity = Issue.class, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "issue", nullable = false)
    private Issue issue;

    @Lob
    @Column(name = "comment", columnDefinition = "text")
    private String value;

    public String getValue() {
        return this.value;
    }

    public Comment setValue(String value) {
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

}