package com.cat.serialization;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Cat implements Serializable {
    private String name;
    private int age;
    private Color color;
    private transient int damage;

    public Cat(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = Color.valueOf(color.toUpperCase());
        damage = ThreadLocalRandom.current().nextInt(0, 100);
    }

    enum Color {
        BLACK(0x0A0A0A),
        WHITE(0x969696),
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
        Cat сat = (Cat) o;
        return age == сat.age &&
                damage == сat.damage &&
                Objects.equals(name, сat.name) &&
                color == сat.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, color, damage);
    }

    @Override
    public String toString() {
        return "{"
                + name + ", "
                + age + " y.o., "
                + color.toString().toLowerCase() + ", "
                + damage + " dmg"
                + "}";
    }
}
