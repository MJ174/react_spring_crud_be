package mj.blog.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mj.blog.common.exception.ResourceNotFoundException;
import mj.blog.dto.response.member.MemberResponseDto;
import mj.blog.entity.Member;
import mj.blog.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {

    private final MemberRepository memberRepository;

    /**
     * 모든 사용자 조회
     */
    public List<MemberResponseDto> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(MemberResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * 특정 사용자 삭제
     * @param id 사용자 ID
     */
    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Member", "ID", String.valueOf(id))
        );
        memberRepository.delete(member);
    }
}
