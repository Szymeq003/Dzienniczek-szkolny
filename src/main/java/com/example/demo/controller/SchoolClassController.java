package com.example.demo.controller;

import com.example.demo.model.SchoolClass;
import com.example.demo.repository.SchoolClassRepository;
import com.example.demo.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/classes")
@RequiredArgsConstructor
public class SchoolClassController {

    private final SchoolClassRepository schoolClassRepository;
    private final TeacherRepository teacherRepository;

    @GetMapping
    public String listClasses(Model model) {
        model.addAttribute("classes", schoolClassRepository.findAll());
        return "classes";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("newClass", new SchoolClass());
        model.addAttribute("teachers", teacherRepository.findAll());
        return "add_class";
    }

    @PostMapping("/add")
    public String addClass(@ModelAttribute SchoolClass newClass) {
        schoolClassRepository.save(newClass);
        return "redirect:/classes";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        SchoolClass schoolClass = schoolClassRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid class Id:" + id));
        model.addAttribute("schoolClass", schoolClass);
        model.addAttribute("teachers", teacherRepository.findAll());
        return "edit_class";
    }

    @PostMapping("/edit/{id}")
    public String updateClass(@PathVariable Long id, @ModelAttribute SchoolClass schoolClass) {
        schoolClass.setId(id);
        schoolClassRepository.save(schoolClass);
        return "redirect:/classes";
    }
}
