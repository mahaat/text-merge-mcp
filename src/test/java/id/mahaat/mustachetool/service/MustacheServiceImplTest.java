package id.mahaat.mustachetool.service;

import id.mahaat.mustachetool.service.impl.MustacheServiceImpl;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class MustacheServiceImplTest {

    @Inject
    MustacheServiceImpl mustacheService;

    @Test
    void givenName_whenConvert_thenReturnSuccess() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", "Mustache");
        var result = mustacheService.compute("Hi {{name}}", parameters);
        assertEquals("Hi Mustache", result);
    }

    @Test
    void givenMultipleName_whenConvert_thenReturnSuccess() {
        Map<String, Object> parameters1 = new HashMap<>();
        parameters1.put("name", "Mustache");

        Map<String, Object> parameters2 = new HashMap<>();
        parameters2.put("name", "Andi");

        Map<String, Object> parameters3 = new HashMap<>();
        parameters3.put("name", "Budi");
        var result = mustacheService.computeList("Hi {{name}}", List.of(parameters1, parameters2, parameters3));
        assertEquals("Hi Mustache", result.get(0));
        assertEquals("Hi Andi", result.get(1));
        assertEquals("Hi Budi", result.get(2));

    }

    @Test
    void givenTemplate_whenGetVariableNames_thenReturnVariables(){
        var result = mustacheService.getVariableNames("Hi {{name}} . I am from {{nation}}");
        assertEquals("name", result.get(0));
        assertEquals("nation", result.get(1));
    }
}