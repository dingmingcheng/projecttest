package com.dmc.DesignPatterns.Multition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Multition {
    public static final int COUNT = 10;

    private int index;

    private static List<Multition> multitions = new ArrayList<Multition>(COUNT);

    static {
        for (int i = 0; i < COUNT; i ++) {
            Multition multition = new Multition();
            multition.index = i;
            multitions.add(multition);
        }
    }

    private Multition() {}

    public static Multition getInstance() {
        Random random = new Random();
        int index = random.nextInt(COUNT);
        return getInstance(index);
    }

    public static Multition getInstance(int index) {
        Multition multition = multitions.get(index);
        return multition;
    }

    public int getIndex() {
        return index;
    }
}
