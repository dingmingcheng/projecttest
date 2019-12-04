package com.dmc.DesignPatterns.builder.scene2;

import java.util.Scanner;

public class Base {
    private Integer chinese;
    private Integer maths;
    private Integer english;

    public Base(Integer chinese, Integer maths, Integer english) {
        this.chinese = chinese;
        this.maths = maths;
        this.english = english;
    }

    public Integer getChinese() {
        return chinese;
    }

    public void setChinese(Integer chinese) {
        this.chinese = chinese;
    }

    public Integer getMaths() {
        return maths;
    }

    public void setMaths(Integer maths) {
        this.maths = maths;
    }

    public Integer getEnglish() {
        return english;
    }

    public void setEnglish(Integer english) {
        this.english = english;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }
    }
}
