package webly.meyzieu_gym.back.coursemanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webly.meyzieu_gym.back.common.exception.custom.SeasonNotFoundException;
import webly.meyzieu_gym.back.coursemanagement.dto.SeasonDto;
import webly.meyzieu_gym.back.coursemanagement.entity.Season;
import webly.meyzieu_gym.back.coursemanagement.repository.SeasonRepository;

@Service
@Transactional
public class SeasonService {

    private final SeasonRepository seasonRepository;

    public SeasonService(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    public void createSeason(SeasonDto seasonDto) {
        Season season = mapToEntity(seasonDto);
        seasonRepository.save(season);
    }

    @Transactional(readOnly = true)
    public SeasonDto getSeasonById(Long id) {
        Season season = seasonRepository.findById(id)
            .orElseThrow(() -> new SeasonNotFoundException("La saison n'a pas été trouvé"));
        return mapToDto(season);
    }

    @Transactional(readOnly = true)
    public List<SeasonDto> getAllSeasons() {
        return seasonRepository.findAll().stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    public SeasonDto updateSeason(Long id, SeasonDto seasonDto) {
        Season season = seasonRepository.findById(id)
            .orElseThrow(() -> new SeasonNotFoundException("La saison n'a pas été trouvé"));
        season.setStartDate(seasonDto.getStartDate());
        season.setEndDate(seasonDto.getEndDate());
        Season updatedSeason = seasonRepository.save(season);
        return mapToDto(updatedSeason);
    }

    private Season mapToEntity(SeasonDto seasonDto) {
        Season season = new Season();
        season.setStartDate(seasonDto.getStartDate());
        season.setEndDate(seasonDto.getEndDate());
        return season;
    }

    private SeasonDto mapToDto(Season season) {
        return new SeasonDto(
            season.getId(), 
            season.getStartDate(), 
            season.getEndDate());
    }
}
