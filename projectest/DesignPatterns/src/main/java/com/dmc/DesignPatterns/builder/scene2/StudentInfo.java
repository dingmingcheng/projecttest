package com.dmc.DesignPatterns.builder.scene2;

public class StudentInfo {
    private String name;
    private String id;
    private Integer chinese;
    private Integer maths;
    private Integer english;
    public static class Builder {
        private String name;
        private String id;
        private Integer chinese;
        private Integer maths;
        private Integer english;

        public Builder base(Base base) {
            this.chinese = base.getChinese();
            this.english = base.getEnglish();
            this.maths = base.getMaths();
            return this;
        }

        public Builder student(Student student) {
            this.id = student.getId();
            this.name = student.getName();
            return this;
        }

        public StudentInfo build() {
            StringBuffer stringBuffer = new StringBuffer();
            return new StudentInfo(this);
        }
    }
    private StudentInfo(Builder builder) {
        this.name = builder.name;
        this.chinese = builder.chinese;
        this.english = builder.english;
        this.maths = builder.maths;
        this.id = builder.id;
        this.name = builder.name;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", chinese=" + chinese +
                ", maths=" + maths +
                ", english=" + english +
                '}';
    }
}
