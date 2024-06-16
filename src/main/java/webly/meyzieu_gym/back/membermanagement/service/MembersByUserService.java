package webly.meyzieu_gym.back.membermanagement.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import webly.meyzieu_gym.back.coursemanagement.entity.Course;
import webly.meyzieu_gym.back.coursemanagement.entity.Season;
import webly.meyzieu_gym.back.coursemanagement.repository.SeasonRepository;
import webly.meyzieu_gym.back.membermanagement.dto.MemberListDto;
import webly.meyzieu_gym.back.membermanagement.entity.Member;
import webly.meyzieu_gym.back.membermanagement.repository.MemberRepository;
import webly.meyzieu_gym.back.registrationmanagement.entity.Registration;

@Service
public class MembersByUserService {

    private final MemberRepository memberRepository;
    private final SeasonRepository seasonRepository;

    public MembersByUserService(MemberRepository memberRepository, SeasonRepository seasonRepository) {
        this.memberRepository = memberRepository;
        this.seasonRepository = seasonRepository;
    }

        public List<MemberListDto> getMembersByUserId(Long userId, boolean filterByRegistration) {
        List<Member> members = memberRepository.findAllByUserId(userId);
        List<Season> upcomingSeasons = getUpcomingSeasons();

        if (filterByRegistration) {
            members = members.stream()
                .filter(member -> !isRegisteredForAllUpcomingSeasons(member, upcomingSeasons) || !isRegistrationValidated(member))
                .collect(Collectors.toList());
        }

        return members.stream()
            .map(this::mapToMemberListDto)
            .collect(Collectors.toList());
    }

    private List<Season> getUpcomingSeasons() {
        Date currentDate = new Date();
        return seasonRepository.findAll().stream()
            .filter(season -> season.getEndDate().after(currentDate))
            .collect(Collectors.toList());
    }

    private boolean isRegisteredForAllUpcomingSeasons(Member member, List<Season> upcomingSeasons) {
        Set<Season> registeredSeasons = member.getRegistrations().stream()
            .map(Registration::getCourse)
            .map(Course::getSeason)
            .collect(Collectors.toSet());

        return upcomingSeasons.stream()
            .allMatch(registeredSeasons::contains);
    }

    private boolean isRegistrationValidated(Member member) {
        return member.getRegistrations().stream()
            .anyMatch(reg -> "registration validated".equals(reg.getRegistrationStatus()));
    }

    private MemberListDto mapToMemberListDto(Member member) {
        return new MemberListDto(
            member.getId(), 
            member.getFirstname(),
            member.getLastname(), 
            member.getProfilePictureUrl());
    }
}
