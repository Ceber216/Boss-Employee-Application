package pl.edu.project.aplication_aui.start;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pl.edu.project.aplication_aui.model.Boss;
import pl.edu.project.aplication_aui.model.Employee;
import pl.edu.project.aplication_aui.service.BossService;
import pl.edu.project.aplication_aui.service.EmployeeService;

import java.util.ArrayList;
import java.util.UUID;

@Component
@Order(1)
@AllArgsConstructor
public class Initializer implements CommandLineRunner {
    private final BossService bossService;
    private final EmployeeService employeeService;

    @Override
    public void run(String... args) throws Exception {
        Boss boss_one = new Boss(UUID.randomUUID() ,"Piotr",5600,new ArrayList<>());
        Boss boss_two = new Boss(UUID.randomUUID() ,"Pawel",6700,new ArrayList<>());
        Boss boss_three = new Boss(UUID.randomUUID() ,"Ania",4000,new ArrayList<>());

        bossService.save(boss_one);
        bossService.save(boss_two);
        bossService.save(boss_three);

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
