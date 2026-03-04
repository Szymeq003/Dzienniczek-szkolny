package com.example.demo.controller;

import com.example.demo.model.Teacher;
import com.example.demo.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("teachers", teacherService.findAll());
        model.addAttribute("newTeacher", new Teacher());
        return "index";
    }

    @PostMapping("/add")
    public String addTeacher(@ModelAttribute Teacher newTeacher) {
        teacherService.save(newTeacher);
        return "redirect:/";
    }
}
