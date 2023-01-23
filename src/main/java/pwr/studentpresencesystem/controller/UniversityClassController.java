package pwr.studentpresencesystem.controller;

import jakarta.persistence.metamodel.ListAttribute;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pwr.studentpresencesystem.entity.Attendance;
import pwr.studentpresencesystem.entity.UniversityClass;
import pwr.studentpresencesystem.entity.User;
import pwr.studentpresencesystem.service.UniversityClassService;
import pwr.studentpresencesystem.service.UserService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class UniversityClassController {

    private UniversityClassService universityClassService;
    private UserService userService;

    public UniversityClassController(UniversityClassService universityClassService, UserService userService) {
        this.universityClassService = universityClassService;
        this.userService = userService;
    }

    @GetMapping("/class/show/{id}")
    public String getClassAttendance(@PathVariable("id") final int id, final Model model,
                                     final Principal principal) {
        final UniversityClass clazz = universityClassService.getClassById(id);
        if(!clazz.getTeacher().getLogin().equals(principal.getName())) {
            throw new AccessDeniedException("You cannot access this page");
        }
        final List<Attendance> attendances = clazz.getAttendances();
        boolean showActivateButton = !clazz.isActive() && clazz.getEndTime() == null;
        boolean showEndButton = clazz.isActive();
        model.addAttribute("class", clazz);
        model.addAttribute("showActivateButton", showActivateButton);
        model.addAttribute("showEndButton", showEndButton);
        model.addAttribute("attendances", attendances);
        return "class_attendance";
    }

    @GetMapping("/class/{id}/activate")
    public String activateClass(@PathVariable("id") final int id, final Principal principal) {
        final UniversityClass clazz = universityClassService.getClassById(id);
        if(!clazz.getTeacher().getLogin().equals(principal.getName())) {
            throw new AccessDeniedException("You cannot access this page");
        }
        if(!universityClassService.doesActiveClassExist() &&
                clazz.getEndTime() == null) {
            clazz.setActive(true);
            universityClassService.update(clazz);
        }
        return "redirect:/class/show/" + id;
    }

    @GetMapping("/class/{id}/deactivate")
    public String endClass(@PathVariable("id") final int id, final Principal principal) {
        final UniversityClass clazz = universityClassService.getClassById(id);
        if(!clazz.getTeacher().getLogin().equals(principal.getName())) {
            throw new AccessDeniedException("You cannot access this page");
        }
        clazz.setActive(false);
        clazz.setEndTime(LocalDateTime.now());
        universityClassService.update(clazz);
        return "redirect:/class/show/" + id;
    }

    @GetMapping("/classes")
    public String getClasses(final Model model, final Principal principal) {
        model.addAttribute("classes",
                universityClassService.getTeachersClasses(principal.getName()));
        return "classes";
    }

    @GetMapping("/class/create")
    public String createClass(final Model model) {
        model.addAttribute("class", new UniversityClass());
        return "create-class";
    }

    @PostMapping("/class/create")
    public String createClass(final UniversityClass universityClass,
                              final Principal principal) {
        System.out.println(universityClass);
        final User teacher = userService.findUserByLogin(principal.getName())
                .orElseThrow(RuntimeException::new);
        universityClass.setTeacher(teacher);
        universityClassService.save(universityClass);

        return "redirect:/classes";
    }
}
