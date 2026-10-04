package pl.edu.project;

import pl.edu.project.dto.EmployeeDTO;
import pl.edu.project.model.Boss;
import pl.edu.project.model.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main{

    public static void main(String[] agrs) {
            System.out.println("TASK 2");
            List<Boss> bosses = new ArrayList<>();
            Boss boss_one = Boss.builder().name("Jan").salary(5500).build();
            Boss boss_two = Boss.builder().name("Marta").salary(6600).build();
            Boss boss_three = Boss.builder().name("Pawel").salary(4800).build();

            bosses.add(boss_one);
            bosses.add(boss_two);
            bosses.add(boss_three);

            Employee employee_one = Employee.builder().name("Andrzej").badgeNumber(100).boss(boss_three).build();
            Employee employee_two = Employee.builder().name("Piotr").badgeNumber(58).boss(boss_two).build();
            Employee employee_three = Employee.builder().name("Ania").badgeNumber(90).boss(boss_one).build();
            Employee employee_four = Employee.builder().name("Iza").badgeNumber(48).boss(boss_two).build();
            Employee employee_five = Employee.builder().name("Marek").badgeNumber(67).boss(boss_one).build();
            Employee employee_six = Employee.builder().name("Michal").badgeNumber(87).boss(boss_one).build();

            boss_one.getEmployees().add(employee_three);
            boss_one.getEmployees().add(employee_five);
            boss_one.getEmployees().add(employee_six);

            boss_two.getEmployees().add(employee_two);
            boss_two.getEmployees().add(employee_four);

            boss_three.getEmployees().add(employee_one);

            bosses.forEach(boss -> {
                System.out.println(boss.toString());
                boss.getEmployees().forEach(employee -> {
                    System.out.println(employee.toString());
                });
            });
            System.out.println("TASK 3");

            HashSet<Employee> employeeSet = bosses.stream().flatMap(boss -> boss.getEmployees().stream())
                    .collect(Collectors.toCollection(HashSet::new));
            employeeSet.stream().forEach(System.out::println);

            System.out.println("TASK 4");
            employeeSet.stream()
                    .filter(employee -> employee.getBadgeNumber() >= 60)
                    .sorted(Comparator.comparing(Employee::getName)).forEach(System.out::println);

            System.out.println("TASK 5");

            List<EmployeeDTO> employeeDTOList = employeeSet.stream()
                    .map(employee -> EmployeeDTO.builder().name(employee.getName()).badgeNumber(employee.getBadgeNumber()).boss(employee.getBoss().getName()).build())
                    .sorted()
                    .toList();
            employeeDTOList.forEach(System.out::println);

            System.out.println("TASK 6");
            try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("binary_file"))){
                out.writeObject(bosses);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            List<Boss> outPut;
            try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("binary_file"))){
                outPut = (List<Boss>) inputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            outPut.forEach(System.out::println);

            System.out.println("TASK 7");
            ForkJoinPool customThreadPool = new ForkJoinPool(4);
            try{
                customThreadPool.submit(
                        () -> outPut.parallelStream().forEach(boss -> {
                            try{
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println(Thread.currentThread().getName() + " -> " + boss);
                        })
                ).get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            finally {
                customThreadPool.shutdown();
            }
        }
}
