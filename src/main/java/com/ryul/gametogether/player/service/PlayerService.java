package com.ryul.gametogether.player.service;

import com.ryul.gametogether.jwt.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ryul.gametogether.exception.InvalidCredentialsException;
import com.ryul.gametogether.player.DTO.LoginRequest;
import com.ryul.gametogether.player.DTO.LoginResponse;
import com.ryul.gametogether.player.DTO.SignupRequest;
import com.ryul.gametogether.player.entity.Player;
import com.ryul.gametogether.player.repository.PlayerRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor  
public class PlayerService {

    private final JwtService jwtService;
    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;

    public Long addPlayer(SignupRequest request) {
        
        // if (playerRepository.existsByLoginId(request.getLoginId())) {
        //     return "중복된 아이디입니다.";
        // } 예외처리 필요

        String encodedPassword = passwordEncoder.encode(request.password());

        Player player = new Player(request.loginId(), encodedPassword, request.name());

        player = playerRepository.save(player);
        
        return player.getId(); 
    }

    public LoginResponse login(LoginRequest request) {

        Player foundPlayer = playerRepository.findByLoginId(request.loginId())
            .orElseThrow(InvalidCredentialsException::new);

        String accessToken = "";
        if(passwordEncoder.matches(request.password(), foundPlayer.getPassword())) {
            accessToken = jwtService.createAccessToken(foundPlayer);
        } else {throw new InvalidCredentialsException();}
        
        return new LoginResponse(
            accessToken,
            "Bearer",
            jwtService.getAccessTokenValiditySeconds(),
            foundPlayer.getId(),
            foundPlayer.getName()
        );
    }
}
