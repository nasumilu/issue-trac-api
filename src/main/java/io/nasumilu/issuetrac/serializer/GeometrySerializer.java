package io.nasumilu.issuetrac.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.locationtech.jts.geom.*;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class GeometrySerializer extends JsonSerializer<Geometry> {
    @Override
    public void serialize(Geometry geometry, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("type", geometry.getGeometryType());
        jsonGenerator.writeFieldName("coordinates");
        switch (geometry.getGeometryType()) {
            case Geometry.TYPENAME_POINT -> this.serializePointCoordinates((Point) geometry, jsonGenerator);
            case Geometry.TYPENAME_LINESTRING ->
                    this.serializeLineStringCoordinates((LineString) geometry, jsonGenerator);
            default -> throw new RuntimeException(String.format("Unable to serialize %s", geometry.getClass()));
        }
        jsonGenerator.writeObjectField("srid", geometry.getSRID());
        jsonGenerator.writeEndObject();
    }

    private void serializePointCoordinates(Point point, JsonGenerator jsonGenerator) throws IOException {
        this.serializeCoordinate(point.getCoordinate(), jsonGenerator);
    }

    private void serializeLineStringCoordinates(LineString lineString, JsonGenerator jsonGenerator) throws IOException {
        this.serializeCoordinateSequence(lineString.getCoordinateSequence(), jsonGenerator);
    }

    private void serializeCoordinateSequence(CoordinateSequence sequence, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartArray();
        for (Coordinate coordinate : sequence.toCoordinateArray()) {
            this.serializeCoordinate(coordinate, jsonGenerator);
        }
        jsonGenerator.writeEndArray();
    }

    private void serializeCoordinate(Coordinate coordinate, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartArray();
        jsonGenerator.writeObject(coordinate.x);
        jsonGenerator.writeObject(coordinate.y);
        jsonGenerator.writeEndArray();
    }
}
