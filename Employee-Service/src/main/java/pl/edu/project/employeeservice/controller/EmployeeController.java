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
    private final EmployeeService _employeeService;
    private final SimplifiedBossService _simplifiedBossService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateEmployeeDTO createEmployeeDTO, @PathVariable UUID bossId){
        if(_simplifiedBossService.findById(bossId) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Boss not found");
        }
        _employeeService.save(new Employee(UUID.randomUUID(),createEmployeeDTO.getName(),createEmployeeDTO.getBadgeNumber(), _simplifiedBossService.findById(bossId)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id, @PathVariable UUID bossId){
        if(_simplifiedBossService.findById(bossId) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Boss not found");
        }
        if(_employeeService.findById(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found");
        }
        _employeeService.deleteById(id);
    }

    @GetMapping
    public List<GetEmployeeResponse> findAll(@PathVariable UUID bossId){
        if(_simplifiedBossService.findById(bossId) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Boss not found");
        }
        SimplifiedBoss boss =  _simplifiedBossService.findById(bossId);
        return boss.getEmployees().stream()
                .map(employee -> new GetEmployeeResponse(employee.getId(),employee.getName()))
                .toList();

    }

    @GetMapping("/{id}")
    public GetEmployeeResponse findById(@PathVariable UUID bossId,@PathVariable UUID id){
        if(_simplifiedBossService.findById(bossId) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Boss not found");
        }
        if(_employeeService.findById(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found");
        }
        return new GetEmployeeResponse(_employeeService.findById(id).getId(), _employeeService.findById(id).getName());
    }
}
