package webly.meyzieu_gym.back.coursemanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webly.meyzieu_gym.back.common.exception.custom.SeasonNotFoundException;
import webly.meyzieu_gym.back.coursemanagement.dto.SeasonDto;
import webly.meyzieu_gym.back.coursemanagement.entity.Season;
import webly.meyzieu_gym.back.coursemanagement.mapper.SeasonMapper;
import webly.meyzieu_gym.back.coursemanagement.repository.SeasonRepository;

@Service
@Transactional
public class SeasonService {

    private final SeasonRepository seasonRepository;
    private final SeasonMapper seasonMapper;

    public SeasonService(SeasonRepository seasonRepository, SeasonMapper seasonMapper) {
        this.seasonRepository = seasonRepository;
        this.seasonMapper = seasonMapper;
    }

    public void createSeason(SeasonDto seasonDto) {
        Season season = seasonMapper.mapToEntity(seasonDto);
        seasonRepository.save(season);
    }

    @Transactional(readOnly = true)
    public SeasonDto getSeasonById(Long id) {
        Season season = seasonRepository.findById(id)
            .orElseThrow(() -> new SeasonNotFoundException("La saison n'a pas été trouvée"));
        return seasonMapper.mapToDto(season);
    }

    @Transactional(readOnly = true)
    public List<SeasonDto> getAllSeasons() {
        return seasonRepository.findAll().stream()
            .map(seasonMapper::mapToDto)
            .collect(Collectors.toList());
    }

    public SeasonDto updateSeason(Long id, SeasonDto seasonDto) {
        Season season = seasonRepository.findById(id)
            .orElseThrow(() -> new SeasonNotFoundException("La saison n'a pas été trouvée"));
        season.setStartDate(seasonDto.getStartDate());
        season.setEndDate(seasonDto.getEndDate());
        Season updatedSeason = seasonRepository.save(season);
        return seasonMapper.mapToDto(updatedSeason);
    }

}
