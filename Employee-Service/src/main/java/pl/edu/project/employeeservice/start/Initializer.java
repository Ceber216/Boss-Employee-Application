package pl.edu.project.employeeservice.start;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pl.edu.project.employeeservice.model.Employee;
import pl.edu.project.employeeservice.model.SimplifiedBoss;
import pl.edu.project.employeeservice.service.SimplifiedBossService;
import pl.edu.project.employeeservice.service.EmployeeService;

import java.util.ArrayList;
import java.util.UUID;

@Component
@Order(1)
@AllArgsConstructor
public class Initializer implements CommandLineRunner {
    private final SimplifiedBossService simplifiedBossService;
    private final EmployeeService employeeService;

    @Override
    public void run(String... args) throws Exception {
        SimplifiedBoss boss_one = new SimplifiedBoss(UUID.randomUUID() ,new ArrayList<>());
        SimplifiedBoss boss_two = new SimplifiedBoss(UUID.randomUUID() ,new ArrayList<>());
        SimplifiedBoss boss_three = new SimplifiedBoss(UUID.randomUUID() ,new ArrayList<>());

        simplifiedBossService.save(boss_one);
        simplifiedBossService.save(boss_two);
        simplifiedBossService.save(boss_three);

        Employee employee_one = new Employee(UUID.randomUUID(),"Michal",1,boss_one);
        Employee employee_two = new Employee(UUID.randomUUID(),"Andrzej",30,boss_one);
        Employee employee_three = new Employee(UUID.randomUUID(),"Marta",20,boss_one);
        Employee employee_four = new Employee(UUID.randomUUID(),"Weronika",90,boss_two);
        Employee employee_five = new Employee(UUID.randomUUID(),"Wiktor",67,boss_two);
        Employee employee_six = new Employee(UUID.randomUUID(),"Szymon",27,boss_three);

        employeeService.save(employee_one);
        employeeService.save(employee_two);
        employeeService.save(employee_three);
        employeeService.save(employee_four);
        employeeService.save(employee_five);
        employeeService.save(employee_six);
    }
}
