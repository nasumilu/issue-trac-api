package io.nasumilu.issuetrac.repository;


import io.nasumilu.issuetrac.entity.Category;
import io.nasumilu.issuetrac.entity.Issue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class IssueRepositoryTest {

    private static final Point shape;

    static {
        shape = (new GeometryFactory(new PrecisionModel(), 4269)).createPoint(new Coordinate());
    }

    @Autowired
    private IssueRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @After
    public void cleanUp() {
        this.deleteHistory();
        this.repository.deleteAll();
        this.categoryRepository.deleteAll();
    }

    public void deleteHistory() {
        this.entityManager.createNativeQuery("delete from issue_disposition_history").executeUpdate();
    }


    @Test
    public void testInsertIssue() {
        var issue = (Issue) (new Issue()).setTitle("Issue Title")
                .setDescription("Test Issue Description")
                .setGeoid("12001")
                .setCategory((new Category()).setName("Test Issue Category").setGeoid("12001"))
                .setShape(shape)
                .setSubject(UUID.randomUUID());

        assertNull(issue.getId());
        assertNull(issue.getCategory().getId());
        this.repository.save(issue);
        assertNotNull(issue.getId());
        assertNotNull(issue.getCategory().getId());

    }

    @Test
    public void testUpdateIssue() {
        var issue = (Issue) (new Issue()).setTitle("Issue Title")
                .setDescription("Test Issue Description")
                .setGeoid("12001")
                .setCategory((new Category()).setName("Test Issue Category").setGeoid("12001"))
                .setShape(shape)
                .setSubject(UUID.randomUUID());

        this.repository.save(issue);
        assertEquals(0.0, issue.getShape().getCoordinate().x, 0.001);
        var expected = -85.123;
        issue.getShape().getCoordinate().setX(expected);
        this.repository.save(issue);
        issue = this.repository.findById(issue.getId()).orElseThrow();
        assertEquals(expected, issue.getShape().getCoordinate().x, 0.001);
    }

}
