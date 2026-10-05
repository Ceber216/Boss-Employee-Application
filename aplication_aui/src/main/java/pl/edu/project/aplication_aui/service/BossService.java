package pl.edu.project.aplication_aui.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.project.aplication_aui.model.Boss;
import pl.edu.project.aplication_aui.repository.BossRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BossService {
    private final BossRepository bossRepository;

    public List<Boss> findAll(){
        return bossRepository.findAll();
    }

    public Boss findById(UUID id){
        return bossRepository.findById(id).orElse(null);
    }

    public void save(Boss boss){
        bossRepository.save(boss);
    }

    public void deleteById(UUID id){
        bossRepository.deleteById(id);
    }
}
