package com.example.demo.controller;

import com.example.demo.model.Teacher;
import com.example.demo.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("teachers", teacherService.findAll());
        model.addAttribute("newTeacher", new Teacher());
        return "index";
    }

    @PostMapping("/add")
    public String addTeacher(@ModelAttribute Teacher newTeacher) {
        teacherService.save(newTeacher);
        return "redirect:/teachers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Teacher teacher = teacherService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid teacher Id:" + id));
        model.addAttribute("teacher", teacher);
        return "edit_teacher";
    }

    @PostMapping("/edit/{id}")
    public String updateTeacher(@PathVariable Long id, @ModelAttribute Teacher teacher) {
        teacher.setId(id);
        teacherService.save(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        teacherService.delete(id);
        return "redirect:/teachers";
    }
}
