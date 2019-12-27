package com.cat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CatParser {
    private static final String KEY_SERIALIZATION = "1";
    private static final String KEY_EXTERNALIZATION = "2";
    public static final String SEMICOLON_DELIMITER = ";";
    public static final String COMMA_AND_WHITESPACES = "\\s*(,)\\s*";
    private static final String LINE_BREAK = "\r\n";

    private String delimiter;
    private String parameterSplitter;

    public CatParser() {
        delimiter = SEMICOLON_DELIMITER;
        parameterSplitter = COMMA_AND_WHITESPACES;
    }

    public Set<Cat> parseCatFromFile(String serializeKey, String pathToFile) {
        Set<Cat> cats = new HashSet<>();
        try (final Scanner fileReader = new Scanner(new File(pathToFile)).useDelimiter(delimiter)) {
            while (fileReader.hasNext()) {
                Optional.of(fileReader.next())
                        .map(line -> line.replace(LINE_BREAK, ""))
                        .filter(line -> !line.isEmpty())
                        .ifPresent(line -> cats.add(catFromString(serializeKey, line)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cats;
    }

    private Cat catFromString(String key, String line){
        if (key.equals(KEY_SERIALIZATION)) {
            return parseSerializableCatFromString(line);
        } else if (key.equals(KEY_EXTERNALIZATION)) {
            return parseExternalizableCatFromString(line);
        } else {
            throw new IllegalArgumentException("Wrong argument, must be 1 or 2");
        }
    }

    private CatExternalizable parseExternalizableCatFromString(String line) {
        String[] parameters = line.split(parameterSplitter);
        return new CatExternalizable(parameters[0],
                Integer.parseInt(parameters[1]),
                parameters[2]);
    }

    private CatSerializable parseSerializableCatFromString(String line) {
        String[] parameters = line.split(parameterSplitter);
        return new CatSerializable(parameters[0],
                Integer.parseInt(parameters[1]),
                parameters[2]);
    }

}
