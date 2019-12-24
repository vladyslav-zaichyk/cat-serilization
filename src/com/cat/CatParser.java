package com.cat;

import com.cat.serialization.Cat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

public class CatParser {
    public static final String SEMICOLON_DELIMITER = ";";
    public static final String COMMA_AND_WHITESPACES = "\\s*(,)\\s*";
    private static final String LINE_BREAK = "\r\n";

    private String delimiter;
    private String parameterSplitter;

    public CatParser() {
        delimiter = SEMICOLON_DELIMITER;
        parameterSplitter = COMMA_AND_WHITESPACES;
    }

    public Cat[] parseCatFromFile(String pathToFile) {
        Set<Cat> cats = new HashSet<>();
        try (final Scanner fileReader = new Scanner(new File(pathToFile)).useDelimiter(delimiter)) {
            while (fileReader.hasNext()) {
                Optional.of(fileReader.next())
                        .map(line -> line.replace(LINE_BREAK, ""))
                        .filter(line -> !line.isEmpty())
                        .ifPresent(line -> cats.add(parseCatFromString(line)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cats.toArray(new Cat[cats.size()]);
    }

    public Cat parseCatFromString(String line) {
        String[] parameters = line.split(parameterSplitter);
        return new Cat(parameters[0],
                Integer.parseInt(parameters[1]),
                parameters[2]);
    }

}
