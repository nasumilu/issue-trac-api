package io.nasumilu.issuetrac.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;

import java.io.IOException;

public class GeometryDeserializer extends JsonDeserializer<Geometry> {

    @Override
    public Geometry deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        var node = jsonParser.readValueAs(JsonNode.class);
        var type = node.get("type").asText();
        var srid = node.get("srid").asInt();

        var factory = new GeometryFactory(new PrecisionModel(), srid);

        switch(type) {
            case Geometry.TYPENAME_POINT -> {
                return factory.createPoint(this.deserializePointCoordinates(node, deserializationContext));
            }
            case default -> throw new RuntimeException(String.format("Unable to deserialize geometry type %s!", type));
        }

    }

    private Coordinate deserializePointCoordinates(JsonNode node, DeserializationContext deserializationContext) {
        return this.deserializeCoordinate(node, deserializationContext);
    }

    private Coordinate deserializeCoordinate(JsonNode node, DeserializationContext deserializationContext) {
        var coordinateNode = node.get("coordinates");
        return new Coordinate(coordinateNode.get(0).asDouble(), coordinateNode.get(1).asDouble());
    }
}
