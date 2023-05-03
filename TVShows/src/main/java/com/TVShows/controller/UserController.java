package com.TVShows.controller;

import com.TVShows.DTO.ImageEncoder;
import com.TVShows.DTO.WatchingStatusRequest;
import com.TVShows.domain.TVShow;
import com.TVShows.domain.User;
import com.TVShows.domain.UsersShowProgress;
import com.TVShows.enums.ViewerStatus;
import com.TVShows.exceptions.WrongOperationException;
import com.TVShows.repo.UsersShowProgressRepo;
import com.TVShows.service.TVShowService;
import com.TVShows.service.UserService;
import com.TVShows.service.UsersShowProgressService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get/{id}")
    public void getUserInfo(@PathVariable Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
    }

    @PostMapping("/{id}/image/upload")
    public void uploadImage(@ModelAttribute("image") ImageEncoder image, @PathVariable Long id,
                            Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
        User authenticatedUser = (User) model.getAttribute("authenticatedUser");

        if (authenticatedUser != null && authenticatedUser.getId().equals(id)) {
            User toUpdate = image.encodeImage(authenticatedUser);
            userService.updateUser(toUpdate);
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
}
