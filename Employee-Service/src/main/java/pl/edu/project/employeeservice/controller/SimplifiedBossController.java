package pl.edu.project.employeeservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.edu.project.employeeservice.model.SimplifiedBoss;
import pl.edu.project.employeeservice.service.SimplifiedBossService;

import java.util.UUID;

@RestController
@RequestMapping("api/bosses")
@AllArgsConstructor
public class SimplifiedBossController {
    private final SimplifiedBossService simplifiedBossService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody SimplifiedBoss simplifiedBoss){
        simplifiedBossService.save(simplifiedBoss);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        simplifiedBossService.deleteById(id);
    }
}
