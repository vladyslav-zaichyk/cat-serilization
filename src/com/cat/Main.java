package com.cat;

import com.cat.serialization.Cat;
import com.cat.serialization.CatSerializer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static final String CATS_FILE_PATH = "src/com/cat/cats.txt";
    private static final String SERIALIZATION_OUTPUT_FILE = "src/com/cat/cats.ser";
    private static final String EXTERNALIZATION_OUTPUT_FILE = "src/com/cat/cats.ext";

    public static void main(String[] args) {
        CatParser catParser = new CatParser();
        Cat[] cats = catParser.parseCatFromFile(CATS_FILE_PATH);
        Set<Cat> catSet = new HashSet<>(Arrays.asList(cats));

        CatSerializer.writeCatObjectsToFile(SERIALIZATION_OUTPUT_FILE, catSet);
        catSet = CatSerializer.readCatObjectsFromFile(SERIALIZATION_OUTPUT_FILE);
        System.out.println(catSet);
    }
}
