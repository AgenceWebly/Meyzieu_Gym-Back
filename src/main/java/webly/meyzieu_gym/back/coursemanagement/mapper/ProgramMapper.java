package webly.meyzieu_gym.back.coursemanagement.mapper;

import org.springframework.stereotype.Component;

import webly.meyzieu_gym.back.coursemanagement.dto.ProgramDto;
import webly.meyzieu_gym.back.coursemanagement.entity.Program;

@Component
public class ProgramMapper {

    public Program mapToEntity(ProgramDto programDto) {
        Program program = new Program();
        program.setName(programDto.getName());
        program.setDescription(programDto.getDescription());
        program.setIncludingCompetition(programDto.isIncludingCompetition());
        return program;
    }

    public ProgramDto mapToDto(Program program) {
        return new ProgramDto(
            program.getId(),
            program.getName(),
            program.getDescription(),
            program.isIncludingCompetition());
    }
}