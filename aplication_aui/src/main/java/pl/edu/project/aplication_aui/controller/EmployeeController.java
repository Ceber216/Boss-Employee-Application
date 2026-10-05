package pl.edu.project.aplication_aui.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.project.aplication_aui.dto.CreateEmployeeDTO;
import pl.edu.project.aplication_aui.dto.GetEmployeeResponse;
import pl.edu.project.aplication_aui.model.Boss;
import pl.edu.project.aplication_aui.model.Employee;
import pl.edu.project.aplication_aui.service.BossService;
import pl.edu.project.aplication_aui.service.EmployeeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bosses/{bossId}/employees")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final BossService bossService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateEmployeeDTO createEmployeeDTO, @PathVariable UUID bossId){
        if(bossService.findById(bossId) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Boss not found");
        }
        employeeService.save(new Employee(UUID.randomUUID(),createEmployeeDTO.getName(),createEmployeeDTO.getBadgeNumber(), bossService.findById(bossId)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id, @PathVariable UUID bossId){
        if(bossService.findById(bossId) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Boss not found");
        }
        if(employeeService.findById(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found");
        }
        employeeService.deleteById(id);
    }

    @GetMapping
    public List<GetEmployeeResponse> findAll(@PathVariable UUID bossId){
        if(bossService.findById(bossId) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Boss not found");
        }
        Boss boss =  bossService.findById(bossId);
        return boss.getEmployees().stream()
                .map(employee -> new GetEmployeeResponse(employee.getId(),employee.getName()))
                .toList();

    }

    @GetMapping("/{id}")
    public GetEmployeeResponse findById(@PathVariable UUID bossId,@PathVariable UUID id){
        if(bossService.findById(bossId) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Boss not found");
        }
        if(employeeService.findById(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found");
        }
        return new GetEmployeeResponse(employeeService.findById(id).getId(),employeeService.findById(id).getName());
    }
}
