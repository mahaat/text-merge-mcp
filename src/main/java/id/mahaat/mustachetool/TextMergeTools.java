package id.mahaat.mustachetool;

import id.mahaat.mustachetool.service.MustacheService;
import io.quarkiverse.mcp.server.Tool;
import io.quarkiverse.mcp.server.ToolArg;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;

public class TextMergeTools {

    @Inject
    private MustacheService mustacheService;

    @Tool(description = "Merge text with parameters")
    String mergeText(@ToolArg(description = "Mustache formatted text template") String template,
                     @ToolArg(description = "Parameters of the text and its values") Map<String, Object> parameters) {
        return mustacheService.compute(template, parameters);
    }

    @Tool(description = "Merge text with multiple group of parameters")
    List<String> mergeTextMultipleParameters(@ToolArg(description = "Mustache formatted text template") String template,
                                             @ToolArg(description = "List of parameters group of the text and its values") List<Map<String, Object>> parameters) {
        return mustacheService.computeList(template, parameters);
    }
}
