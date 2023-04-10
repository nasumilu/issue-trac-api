package io.nasumilu.issuetrac.entity;

import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class AbstractSubjectableTest {


    @Test
    public void testSubjectableGetSetId() {
        var subjectable = Mockito.mock(AbstractSubjectable.class, Answers.CALLS_REAL_METHODS);

        Long expected = 1001L;
        assertEquals(expected, subjectable.setId(expected).getId());

    }

    @Test
    public void testSubjectableGetSetSubject() {
        var subjectable = Mockito.mock(AbstractSubjectable.class, Answers.CALLS_REAL_METHODS);
        var subject = UUID.randomUUID();
        assertEquals(subject, subjectable.setSubject(subject).getSubject());
    }

    @Test
    public void testSubjectableGetSetSubjectString() {
        var subjectable = Mockito.mock(AbstractSubjectable.class, Answers.CALLS_REAL_METHODS);
        var uuid = UUID.randomUUID();
        var subject = uuid.toString();
        assertEquals(subject, subjectable.setSubject(subject).getSubject().toString());
        assertNotSame(uuid, subjectable.getSubject());
    }

}
