package webly.meyzieu_gym.back.coursemanagement.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import webly.meyzieu_gym.back.coursemanagement.dto.TrainingSlotDto;
import webly.meyzieu_gym.back.coursemanagement.entity.TrainingSlot;

@Component
public class TrainingSlotMapper {

    public List<TrainingSlotDto> mapToDtos(List<TrainingSlot> trainingSlots) {
        return trainingSlots.stream()
                .map(slot -> new TrainingSlotDto(
                        slot.getId(),
                        slot.getDay(),
                        slot.getStartTime(),
                        slot.getEndTime()))
                .collect(Collectors.toList());
    }
}