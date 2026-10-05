package pl.edu.project.aplication_aui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.project.aplication_aui.model.Boss;
import pl.edu.project.aplication_aui.model.Employee;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    //Query Creation from Method Names
    Employee findEmployeesByBadgeNumber(int badgeNumber);
    List<Employee> findAllByBoss(Boss boss);
}
