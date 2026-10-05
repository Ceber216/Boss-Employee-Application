package pl.edu.project.aplication_aui.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.project.aplication_aui.model.Boss;
import pl.edu.project.aplication_aui.model.Employee;
import pl.edu.project.aplication_aui.repository.EmployeeRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee findById(UUID id){
        return employeeRepository.findById(id).orElse(null);
    }

    public void save(Employee employee){
        employeeRepository.save(employee);
    }

    public void deleteById(UUID id){
        employeeRepository.deleteById(id);
    }

    public List<Employee> findAllByBoss(Boss boss){
        return employeeRepository.findAllByBoss(boss);
    }
}
