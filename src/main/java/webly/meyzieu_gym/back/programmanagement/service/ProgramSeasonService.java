package webly.meyzieu_gym.back.programmanagement.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import webly.meyzieu_gym.back.common.exception.custom.DuplicateProgramSeasonException;
import webly.meyzieu_gym.back.common.exception.custom.ProgramNotFoundException;
import webly.meyzieu_gym.back.common.exception.custom.SeasonNotFoundException;
import webly.meyzieu_gym.back.programmanagement.dto.CreateProgramSeasonDto;
import webly.meyzieu_gym.back.programmanagement.entity.Program;
import webly.meyzieu_gym.back.programmanagement.entity.ProgramSeason;
import webly.meyzieu_gym.back.programmanagement.entity.Season;
import webly.meyzieu_gym.back.programmanagement.repository.ProgramRepository;
import webly.meyzieu_gym.back.programmanagement.repository.ProgramSeasonRepository;
import webly.meyzieu_gym.back.programmanagement.repository.SeasonRepository;

@Service
public class ProgramSeasonService {
    
    private final ProgramSeasonRepository programSeasonRepository;
    private final SeasonRepository seasonRepository;
    private final ProgramRepository programRepository;

    public ProgramSeasonService(ProgramSeasonRepository programSeasonRepository, SeasonRepository seasonRepository, ProgramRepository programRepository) {
        this.programSeasonRepository = programSeasonRepository;
        this.seasonRepository = seasonRepository;
        this.programRepository = programRepository;
    }

    @Transactional
    public void createProgramSeason(CreateProgramSeasonDto createProgramSeasonDto) {
        Season season = seasonRepository.findById(createProgramSeasonDto.getSeasonId())
                .orElseThrow(() -> new SeasonNotFoundException("Season not found"));
        Program program = programRepository.findById(createProgramSeasonDto.getProgramId())
                .orElseThrow(() -> new ProgramNotFoundException("Program not found"));

        programSeasonRepository.findByProgramIdAndSeasonIdAndMinAgeAndMaxAge(
            createProgramSeasonDto.getProgramId(),
            createProgramSeasonDto.getSeasonId(),
            createProgramSeasonDto.getMinAge(),
            createProgramSeasonDto.getMaxAge()
        ).ifPresent(existingProgramSeason -> {
            throw new DuplicateProgramSeasonException("A ProgramSeason with the same program, season, minAge, and maxAge already exists.");
        });

        ProgramSeason programSeason = new ProgramSeason(
                season, 
                program, 
                createProgramSeasonDto.getRegistrationStartDate(),
                createProgramSeasonDto.getRegistrationEndDate(), 
                createProgramSeasonDto.getPrice(),
                createProgramSeasonDto.getMaxMembers(), 
                createProgramSeasonDto.getMinAge(),
                createProgramSeasonDto.getMaxAge());

        programSeasonRepository.save(programSeason);
    }
}
