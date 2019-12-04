package com.dmc.DesignPatterns.builder.scene2;

/**
 * 第二种场景，用于javabean部分，进行javabean的赋值，在参数较多时，或者在参数不确定是否需要赋值的情况下，构造器比较难写，而且一旦粗心没写好
 * 入参，则容易出错，如果使用setter则代码较为臃肿，出错也难以检查。
 */
public class mainTest {
    public static void main(String[] args) {
        Student student = new Student("dmc", "1001");
        Base base = new Base(50, 150, 100);
        StudentInfo studentInfo = new StudentInfo.Builder()
                .student(student)
                .base(base)
                .build();
        System.out.println(studentInfo.toString());
        StudentInfo studentInfo1 = StudentInfo.builder().build();
    }
}
