package pl.edu.project.bossservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import pl.edu.project.bossservice.model.Boss;
import pl.edu.project.bossservice.repository.BossRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BossService {
    private final BossRepository _bossRepository;

    public List<Boss> findAll(){
        return _bossRepository.findAll();
    }

    public Boss findById(UUID id){
        return _bossRepository.findById(id).orElse(null);
    }

    public void save(Boss boss){
        _bossRepository.save(boss);
    }

    public void deleteById(UUID id){
        _bossRepository.deleteById(id);
    }
}
