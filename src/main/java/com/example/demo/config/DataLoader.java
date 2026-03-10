package com.example.demo.config;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final GradeRepository gradeRepository;
    private final SchoolClassRepository schoolClassRepository;

    @Override
    public void run(String... args) {
        if (teacherRepository.count() > 0) {
            return;
        }

        Teacher t1 = new Teacher();
        t1.setFirstName("Anna");
        t1.setLastName("Kowalska");
        t1.setEmail("a.kowalska@szkola.pl");
        teacherRepository.save(t1);

        Teacher t2 = new Teacher();
        t2.setFirstName("Tomasz");
        t2.setLastName("Nowak");
        t2.setEmail("t.nowak@szkola.pl");
        teacherRepository.save(t2);

        Teacher t3 = new Teacher();
        t3.setFirstName("Maria");
        t3.setLastName("Wisniewska");
        t3.setEmail("m.wisniewska@szkola.pl");
        teacherRepository.save(t3);

        Teacher t4 = new Teacher();
        t4.setFirstName("Jan");
        t4.setLastName("Zielinski");
        t4.setEmail("j.zielinski@szkola.pl");
        teacherRepository.save(t4);

        Subject s1 = new Subject();
        s1.setName("Matematyka");
        s1.setTeacher(t1);
        subjectRepository.save(s1);

        Subject s2 = new Subject();
        s2.setName("Jezyk polski");
        s2.setTeacher(t2);
        subjectRepository.save(s2);

        Subject s3 = new Subject();
        s3.setName("Fizyka");
        s3.setTeacher(t3);
        subjectRepository.save(s3);

        Subject s4 = new Subject();
        s4.setName("Informatyka");
        s4.setTeacher(t4);
        subjectRepository.save(s4);

        Subject s5 = new Subject();
        s5.setName("Historia");
        s5.setTeacher(t2);
        subjectRepository.save(s5);

        SchoolClass class3A = schoolClassRepository.findByName("3A").orElseGet(() -> {
            SchoolClass c = new SchoolClass();
            c.setName("3A");
            return schoolClassRepository.save(c);
        });

        SchoolClass class3B = schoolClassRepository.findByName("3B").orElseGet(() -> {
            SchoolClass c = new SchoolClass();
            c.setName("3B");
            return schoolClassRepository.save(c);
        });

        SchoolClass class2A = schoolClassRepository.findByName("2A").orElseGet(() -> {
            SchoolClass c = new SchoolClass();
            c.setName("2A");
            return schoolClassRepository.save(c);
        });

        Student st1 = new Student();
        st1.setFirstName("Jakub");
        st1.setLastName("Lewandowski");
        st1.setSchoolClass(class3A);
        st1.setEmail("j.lewandowski@uczen.pl");
        studentRepository.save(st1);

        Student st2 = new Student();
        st2.setFirstName("Maja");
        st2.setLastName("Kaminska");
        st2.setSchoolClass(class3A);
        st2.setEmail("m.kaminska@uczen.pl");
        studentRepository.save(st2);

        Student st3 = new Student();
        st3.setFirstName("Piotr");
        st3.setLastName("Wozniak");
        st3.setSchoolClass(class3B);
        st3.setEmail("p.wozniak@uczen.pl");
        studentRepository.save(st3);

        Student st4 = new Student();
        st4.setFirstName("Aleksandra");
        st4.setLastName("Dabrowska");
        st4.setSchoolClass(class3B);
        st4.setEmail("a.dabrowska@uczen.pl");
        studentRepository.save(st4);

        Student st5 = new Student();
        st5.setFirstName("Krzysztof");
        st5.setLastName("Szymanski");
        st5.setSchoolClass(class2A);
        st5.setEmail("k.szymanski@uczen.pl");
        studentRepository.save(st5);

        Student st6 = new Student();
        st6.setFirstName("Natalia");
        st6.setLastName("Wojciechowska");
        st6.setSchoolClass(class2A);
        st6.setEmail("n.wojciechowska@uczen.pl");
        studentRepository.save(st6);

        addGrade(st1, s1, 5, LocalDate.of(2026, 2, 10));
        addGrade(st1, s2, 4, LocalDate.of(2026, 2, 12));
        addGrade(st1, s3, 3, LocalDate.of(2026, 2, 15));
        addGrade(st1, s4, 6, LocalDate.of(2026, 2, 20));

        addGrade(st2, s1, 4, LocalDate.of(2026, 2, 10));
        addGrade(st2, s2, 5, LocalDate.of(2026, 2, 12));
        addGrade(st2, s3, 4, LocalDate.of(2026, 2, 15));
        addGrade(st2, s5, 5, LocalDate.of(2026, 2, 18));

        addGrade(st3, s1, 2, LocalDate.of(2026, 2, 10));
        addGrade(st3, s2, 3, LocalDate.of(2026, 2, 12));
        addGrade(st3, s4, 4, LocalDate.of(2026, 2, 22));

        addGrade(st4, s1, 5, LocalDate.of(2026, 2, 10));
        addGrade(st4, s3, 5, LocalDate.of(2026, 2, 15));
        addGrade(st4, s5, 6, LocalDate.of(2026, 2, 18));

        addGrade(st5, s1, 3, LocalDate.of(2026, 3, 1));
        addGrade(st5, s2, 4, LocalDate.of(2026, 3, 2));
        addGrade(st5, s4, 5, LocalDate.of(2026, 3, 3));

        addGrade(st6, s1, 4, LocalDate.of(2026, 3, 1));
        addGrade(st6, s2, 2, LocalDate.of(2026, 3, 2));
        addGrade(st6, s3, 3, LocalDate.of(2026, 3, 3));

        System.out.println("=== Zaladowano przykladowe dane! ===");
        System.out.println("Teachers (Nauczyciele): " + teacherRepository.count());
        System.out.println("Subjects (Przedmioty): " + subjectRepository.count());
        System.out.println("Students (Uczniowie): " + studentRepository.count());
        System.out.println("Grades (Oceny): " + gradeRepository.count());
    }

    private void addGrade(Student student, Subject subject, int value, LocalDate date) {
        Grade grade = new Grade();
        grade.setStudent(student);
        grade.setSubject(subject);
        grade.setValue(value);
        grade.setDate(date);
        gradeRepository.save(grade);
    }
}
