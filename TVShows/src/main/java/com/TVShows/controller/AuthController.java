package com.TVShows.controller;

import com.TVShows.DTO.RegisterRequest;
import com.TVShows.service.ConfirmationTokenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.TVShows.service.AuthService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {	

	private final AuthService authService;
	private final ConfirmationTokenService tokenService;

	@PostMapping("/register")
	public String register(@ModelAttribute RegisterRequest request, Model model) {
		model.addAttribute("user", request);
		authService.register(request);
		return "verification";
	}

	@GetMapping("/confirm")
	public String confirm(@RequestParam("token") String token) {
		tokenService.confirmToken(token);
		return "success";
	}
}
