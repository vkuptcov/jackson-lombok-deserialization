package com.github.vkuptcov;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import lombok.Builder;
import lombok.SneakyThrows;
import lombok.Value;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class DeserializerTest {

    private static final ObjectMapper m = new ObjectMapper();


    @SneakyThrows
    @Test
    @UseDataProvider("jsons")
    public void deserialize(String json, String expectedData) {
        DataHolder dataHolder = m.readValue(json, DataHolder.class);
        Assert.assertEquals(expectedData, dataHolder.getData());
    }

    @DataProvider
    public static Object[][] jsons() {
        return new Object[][] {
            {"{\"data\": \"test\"}", "test"},
            {"{}", "default value"},
        };
    }
}

@JsonDeserialize(builder = DataHolder.DataHolderBuilder.class)
@Builder
@Value
class DataHolder {

    @Builder.Default
    private final String data = "default value";

    @JsonPOJOBuilder(withPrefix = "")
    static final class DataHolderBuilder {}
}
