package io.nasumilu.issuetrac.repository;

import io.nasumilu.issuetrac.entity.*;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentRepositoryTest {


    private static final Point shape;

    static {
        shape = (new GeometryFactory(new PrecisionModel(), 4269)).createPoint(new Coordinate());
    }

    @Autowired
    private CommentRepository repository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @After
    public void cleanUp() {
        this.repository.deleteAll();
        this.issueRepository.deleteAll();
        this.categoryRepository.deleteAll();
    }

    @Test
    public void testInserIssueComment() {
        var sub = UUID.randomUUID();
        String path = Objects.requireNonNull(
                MediaTest.class.getClassLoader().getResource("media.png")
        ).getPath();

        var issue = (Issue) (new Issue()).setTitle("Issue Title")
                .setDescription("Test Issue Description")
                .setGeoid("12001")
                .setCategory((new Category()).setName("Test Issue Category").setGeoid("12001"))
                .setShape(shape)
                .setSubject(sub);

        var comment = (Comment) (new Comment()).setIssue(issue)
                .setValue("This is a long text value that should be stored as text in the database!")
                .setSubject(sub);

        assertNull(issue.getId());
        assertNull(comment.getId());
        this.repository.save(comment);
        assertNotNull(issue.getId());
        assertNotNull(comment.getId());
    }

}
