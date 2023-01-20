package com.javable.daleks.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LevelsController {
    @Autowired
    private LevelsRepository levelsRepository;

    @GetMapping("/levels")
    public List<Level> getLevels() {
        return levelsRepository.findAll();
    }
    @GetMapping("/levels/campaign")
    public List<Level> getCampaignLevels() {
        return levelsRepository.findAllByIsCampaign(true);
    }

    @PostMapping("/levels")
    public Level createLevel(@RequestBody Level level) {
        return levelsRepository.save(level);
    }

    @DeleteMapping("/levels/{name}")
    public void deleteLevel(@PathVariable String name) {
        levelsRepository.delete(levelsRepository.findItemByName(name));
    }
}
