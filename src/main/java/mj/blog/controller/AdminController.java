package mj.blog.controller;

import lombok.RequiredArgsConstructor;
import mj.blog.dto.response.member.MemberResponseDto;
import mj.blog.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    /**
     * 모든 사용자 목록 조회
     */
    @GetMapping("/member")
    public ResponseEntity<List<MemberResponseDto>> getAllMembers() {
        List<MemberResponseDto> members = adminService.getAllMembers();
        return ResponseEntity.status(HttpStatus.OK).body(members);
    }

    /**
     * 특정 사용자 삭제
     */
    @DeleteMapping("/member/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        adminService.deleteMember(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
