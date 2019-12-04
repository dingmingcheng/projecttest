package com.dmc.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@qianli-inc.com
 * @Description
 * @Date: Created in 上午9:22 2018/2/8
 * @Modifyed By:
 */
public class sortTest {
    static class student implements Comparable<student>{
        private Integer id;
        private Integer socre;

        public student(Integer id, Integer socre) {
            this.id = id;
            this.socre = socre;
        }

        @Override
        public String toString() {
            return "student{" +
                    "id=" + id +
                    ", socre=" + socre +
                    '}';
        }

        @Override
        public int compareTo(student o) {
            if (this.id == o.id) {
                int t = this.socre - o.socre;
                return t;
            }
            int value = this.id - o.id;
            return value;
        }
    }

    public static void main(String[] args) {
        List<student> list = new ArrayList<student>();
        int id = (int)(Math.random() * 1000);
        for (int i = 0; i < 10; i ++) {
            int score = i * 10;
            list.add(new student(id, score));
        }
        Collections.sort(list);
        for (student stu : list) {
            System.out.println(stu);
        }
    }
}
