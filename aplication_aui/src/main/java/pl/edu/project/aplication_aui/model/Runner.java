package pl.edu.project.aplication_aui.model;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
@AllArgsConstructor
public class Runner implements CommandLineRunner {
    EmployeeService employeeService;
    BossService bossService;


    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write help to see available commands");
        String command;
        main_loop:while(true) {
            command = scanner.next();
            switch (command) {
                case "help" -> {
                    System.out.println("--- Available Commands ---");
                    System.out.println("help            - Displays this command list");
                    System.out.println("getAllBosses    - Displays all bosses (categories)");
                    System.out.println("getAllEmployees - Displays all employees");
                    System.out.println("addNewEmployee  - Adds a new employee with boss selection");
                    System.out.println("deleteEmployee  - Deletes an employee by ID");
                    System.out.println("quit            - Exits the application");
                }
                case "quit" -> {
                    break main_loop;
                }
                case "getAllBosses" -> {
                    System.out.println(bossService.findAll());
                    break;
                }
                case "getAllEmployees" -> {
                    System.out.println(employeeService.findAll());
                    break;
                }
                case "addNewEmployee" -> {
                    System.out.println("Give a name of employee: ");
                    String command_name = scanner.next();

                    System.out.println("Give a badge number of employee: ");
                    int command_badgeNumber = scanner.nextInt();

                    System.out.println("Available bosses: (choose number)");
                    List<Boss> bosses;
                    bosses = bossService.findAll();
                    for(int i = 0; i < bosses.size(); i++){
                        System.out.println(i + ". " + bosses.get(i).getName() + " (ID: " + bosses.get(i).getId() + ")");
                    }
                    int command_boss = scanner.nextInt();
                    Boss selectedBoss = bosses.get(command_boss);
                    Employee createdEmployee = new Employee(UUID.randomUUID(),command_name,command_badgeNumber,selectedBoss);

                    employeeService.save(createdEmployee);
                    break;
                }
                case "deleteEmployee" -> {
                    String next_command;
                    System.out.println("Give an id of employee: ");
                    next_command = scanner.next();
                    UUID id = UUID.fromString(next_command);
                    employeeService.deleteById(id);
                    break;
                }
            }
        }
    }

}
