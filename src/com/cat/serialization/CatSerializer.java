package com.cat.serialization;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class CatSerializer {
    public static void writeCatObjectsToFile(String pathToFile, Set<Cat> cats) {
        try (final FileOutputStream fileOut = new FileOutputStream(pathToFile)) {
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOut);
            for (Cat cat : cats) {
                outputStream.writeObject(cat);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<Cat> readCatObjectsFromFile(String pathToFile) {
        Set<Cat> cats = new HashSet<>();
        try (final FileInputStream fileIn = new FileInputStream(pathToFile)) {
            ObjectInputStream inputStream = new ObjectInputStream(fileIn);
            System.out.println(inputStream.available());
            while (true) {
                System.out.println((Cat) inputStream.readObject());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cats;
    }
}
