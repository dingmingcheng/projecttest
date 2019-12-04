package com.dmc.DesignPatterns.Strategy;

import java.util.Comparator;

public class MainTest {
    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        teacher.addStudent(new Student(1,2,3,4, "a"))
                .addStudent(new Student(4,1,13,1, "b"))
                .addStudent(new Student(5,2,23,3, "c"))
                .addStudent(new Student(7,5,7,2, "d"))
                .addStudent(new Student(3,13,1,10, "e"));

        Comparator strategy = StrategyFactory.getStrategy("scienceGrade");
        teacher.setComparator(strategy);
        teacher.showRankByStrategy();
    }
}
