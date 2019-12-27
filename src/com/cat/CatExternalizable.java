package com.cat;

import java.io.*;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class CatExternalizable implements Cat, Externalizable {
    private static final long serialVersionUID = 7010110088174444898L;

    private String name;
    private int age;
    private Color color;
    private transient int damage;

    public CatExternalizable() {
    }

    public CatExternalizable(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = Color.valueOf(color.toUpperCase());
        damage = ThreadLocalRandom.current().nextInt(0, 100);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeObject(age);
        out.writeObject(color);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        age = (int) in.readObject();
        color = (Color) in.readObject();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatExternalizable cat = (CatExternalizable) o;
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
