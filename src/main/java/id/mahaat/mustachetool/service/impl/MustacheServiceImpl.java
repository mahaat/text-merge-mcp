package id.mahaat.mustachetool.service.impl;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import id.mahaat.mustachetool.service.IdentifierService;
import id.mahaat.mustachetool.service.MustacheService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Singleton
public class MustacheServiceImpl implements MustacheService {

    @Inject
    private IdentifierService identifierService;

    @Override
    public String compute(String template, Map<String, Object> parameters) {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(new StringReader(template), identifierService.generateShortId());
        StringWriter writer = new StringWriter();
        mustache.execute(writer, parameters);

        return writer.toString();
    }

    @Override
    public List<String> computeList(String template, List<Map<String, Object>> parameters) {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(new StringReader(template), identifierService.generateShortId());
        List<String> results = new ArrayList<>();
        for (Map<String, Object> parameter : parameters) {
            StringWriter writer = new StringWriter();
            mustache.execute(writer, parameter);
            results.add(writer.toString());
        }

        return results;
    }
}
