package io.nasumilu.issuetrac.entity;

import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.*;

public class MediaTest {

    @Test
    public void testGetSetIssue() {
        var issue = new Issue();
        var media = new Media();
        assertSame(issue, media.setIssue(issue).getIssue());
    }

    @Test
    public void testGetSetMimeType() {
        var media = new Media();
        var expected = "image/png";
        assertSame(expected, media.setMimeType(expected).getMimeType());
    }


    @Test
    public void testGetSetImageDataPng() throws IOException {
        String path = Objects.requireNonNull(
                MediaTest.class.getClassLoader().getResource("media.png")
        ).getPath();

        var media = new Media();
        media.setValue(path);
        assertNotNull(media.getValue());
        assertEquals("image/png", media.getMimeType());
    }


    @Test
    public void testGetSetImageDataJpg() throws IOException {
        String path = Objects.requireNonNull(
                MediaTest.class.getClassLoader().getResource("media.jpg")
        ).getPath();

        var media = new Media();
        media.setValue(path);
        assertNotNull(media.getValue());
        assertEquals("image/jpeg", media.getMimeType());
    }

}
