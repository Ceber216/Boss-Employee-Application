package pl.edu.project.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.project.employeeservice.model.SimplifiedBoss;
import pl.edu.project.employeeservice.model.Employee;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    //Query Creation from Method Names
    Employee findEmployeesByBadgeNumber(int badgeNumber);
    List<Employee> findAllBySimplifiedBoss(SimplifiedBoss boss);
}
