package com.dmc.DesignPatterns.Strategy;

import java.util.Objects;

public class Student {
    private int mathGrade;

    private int historyGrade;

    private int scienceGrade;

    private int englishGrade;

    private String name;

    public Student(int mathGrade, int historyGrade, int scienceGrade, int englishGrade, String name) {
        this.mathGrade = mathGrade;
        this.historyGrade = historyGrade;
        this.scienceGrade = scienceGrade;
        this.englishGrade = englishGrade;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return mathGrade == student.mathGrade &&
                historyGrade == student.historyGrade &&
                scienceGrade == student.scienceGrade &&
                englishGrade == student.englishGrade &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mathGrade, historyGrade, scienceGrade, englishGrade, name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "mathGrade=" + mathGrade +
                ", historyGrade=" + historyGrade +
                ", scienceGrade=" + scienceGrade +
                ", englishGrade=" + englishGrade +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMathGrade() {
        return mathGrade;
    }

    public void setMathGrade(int mathGrade) {
        this.mathGrade = mathGrade;
    }

    public int getHistoryGrade() {
        return historyGrade;
    }

    public void setHistoryGrade(int historyGrade) {
        this.historyGrade = historyGrade;
    }

    public int getScienceGrade() {
        return scienceGrade;
    }

    public void setScienceGrade(int scienceGrade) {
        this.scienceGrade = scienceGrade;
    }

    public int getEnglishGrade() {
        return englishGrade;
    }

    public void setEnglishGrade(int englishGrade) {
        this.englishGrade = englishGrade;
    }
}
