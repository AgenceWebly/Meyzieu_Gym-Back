package webly.meyzieu_gym.back.coursemanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webly.meyzieu_gym.back.common.exception.custom.ProgramNotFoundException;
import webly.meyzieu_gym.back.coursemanagement.dto.ProgramDto;
import webly.meyzieu_gym.back.coursemanagement.entity.Program;
import webly.meyzieu_gym.back.coursemanagement.mapper.ProgramMapper;
import webly.meyzieu_gym.back.coursemanagement.repository.ProgramRepository;

@Service
@Transactional
public class ProgramService {
    
    private final ProgramRepository programRepository;
    private final ProgramMapper programMapper;
    
    public ProgramService(ProgramRepository programRepository, ProgramMapper programMapper) {
        this.programRepository = programRepository;
        this.programMapper =  programMapper;
    }

    public void createProgram(ProgramDto programDto) {
        Program program = programMapper.mapToEntity(programDto);
        programRepository.save(program);
    }

    @Transactional(readOnly = true)
    public ProgramDto getProgramById(Long id) {
        Program program = programRepository.findById(id)
            .orElseThrow(() -> new ProgramNotFoundException("Le programme n'a pas été trouvé"));
        return programMapper.mapToDto(program);
    }

    @Transactional(readOnly = true)
    public List<ProgramDto> getAllPrograms() {
        return programRepository.findAll().stream()
            .map(programMapper::mapToDto)
            .collect(Collectors.toList());
    }

    public ProgramDto updateProgram(Long id, ProgramDto programDto) {
        Program program = programRepository.findById(id)
            .orElseThrow(() -> new ProgramNotFoundException("Le programme n'a pas été trouvé"));
            program.setName(programDto.getName());
            program.setDescription(programDto.getDescription());
            program.setIncludingCompetition(programDto.isIncludingCompetition());
        Program updatedProgram = programRepository.save(program);
        return programMapper.mapToDto(updatedProgram);
    }

}
