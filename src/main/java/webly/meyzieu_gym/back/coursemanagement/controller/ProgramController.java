package webly.meyzieu_gym.back.coursemanagement.controller;

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
import webly.meyzieu_gym.back.coursemanagement.dto.ProgramDto;
import webly.meyzieu_gym.back.coursemanagement.service.ProgramService;

@RestController
@RequestMapping("/api/programs")
@PreAuthorize("hasRole('ADMIN')")
public class ProgramController {
    
    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @PostMapping
    public ResponseEntity<Void> createProgram(@Valid @RequestBody ProgramDto programDto) {
        programService.createProgram(programDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramDto> getProgramById(@PathVariable Long id) {
        ProgramDto programDto = programService.getProgramById(id);
        return ResponseEntity.ok(programDto);
    }

    @GetMapping
    public ResponseEntity<List<ProgramDto>> getAllProgram() {
        List<ProgramDto> programs = programService.getAllPrograms();
        return ResponseEntity.ok(programs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgramDto> updateProgram(@PathVariable Long id, @Valid @RequestBody ProgramDto programDto) {
        ProgramDto updatedProgram = programService.updateProgram(id, programDto);
        return ResponseEntity.ok(updatedProgram);
    }
    
}
