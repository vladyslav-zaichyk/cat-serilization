package com.cat;

import java.io.*;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class CatSerializable implements Cat, Serializable {
    private static final long serialVersionUID = 1833926900887302741L;

    private String name;
    private int age;
    private Color color;
    private transient int damage;

    public CatSerializable(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = Color.valueOf(color.toUpperCase());
        damage = ThreadLocalRandom.current().nextInt(0, 100);
    }

    enum Color {
        BLACK(0x0A0A0A),
        WHITE(0xFFFFFF),
        GRAY(0x969696),
        RED(0xF7F7F7),
        BROWN(0xBA8A22);

        private int colorCode;

        Color(int colorCode) {
            this.colorCode = colorCode;
        }

        public int getColorCode() {
            return colorCode;
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        in.defaultReadObject();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatSerializable cat = (CatSerializable) o;
        return age == cat.age &&
                damage == cat.damage &&
                Objects.equals(name, cat.name) &&
                color == cat.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, color, damage);
    }

    @Override
    public String toString() {
        return String.format("{ %s, %s y.o., %s, %s dmg }", name, age, color, damage);
    }
}
