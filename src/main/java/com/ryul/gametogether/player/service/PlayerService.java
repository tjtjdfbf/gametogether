package com.ryul.gametogether.player.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ryul.gametogether.player.DTO.PlayerSignupRequest;
import com.ryul.gametogether.player.entity.Player;
import com.ryul.gametogether.player.repository.PlayerRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor  
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;

    public String addPlayer(PlayerSignupRequest request) {
        
        if (playerRepository.existsByLoginId(request.getLoginId())) {
            return "중복된 아이디입니다.";
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Player player = new Player(request.getLoginId(), encodedPassword, request.getName());

        playerRepository.save(player);
        
        return "회원가입 완료"; 
    }
}
