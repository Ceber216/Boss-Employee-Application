package pl.edu.project.employeeservice.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.project.employeeservice.model.Employee;
import pl.edu.project.employeeservice.model.SimplifiedBoss;
import pl.edu.project.employeeservice.repository.SimplifiedBossRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SimplifiedBossService {
    private final SimplifiedBossRepository simplifiedBossRepository;

    public void save(SimplifiedBoss simplifiedBoss){
        simplifiedBossRepository.save(simplifiedBoss);
    }

    public void deleteById(UUID id){
        simplifiedBossRepository.deleteById(id);
    }

    public SimplifiedBoss findById(UUID id){
        return simplifiedBossRepository.findById(id).orElse(null);
    }
}
