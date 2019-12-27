package com.cat;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static final String KEY_SERIALIZATION = "1";
    private static final String KEY_EXTERNALIZATION = "2";
    private static final String CATS_FILE_PATH = "src/com/cat/cats.txt";
    private static final String SERIALIZATION_OUTPUT_FILE = "src/com/cat/cats.ser";
    private static final String EXTERNALIZATION_OUTPUT_FILE = "src/com/cat/cats.ext";

    public static void main(String[] args) {
        CatParser catParser = new CatParser();
        Set<Cat> catSerializableSet =  catParser.parseCatFromFile(KEY_SERIALIZATION, CATS_FILE_PATH);
        Set<Cat> catExternalizableSet =  catParser.parseCatFromFile(KEY_EXTERNALIZATION, CATS_FILE_PATH);

        writeCatObjectsToFile(SERIALIZATION_OUTPUT_FILE, catSerializableSet);
        writeCatObjectsToFile(EXTERNALIZATION_OUTPUT_FILE, catExternalizableSet);

        System.out.println(String.format("cats before serialization:\n%s\ncats before externalisation:\n%s\n",
                catSerializableSet, catExternalizableSet));

        catSerializableSet = readCatObjectsFromFile(SERIALIZATION_OUTPUT_FILE);
        catExternalizableSet = readCatObjectsFromFile(EXTERNALIZATION_OUTPUT_FILE);

        System.out.println(String.format("cats after serialization:\n%s\ncats after externalisation:\n%s\n",
                catSerializableSet, catExternalizableSet));
    }

    public static void writeCatObjectsToFile(String pathToFile, Set<Cat> cats) {
        try (final FileOutputStream fileOut = new FileOutputStream(pathToFile)) {
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOut);
            outputStream.writeObject(cats);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<Cat> readCatObjectsFromFile(String pathToFile) {
        Set<Cat> cats = null;
        try (final FileInputStream fileIn = new FileInputStream(pathToFile)) {
            ObjectInputStream inputStream = new ObjectInputStream(fileIn);
            cats = (Set<Cat>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cats;
    }
}
