package com.github.vkuptcov;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Test;

public class DataHolderDeserializationTest {

    @SneakyThrows
    @Test
    public void deserialize() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.readValue("{}", DataHolder.class);
    }
}
