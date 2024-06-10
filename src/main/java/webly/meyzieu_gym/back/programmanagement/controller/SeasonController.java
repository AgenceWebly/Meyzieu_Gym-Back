package webly.meyzieu_gym.back.programmanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import webly.meyzieu_gym.back.programmanagement.dto.SeasonDto;
import webly.meyzieu_gym.back.programmanagement.service.SeasonService;

@RestController
@RequestMapping("/api/seasons")
@PreAuthorize("hasRole('ADMIN')")
public class SeasonController {

    private final SeasonService seasonService;

    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @PostMapping
    public ResponseEntity<Void> createSeason(@Valid @RequestBody SeasonDto seasonDto) {
        seasonService.createSeason(seasonDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeasonDto> getSeasonById(@PathVariable Long id) {
        SeasonDto seasonDto = seasonService.getSeasonById(id);
        return ResponseEntity.ok(seasonDto);
    }

    @GetMapping
    public ResponseEntity<List<SeasonDto>> getAllSeasons() {
        List<SeasonDto> seasons = seasonService.getAllSeasons();
        return ResponseEntity.ok(seasons);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeasonDto> updateSeason(@PathVariable Long id, @Valid @RequestBody SeasonDto seasonDto) {
        SeasonDto updatedSeason = seasonService.updateSeason(id, seasonDto);
        return ResponseEntity.ok(updatedSeason);
    }
}
