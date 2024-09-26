package org.nwolfhub.wordlebot.telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

@Component
public class LanguageProvider {
    private static final Logger log = LoggerFactory.getLogger(LanguageProvider.class);
    @Value("${bot.language.dir}")
    private String languageDir;
    @Value("${bot.language.default}")
    public String defaultLanguage;
    private final HashMap<String, HashMap<String, String>> languages = new HashMap<>();

    public LanguageProvider() {
        File directory = new File(languageDir);
        for (File file : Objects.requireNonNull(directory.listFiles((dir, name) -> name.endsWith(".botlang")))) {
            try (FileInputStream in = new FileInputStream(file)) {
                String content = new String(in.readAllBytes());
                parseLanguage(file.getName().replace(".botlang", ""), content);
            } catch (IOException e) {
                log.error("Failed to load language:{}", e.getMessage());
            }
        }
        log.info("Loaded {} languages", languages.size());
    }

    private void parseLanguage(String name, String rawContent) {
        String[] lines = rawContent.split(";\n");
        HashMap<String, String> language = new HashMap<>();
        for (String line : lines) {
            String key = line.split("=")[0].strip();
            String value = line.substring(line.indexOf('=') + 1).strip();
            languages.put(key, language);
        }
        languages.put(name, language);
    }

    public String getKey(String language, String key) {
        return (language != null && languages.containsKey(language)) ? languages.get(language).get(key) : languages.get(defaultLanguage).get(key);
    }

    public String getKey(String key) {
        return languages.get(defaultLanguage).get(key);
    }
}
