package com.game.controller;

import com.game.model.User;
import com.game.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

@Controller
public class WebController {
    private final UserService userService;

    @Autowired
    public WebController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String helloPage() {
        return "hello";
    }

    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User byName = userService.findByUsername(user.getUsername());

        if (byName != null) {
            model.addAttribute("message", "User Exists");
            return "registration";
        }
        user.setTried(new LinkedHashMap<>());
        user.setRatio(0d);
        user.setNumberOfGame(0);
        userService.addUser(user);

        return "redirect:/login";
    }

    @GetMapping("/start")
    public String startGame(@AuthenticationPrincipal User user) {
        user.startGame();
        return "start";
    }

    @PostMapping("/start")
    public String turn(Model model, @AuthenticationPrincipal User user, String number) {
        List<String> temp = Arrays.asList(number.split(""));
        List<Integer> userNumbers = new LinkedList<>();

        for (int i = 0; i < 4; i++)
            userNumbers.add(Integer.parseInt(temp.get(i)));

        if (user.trying(userNumbers)) {
            model.addAttribute("message", "You won! Input number to start new game");
            model.addAttribute("tried", user.getTried());
            user.endGame();
            userService.save(user);
            return "start";
        }

        model.addAttribute("message", "Try again");
        model.addAttribute("tried", user.getTried());

        return "start";
    }
}
