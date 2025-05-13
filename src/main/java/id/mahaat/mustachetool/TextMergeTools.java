package id.mahaat.mustachetool;

import id.mahaat.mustachetool.service.MustacheService;
import io.quarkiverse.mcp.server.TextContent;
import io.quarkiverse.mcp.server.Tool;
import io.quarkiverse.mcp.server.ToolArg;
import io.quarkiverse.mcp.server.ToolResponse;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;

public class TextMergeTools {

    @Inject
    private MustacheService mustacheService;

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
}
