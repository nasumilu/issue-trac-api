package io.nasumilu.issuetrac.entity;

import jakarta.persistence.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Entity
@Table(name = "issue_media")
public class Media extends AbstractSubjectable {

    @Column(name = "mime_type", nullable = false)
    private String mime;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "issue", nullable = false)
    private Issue issue;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image", nullable = false)
    private byte[] value;

    public byte[] getValue() {
        return value;
    }

    public Media setValue(byte[] value) {
        this.value = value;
        return this;
    }

    public Media setValue(String path) throws IOException, IllegalArgumentException {
        return this.setValue(Paths.get(path));
    }

    public Media setValue(Path path) throws IOException, IllegalArgumentException {
        var mime = Files.probeContentType(path);
        this.setMimeType(mime);
        return this.setValue(Files.readAllBytes(path));
    }

    public String getMimeType() {
        return mime;
    }

    public Media setMimeType(String mime) {
        this.mime = mime;
        return this;
    }

    public Issue getIssue() {
        return issue;
    }

    public Media setIssue(Issue issue) {
        this.issue = issue;
        return this;
    }

}