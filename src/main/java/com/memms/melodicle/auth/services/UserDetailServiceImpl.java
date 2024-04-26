package com.memms.melodicle.auth.services;

import com.memms.melodicle.domain.entities.UserEntity;
import com.memms.melodicle.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return UserDetailsImpl.build(user);
    }

//    @Transactional
//    public UserDetails loadUserByUserId(Long id) throws UsernameNotFoundException{
//        UserEntity user = userRepository.findById(id)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
//
//        return UserDetailsImpl.build(user);
//    }
}
