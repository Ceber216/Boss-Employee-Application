package pl.edu.project.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.project.employeeservice.model.Employee;

import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GetSimplifiedBossResponse {
    private UUID id;
    private List<Employee> employeeList;
}
