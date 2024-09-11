package webly.meyzieu_gym.back.coursemanagement.mapper;

import org.springframework.stereotype.Component;

import webly.meyzieu_gym.back.coursemanagement.dto.SeasonDto;
import webly.meyzieu_gym.back.coursemanagement.entity.Season;

@Component
public class SeasonMapper {
    
    public Season mapToEntity(SeasonDto seasonDto) {
        Season season = new Season();
        season.setStartDate(seasonDto.getStartDate());
        season.setEndDate(seasonDto.getEndDate());
        return season;
    }

    public SeasonDto mapToDto(Season season) {
        return new SeasonDto(
            season.getId(), 
            season.getStartDate(), 
            season.getEndDate());
    }
}
