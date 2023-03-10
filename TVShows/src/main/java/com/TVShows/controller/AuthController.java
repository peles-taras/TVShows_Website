package com.TVShows.controller;

import com.TVShows.DTO.RegisterRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.TVShows.service.AuthService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {	

	private final AuthService service;

	@PostMapping("/register")
	public String register(@ModelAttribute RegisterRequest request, Model model) {
		model.addAttribute("user", request);
		service.register(request);
		return "auth";
	}
}
