package pl.edu.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Builder
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Boss implements Comparable<Boss>, Serializable {
    private String name;
    private int salary;
    @Builder.Default
    private List<Employee> employees = new ArrayList<>();

    @Override
    public int compareTo(Boss boss){
        return Integer.compare(this.salary,boss.salary);
    }
}
