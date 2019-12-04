package com.dmc.DesignPatterns.Strategy;

import java.util.*;

public class Teacher {
    private List<Student> students = new ArrayList<Student>();

    private Comparator<? super Student>  comparator;

    Teacher() {
    }

    public Teacher addStudent(Student student) {
        students.add(student);
        return this;
    }

    public void showRankByStrategy() {
        Collections.sort(students, comparator);
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }

    public Comparator<? super Student> getComparator() {
        return comparator;
    }

    public void setComparator(Comparator<? super Student> comparator) {
        this.comparator = comparator;
    }
}
