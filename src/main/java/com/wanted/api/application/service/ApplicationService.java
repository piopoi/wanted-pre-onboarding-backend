package com.wanted.api.application.service;

import com.wanted.api.application.domain.Application;
import com.wanted.api.application.exception.AlreadyAppliedException;
import com.wanted.api.application.repository.ApplicationRepository;
import com.wanted.api.member.domain.Member;
import com.wanted.api.member.service.MemberService;
import com.wanted.api.recruit.domain.Recruit;
import com.wanted.api.recruit.service.RecruitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final MemberService memberService;
    private final RecruitService recruitService;

    @Transactional
    public Long createApplication(Long memberId, Long recruitId) {
        validateApplicationCount(memberId, recruitId);

        Member member = memberService.findMemberById(memberId);
        Recruit recruit = recruitService.findRecruitById(recruitId);
        Application application = Application.of(member, recruit);
        return applicationRepository.save(application).getId();
    }

    /**
     * 해당 채용공고에 지원한 내역이 있는지 체크.
     */
    private void validateApplicationCount(Long memberId, Long recruitId) {
        long applicationCount = applicationRepository.countByMemberIdAndRecruitId(memberId, recruitId);
        if (applicationCount > 0) {
            throw new AlreadyAppliedException();
        }
    }
}
