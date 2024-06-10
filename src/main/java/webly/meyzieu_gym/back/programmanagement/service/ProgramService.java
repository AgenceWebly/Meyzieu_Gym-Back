package webly.meyzieu_gym.back.programmanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webly.meyzieu_gym.back.common.exception.custom.ProgramNotFoundException;
import webly.meyzieu_gym.back.programmanagement.dto.ProgramDto;
import webly.meyzieu_gym.back.programmanagement.entity.Program;
import webly.meyzieu_gym.back.programmanagement.repository.ProgramRepository;

@Service
@Transactional
public class ProgramService {
    
    private final ProgramRepository programRepository;

    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public void createProgram(ProgramDto programDto) {
        Program program = mapToEntity(programDto);
        programRepository.save(program);
    }

    @Transactional(readOnly = true)
    public ProgramDto getProgramById(Long id) {
        Program program = programRepository.findById(id)
            .orElseThrow(() -> new ProgramNotFoundException("Program not found"));
        return mapToDto(program);
    }

    @Transactional(readOnly = true)
    public List<ProgramDto> getAllPrograms() {
        return programRepository.findAll().stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    public ProgramDto updateProgram(Long id, ProgramDto programDto) {
        Program program = programRepository.findById(id)
            .orElseThrow(() -> new ProgramNotFoundException("Program not found"));
            program.setName(programDto.getName());
            program.setDescription(programDto.getDescription());
            program.setIncludingCompetition(programDto.isIncludingCompetition());
        Program updatedProgram = programRepository.save(program);
        return mapToDto(updatedProgram);
    }

    private Program mapToEntity(ProgramDto programDto) {
        Program program = new Program();
        program.setName(programDto.getName());
        program.setDescription(programDto.getDescription());
        program.setIncludingCompetition(programDto.isIncludingCompetition());
        return program;
    }

    private ProgramDto mapToDto(Program program) {
        return new ProgramDto(
            program.getId(),
            program.getName(),
            program.getDescription(),
            program.isIncludingCompetition());
    }
}
