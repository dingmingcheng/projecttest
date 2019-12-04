package com.dmc.fastjsontest.jsonTestBean;

import java.util.List;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 上午11:03 2018/5/22
 * @Modified By:
 */
public class Complex {
    private Integer index;
    private List<Simple> simples;
    private List<Integer> grades;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public List<Simple> getSimples() {
        return simples;
    }

    public void setSimples(List<Simple> simples) {
        this.simples = simples;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public void setGrades(List<Integer> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Complex{" +
            "index=" + index +
            ", simples=" + simples +
            ", grades=" + grades +
            '}';
    }
}
