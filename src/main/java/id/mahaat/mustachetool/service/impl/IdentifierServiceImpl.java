package id.mahaat.mustachetool.service.impl;

import id.mahaat.mustachetool.service.IdentifierService;
import jakarta.inject.Singleton;
import org.sqids.Sqids;

import java.util.Arrays;

@Singleton
public class IdentifierServiceImpl implements IdentifierService {
    private final Sqids sqids = Sqids.builder().build();

    @Override
    public String generateShortId() {
        return sqids.encode(Arrays.asList(System.currentTimeMillis()));
    }
}
