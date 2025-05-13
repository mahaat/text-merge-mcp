package id.mahaat.mustachetool.service;

import java.util.List;
import java.util.Map;

public interface MustacheService {

    String compute(String template, Map<String, Object> parameters);

    List<String> computeList(String template, List<Map<String, Object>> parameters);
}
