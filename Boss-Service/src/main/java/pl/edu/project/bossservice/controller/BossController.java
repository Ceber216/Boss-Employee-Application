package pl.edu.project.bossservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import pl.edu.project.bossservice.dto.CreateBossDTO;
import pl.edu.project.bossservice.dto.GetBossResponse;
import pl.edu.project.bossservice.model.Boss;
import pl.edu.project.bossservice.service.BossService;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/bosses")
@AllArgsConstructor
public class BossController {
    private final BossService _bossService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateBossDTO createBossDTO){
        Boss boss = new Boss(UUID.randomUUID(),createBossDTO.getName(),createBossDTO.getSalary());
        final String uri = "http://localhost:8082/api/bosses";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Boss> new_boss = new HttpEntity<>(boss);
        HttpStatusCode status = restTemplate.exchange(uri, HttpMethod.POST, new_boss,Void.class).getStatusCode();
        if(!status.is2xxSuccessful()){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Synchronisation failed");
        }
        else{
            _bossService.save(boss);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        if(_bossService.findById(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Boss not found! ");
        }
        final String uri = "http://localhost:8082/api/bosses/" + id;
        RestTemplate restTemplate = new RestTemplate();
        HttpStatusCode status = restTemplate.exchange(uri,HttpMethod.DELETE, null,Void.class).getStatusCode();
        if(!status.is2xxSuccessful()){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Synchronisation failed");
        }
        else{
            _bossService.deleteById(id);
        }
    }

    @GetMapping()
    public List<GetBossResponse> findAll() {
        List<Boss> temporaryList = _bossService.findAll();
        return temporaryList.stream()
                .map(boss -> new GetBossResponse(boss.getId(), boss.getName()))
                .toList();
    }

    @GetMapping("/{bossId}")
    public GetBossResponse findById(@PathVariable UUID bossId){
        if(_bossService.findById(bossId) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Boss not found");
        }
        return new GetBossResponse(_bossService.findById(bossId).getId(), _bossService.findById(bossId).getName());
    }
}
