package webly.meyzieu_gym.back.coursemanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webly.meyzieu_gym.back.common.exception.custom.ProgramNotFoundException;
import webly.meyzieu_gym.back.coursemanagement.dto.ProgramDto;
import webly.meyzieu_gym.back.coursemanagement.entity.Program;
import webly.meyzieu_gym.back.coursemanagement.repository.ProgramRepository;

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
            .orElseThrow(() -> new ProgramNotFoundException("Le programme n'a pas été trouvé"));
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
            .orElseThrow(() -> new ProgramNotFoundException("Le programme n'a pas été trouvé"));
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
