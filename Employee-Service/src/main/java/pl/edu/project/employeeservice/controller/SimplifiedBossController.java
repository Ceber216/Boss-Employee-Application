package pl.edu.project.employeeservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.project.employeeservice.model.SimplifiedBoss;
import pl.edu.project.employeeservice.service.SimplifiedBossService;

import java.util.UUID;

@RestController
@RequestMapping("api/bosses")
@AllArgsConstructor
public class SimplifiedBossController {
    private final SimplifiedBossService _simplifiedBossService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody SimplifiedBoss simplifiedBoss){
        _simplifiedBossService.save(simplifiedBoss);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        _simplifiedBossService.deleteById(id);
    }
}
