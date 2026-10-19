package org.MY_APP.main;

import jakarta.servlet.http.HttpSession;
import org.MY_APP.main.model.User;
import org.MY_APP.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // ✅ Show login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public String handleLogin(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session
    ) {
        User user = userService.findByEmail(email);

        if (user == null || !user.getPassword().equals(password)) {
            return "redirect:/login?error=true";
        }

        // ✅ Store user into session
        session.setAttribute("loggedUser", user);

        // ✅ SHOW AGREEMENT POPUP ON NEXT PAGE LOAD
        session.setAttribute("needsAgreement", true);

        return "redirect:/home";
    }

    // ✅ SIGNUP + AUTO LOGIN
    @PostMapping("/signup")
    public String handleSignup(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session   // 🔥 ΠΡΟΣΘΗΚΗ
    ) {
        boolean created = userService.createUser(email, password, fullName);

        if (!created) {
            return "redirect:/login?exists=true"; // email already in use
        }

        // ✅ Βρες τον χρήστη που μόλις δημιουργήθηκε
        User newUser = userService.findByEmail(email);

        // ✅ AUTO LOGIN — μπαίνει κατευθείαν στο session
        session.setAttribute("loggedUser", newUser);

        // ✅ SHOW AGREEMENT POPUP ON NEXT PAGE LOAD
        session.setAttribute("needsAgreement", true);

        return "redirect:/home"; // ✅ μπαίνει κατευθείαν μέσα
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();   // 🔥 καθαρίζει ΤΑ ΠΑΝΤΑ από το session
        return "redirect:/home";
    }
}
