package pl.edu.project.employeeservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.project.employeeservice.dto.CreateEmployeeDTO;
import pl.edu.project.employeeservice.dto.GetEmployeeResponse;
import pl.edu.project.employeeservice.model.SimplifiedBoss;
import pl.edu.project.employeeservice.model.Employee;
import pl.edu.project.employeeservice.service.SimplifiedBossService;
import pl.edu.project.employeeservice.service.EmployeeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bosses/{bossId}/employees")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final SimplifiedBossService simplifiedBossService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateEmployeeDTO createEmployeeDTO, @PathVariable UUID bossId){
        if(simplifiedBossService.findById(bossId) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Boss not found");
        }
        employeeService.save(new Employee(UUID.randomUUID(),createEmployeeDTO.getName(),createEmployeeDTO.getBadgeNumber(), simplifiedBossService.findById(bossId)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id, @PathVariable UUID bossId){
        if(simplifiedBossService.findById(bossId) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Boss not found");
        }
        if(employeeService.findById(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found");
        }
        employeeService.deleteById(id);
    }

    @GetMapping
    public List<GetEmployeeResponse> findAll(@PathVariable UUID bossId){
        if(simplifiedBossService.findById(bossId) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Boss not found");
        }
        SimplifiedBoss boss =  simplifiedBossService.findById(bossId);
        return boss.getEmployees().stream()
                .map(employee -> new GetEmployeeResponse(employee.getId(),employee.getName()))
                .toList();

    }

    @GetMapping("/{id}")
    public GetEmployeeResponse findById(@PathVariable UUID bossId,@PathVariable UUID id){
        if(simplifiedBossService.findById(bossId) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Boss not found");
        }
        if(employeeService.findById(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found");
        }
        return new GetEmployeeResponse(employeeService.findById(id).getId(),employeeService.findById(id).getName());
    }
}
