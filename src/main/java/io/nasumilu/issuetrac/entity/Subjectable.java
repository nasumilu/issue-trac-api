package io.nasumilu.issuetrac.entity;

import java.util.UUID;

/**
 * A <code>Subjectable</code> is considered an items point of origin. In the context of the IssueTrac applications this
 * is the original creator of an {@link Issue}, {@link Comment}, and {@link Media}
 */
public interface Subjectable {

    /**
     * The <code>Subjectable</code> unique identifier
     */
    public Long getId();

    /**
     * The <code>Subjectable</code>'s origin.
     */
    public UUID getSubject();
}
