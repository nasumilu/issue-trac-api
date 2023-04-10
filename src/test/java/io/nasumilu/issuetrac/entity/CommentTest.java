package io.nasumilu.issuetrac.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class CommentTest {


    @Test
    public void testGetSetComment() {
        var comment = new Comment();
        var expected = "This is the expected comment!";
        assertEquals(expected, comment.setValue(expected).getValue());
    }

    @Test
    public void testGetSetIssue() {
        var issue = new Issue();
        var comment = new Comment();
        assertSame(issue, comment.setIssue(issue).getIssue());
    }
}
