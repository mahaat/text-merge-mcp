package id.mahaat.mustachetool;

import id.mahaat.mustachetool.service.CsvService;
import id.mahaat.mustachetool.service.MustacheService;
import io.quarkiverse.mcp.server.*;
import jakarta.inject.Inject;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

public class TextMergeTools {

    @Inject
    private MustacheService mustacheService;

    @Inject
    private CsvService csvService;

    @Tool(description = "Merge text with parameters")
    ToolResponse mergeText(@ToolArg(description = "Mustache formatted text template") String template,
                           @ToolArg(description = "Parameters of the text and its values") Map<String, Object> parameters) {
        try {
            var result = mustacheService.compute(template, parameters);
            return ToolResponse.success(result);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ToolResponse.error("Failed to merge");
        }
    }

    @Tool(description = "Merge text with multiple group of parameters")
    ToolResponse mergeTextMultipleParameters(@ToolArg(description = "Mustache formatted text template") String template,
                                             @ToolArg(description = "List of parameters group of the text and its values") List<Map<String, Object>> parameters) {
        try {
            var result = mustacheService.computeList(template, parameters);
            List<TextContent> contents = result.stream().map(TextContent::new).toList();
            return ToolResponse.success(contents);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ToolResponse.error("Failed to merge");
        }
    }

    @Tool(description = "Generate csv file with header consists of parameters of given text template. Use this file to add more data from the client")
    ToolResponse getParameterTemplate(@ToolArg(description = "Mustache formatted text template") String template) {
        try {
            var result = mustacheService.getVariableNames(template);
            File tempFile = File.createTempFile("123456", ".csv");
            csvService.writeCsv(tempFile.toPath(), List.of(result));
            TextResourceContents contents = TextResourceContents.create(tempFile.toURI().toString(), Files.readString(tempFile.toPath()));
            return ToolResponse.success(new EmbeddedResource(contents));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ToolResponse.error("Failed build csv");
        }
    }
}
