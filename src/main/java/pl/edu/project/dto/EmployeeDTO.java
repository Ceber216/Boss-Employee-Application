package pl.edu.project.dto;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class EmployeeDTO implements Comparable<EmployeeDTO>{
    private String name;
    private int badgeNumber;
    private String boss;

    @Override
    public int compareTo(EmployeeDTO employeeDTO){
        return Integer.compare(this.badgeNumber,employeeDTO.badgeNumber);
    }
}
