package com.zenika.grenoble.nightclazz.java21.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonLoader {

    public static String from(String jsonPath) throws IOException, URISyntaxException {
        URL resource = JsonLoader.class.getResource(jsonPath);
        assert resource != null;
        return Files.readString(Path.of(resource.toURI()));
    }
}
