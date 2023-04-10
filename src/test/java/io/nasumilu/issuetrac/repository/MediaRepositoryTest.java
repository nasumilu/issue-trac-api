package io.nasumilu.issuetrac.repository;

import io.nasumilu.issuetrac.entity.Category;
import io.nasumilu.issuetrac.entity.Issue;
import io.nasumilu.issuetrac.entity.Media;
import io.nasumilu.issuetrac.entity.MediaTest;
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

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MediaRepositoryTest {

    private static final Point shape;

    static {
        shape = (new GeometryFactory(new PrecisionModel(), 4269)).createPoint(new Coordinate());
    }

    @Autowired
    private MediaRepository repository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @After
    public void cleanUp() {
        //this.repository.deleteAll();
        //this.issueRepository.deleteAll();
        //this.categoryRepository.deleteAll();
    }

    @Test
    public void testInsertIssueMedia() throws IOException {
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

        var media = (Media) (new Media())
                .setIssue(issue)
                .setValue(path)
                .setSubject(sub);

        assertNull(issue.getId());
        assertNull(media.getId());
        this.repository.save(media);
        assertNotNull(issue.getId());
        assertNotNull(media.getId());
    }
}
