package webly.meyzieu_gym.back.programmanagement.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import webly.meyzieu_gym.back.common.exception.custom.DuplicateProgramSeasonException;
import webly.meyzieu_gym.back.programmanagement.dto.CreateProgramSeasonDto;
import webly.meyzieu_gym.back.programmanagement.entity.Program;
import webly.meyzieu_gym.back.programmanagement.entity.ProgramSeason;
import webly.meyzieu_gym.back.programmanagement.entity.Season;
import webly.meyzieu_gym.back.programmanagement.repository.ProgramRepository;
import webly.meyzieu_gym.back.programmanagement.repository.ProgramSeasonRepository;
import webly.meyzieu_gym.back.programmanagement.repository.SeasonRepository;

class ProgramSeasonServiceTest {

    @Mock
    private ProgramSeasonRepository programSeasonRepository;

    @Mock
    private SeasonRepository seasonRepository;

    @Mock
    private ProgramRepository programRepository;

    @InjectMocks
    private ProgramSeasonService programSeasonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProgramSeason() throws Exception {
        // Setup the Season entity
        Season season = new Season();
        season.setId(1L);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse("2023-09-01");
        Date endDate = sdf.parse("2023-12-31");

        season.setStartDate(startDate);
        season.setEndDate(endDate);

        // Setup the Program entity
        Program program = new Program();
        program.setId(1L);
        program.setName("Yoga");

        // Mock the repositories
        when(seasonRepository.findById(1L)).thenReturn(Optional.of(season));
        when(programRepository.findById(1L)).thenReturn(Optional.of(program));
        when(programSeasonRepository.findByProgramIdAndSeasonIdAndMinAgeAndMaxAge(1L, 1L, 18, 65)).thenReturn(Optional.empty());

        // Create the DTO
        CreateProgramSeasonDto dto = new CreateProgramSeasonDto();
        dto.setSeasonId(1L);
        dto.setProgramId(1L);
        dto.setRegistrationStartDate(LocalDateTime.of(2023, 1, 1, 0, 0));
        dto.setRegistrationEndDate(LocalDateTime.of(2023, 12, 31, 23, 59));
        dto.setPrice(BigDecimal.valueOf(100.00));
        dto.setMaxMembers(20);
        dto.setMinAge(18);
        dto.setMaxAge(65);

        // Call the service
        programSeasonService.createProgramSeason(dto);

        // Verify that the save method was called
        verify(programSeasonRepository).save(any(ProgramSeason.class));
    }
    
    @Test
    void testCreateProgramSeason_Duplicate() {
        // Setup the Season entity
        Season season = new Season();
        season.setId(1L);

        // Setup the Program entity
        Program program = new Program();
        program.setId(1L);
        program.setName("Yoga");

        // Create the DTO
        CreateProgramSeasonDto dto = new CreateProgramSeasonDto();
        dto.setSeasonId(1L);
        dto.setProgramId(1L);
        dto.setRegistrationStartDate(LocalDateTime.of(2023, 1, 1, 0, 0));
        dto.setRegistrationEndDate(LocalDateTime.of(2023, 12, 31, 23, 59));
        dto.setPrice(BigDecimal.valueOf(100.00));
        dto.setMaxMembers(20);
        dto.setMinAge(18);
        dto.setMaxAge(65);

        // Mock the repositories
        when(seasonRepository.findById(1L)).thenReturn(Optional.of(season));
        when(programRepository.findById(1L)).thenReturn(Optional.of(program));
        when(programSeasonRepository.findByProgramIdAndSeasonIdAndMinAgeAndMaxAge(1L, 1L, 18, 65)).thenReturn(Optional.of(new ProgramSeason()));

        // Call the service and expect DuplicateProgramSeasonException
        assertThrows(DuplicateProgramSeasonException.class, () -> programSeasonService.createProgramSeason(dto));
    }
}
