package io.nasumilu.issuetrac.entity;

import io.nasumilu.issuetrac.repository.IssueRepository;
import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class IssueTest {

    @Test
    public void testGetSetDisposition() {
        var issue = new Issue();
        assertEquals(Issue.Disposition.NEW, issue.getDisposition());
        assertSame(Issue.Disposition.COMPLETED, issue.setDisposition(Issue.Disposition.COMPLETED).getDisposition());
    }

    @Test
    public void testGetSetDescription() {
        var issue = new Issue();
        assertNull(issue.getDescription());
        var expected = "My Issue Description";
        assertEquals(expected, issue.setDescription(expected).getDescription());
    }

    @Test
    public void testGetSetCategory() {
        var issue = new Issue();
        var category = new Category();
        assertNull(issue.getCategory());
        assertSame(category, issue.setCategory(category).getCategory());
    }

    @Test
    public void testGetSetPoint() {
        var issue = new Issue();
        var point = (new GeometryFactory(new PrecisionModel(), 4326)).createPoint(new Coordinate());
        assertNull(issue.getShape());
        assertSame(point, issue.setShape(point).getShape());
    }

    @Test
    public void testGetSetTitle() {
        var issue = new Issue();
        var title = "Big issue today";
        assertNull(issue.getTitle());
        assertEquals(title, issue.setTitle(title).getTitle());
    }


}
