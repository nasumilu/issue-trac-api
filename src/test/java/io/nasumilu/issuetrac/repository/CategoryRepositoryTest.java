package io.nasumilu.issuetrac.repository;

import io.nasumilu.issuetrac.entity.Category;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    @After
    public void cleanUp() {
        this.repository.deleteAll();
    }


    @Test
    public void testInsertCategoryTest() {
        var parent = (new Category()).setName("Test Parent Category")
                .setDescription("Test Parent Category Description")
                .setGeoid("12");
        var child = (new Category()).setName("Test Child Category")
                .setDescription("Test Child Category Description")
                .setGeoid("12001")
                .setParent(parent);
        this.repository.save(child);
        assertNotNull(child.getId());
        assertEquals(child.getParent().orElseThrow().getId(), parent.getId());
    }

    @Test
    public void testUpdateCategoryTest() {
        var category = (new Category()).setName("Test Category")
                .setDescription("Silly Description")
                .setGeoid("12001");
        this.repository.save(category);

        assertNotNull(category.getId());
        assertNotNull(category.getDescription());
        // find and update
        category = this.repository.findById(category.getId()).orElseThrow();
        category.setDescription(null);
        this.repository.save(category);
        assertNull(category.getDescription());
    }

}
