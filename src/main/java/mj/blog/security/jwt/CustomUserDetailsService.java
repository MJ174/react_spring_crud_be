package mj.blog.security.jwt;

import mj.blog.common.exception.ResourceNotFoundException;
import mj.blog.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * DaoAuthenticationProvider 구현
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.memberRepo.findByEmail(username).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Email : ", username));
    }
}
