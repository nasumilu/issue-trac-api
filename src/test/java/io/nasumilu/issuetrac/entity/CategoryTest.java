package io.nasumilu.issuetrac.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {


    @Test
    public void testGetSetId() {
        var category = new Category();
        assertNull(category.getId());
        Long expected = 101L;
        assertEquals(expected, category.setId(expected).getId());
    }

    @Test
    public void testGetSetGeoid() {
        var category = new Category();
        assertNull(category.getGeoid());
        var expected = "12001";
        assertEquals(expected, category.setGeoid(expected).getGeoid());
    }

    @Test
    public void testGetSetName() {
        var category = new Category();
        assertNull(category.getName());
        var expected = "Test Category";
        assertEquals(expected, category.setName(expected).getName());
    }

    @Test
    public void testGetSetDescription() {
        var category = new Category();
        assertNull(category.getDescription());
        var expected = "Test Category Description";
        assertEquals(expected, category.setDescription(expected).getDescription());
    }

    @Test
    public void testGetSetParent() {
        var parent = new Category();
        var child = new Category();
        assertFalse(child.getParent().isPresent());
        assertSame(parent, child.setParent(parent).getParent().orElse(null));
    }

}
