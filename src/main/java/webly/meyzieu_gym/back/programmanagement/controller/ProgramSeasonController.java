package webly.meyzieu_gym.back.programmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import webly.meyzieu_gym.back.programmanagement.dto.CreateProgramSeasonDto;
import webly.meyzieu_gym.back.programmanagement.service.ProgramSeasonService;

@RestController
@RequestMapping("/api/program-seasons")
@PreAuthorize("hasRole('ADMIN')")
public class ProgramSeasonController {

    private final ProgramSeasonService programSeasonService;

    public ProgramSeasonController(ProgramSeasonService programSeasonService) {
        this.programSeasonService = programSeasonService;
    }

    @PostMapping
    public ResponseEntity<Void> createProgramSeason(@Valid @RequestBody CreateProgramSeasonDto createProgramSeasonDto) {
        programSeasonService.createProgramSeason(createProgramSeasonDto);
        return ResponseEntity.ok().build();
    }
}
