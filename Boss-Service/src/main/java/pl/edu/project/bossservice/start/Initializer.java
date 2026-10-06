package pl.edu.project.bossservice.start;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.project.bossservice.model.Boss;
import pl.edu.project.bossservice.service.BossService;

import java.util.ArrayList;
import java.util.UUID;

@Component
@AllArgsConstructor
public class Initializer implements CommandLineRunner {
    private final BossService bossService;


    @Override
    public void run(String... args) {
        Boss boss_one = new Boss(UUID.randomUUID() ,"Piotr",5600);
        Boss boss_two = new Boss(UUID.randomUUID() ,"Pawel",6700);
        Boss boss_three = new Boss(UUID.randomUUID() ,"Ania",4000);

        bossService.save(boss_one);
        bossService.save(boss_two);
        bossService.save(boss_three);
    }
}
