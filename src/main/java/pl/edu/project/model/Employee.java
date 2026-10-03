package pl.edu.project.model;


import lombok.*;

import java.io.Serializable;

@Builder
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Employee implements Comparable<Employee>, Serializable {
    private String name;
    private int badgeNumber;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Boss boss;

    @Override
    public int compareTo(Employee employee){
        return Integer.compare(this.badgeNumber,employee.badgeNumber);
    }
}
