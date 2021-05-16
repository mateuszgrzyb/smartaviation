package misc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class JSON {

    public JSON(String resource) throws IOException {
        this(JSON.class.getClassLoader().getResourceAsStream(resource));
    }

    public JSON(InputStream stream) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        array = (ArrayNode) mapper.readTree(stream);
    }

    private final ArrayNode array;
    protected ArrayNode getArray() {
        return array;
    }
}

