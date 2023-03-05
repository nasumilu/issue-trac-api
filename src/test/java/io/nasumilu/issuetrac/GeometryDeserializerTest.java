package io.nasumilu.issuetrac;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.nasumilu.issuetrac.entity.Issue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeometryDeserializerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testDeserialize() throws IOException {
        var json = "{\"description\":\"Test Description\",\"shape\":{\"type\":\"Point\",\"coordinates\": [-85.156565,28.5544455],\"srid\":4269},\"title\":\"Test Issue\"}";
        var issue = objectMapper.readValue(json, Issue.class);

        assertNotNull(issue.getShape());
        assertEquals(issue.getShape().getX(), -85.156565, issue.getShape().getPrecisionModel().getMaximumSignificantDigits());
    }

}
