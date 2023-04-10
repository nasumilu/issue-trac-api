package io.nasumilu.issuetrac.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.nasumilu.issuetrac.serializer.GeometryDeserializer;
import io.nasumilu.issuetrac.serializer.GeometrySerializer;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.Point;


@Entity
@Table(name = "issue")
@JsonPropertyOrder({ "id", "title", "description", "shape" })
public class Issue extends AbstractSubjectable {

    @Lob
    @Column(name = "description")
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String description;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "category", nullable = false)
    private Category category;

    @Column(name = "shape", nullable = false)
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(using = GeometryDeserializer.class)
    private Point shape;

    @Column(name = "title", nullable = false, length = 64)
    private String title;

    @Column(name = "geoid", nullable = false, length = 16)
    private String geoid;

    public String getGeoid() {
        return geoid;
    }

    public Issue setGeoid(String geoid) {
        this.geoid = geoid;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue setDescription(String description) {
        this.description = description;
        return this;
    }

    public Point getShape() {
        return shape;
    }

    public Issue setShape(Point shape) {
        this.shape = shape;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Issue setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Issue setTitle(String title) {
        this.title = title;
        return this;
    }

}