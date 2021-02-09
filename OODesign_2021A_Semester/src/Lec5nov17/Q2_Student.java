package Lec5nov17;

import java.util.Comparator;
import java.util.Objects;

public class Q2_Student {
    private int grade;
    private String name;

    public Q2_Student(int grade, String name) {
        this.grade = grade;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Q2_Student{" +
                "grade=" + grade +
                ", name='" + name + '\'' +
                '}';
    }

//    @Override
//    public int hashCode() {
//        return name.hashCode()+grade;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        Q2_Student temp = (Q2_Student) obj;
//        return (this.name.equals(temp.name) && grade == temp.grade);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Q2_Student that = (Q2_Student) o;
        return grade == that.grade &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grade, name);
    }
}

