package pl.edu.project.bossservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
    private final BossService bossService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateBossDTO createBossDTO){
        bossService.save(new Boss(UUID.randomUUID(),createBossDTO.getName(),createBossDTO.getSalary()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        if(bossService.findById(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Boss not found! ");
        }
        bossService.deleteById(id);
    }

    @GetMapping()
    public List<GetBossResponse> findAll() {
        List<Boss> temporaryList = bossService.findAll();
        return temporaryList.stream()
                .map(boss -> new GetBossResponse(boss.getId(), boss.getName()))
                .toList();
    }

    @GetMapping("/{bossId}")
    public GetBossResponse findById(@PathVariable UUID bossId){
        if(bossService.findById(bossId) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Boss not found");
        }
        return new GetBossResponse(bossService.findById(bossId).getId(),bossService.findById(bossId).getName());
    }
}
