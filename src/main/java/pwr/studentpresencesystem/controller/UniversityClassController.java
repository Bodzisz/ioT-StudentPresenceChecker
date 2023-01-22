package pwr.studentpresencesystem.controller;

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
    public String getClassAttendance(@PathVariable("id") final int id, final Model model) {
        final UniversityClass clazz = universityClassService.getClassById(id);
        final Set<Attendance> attendances = clazz.getAttendances();
        boolean showActivateButton = !clazz.isActive() && clazz.getEndTime() == null;
        boolean showEndButton = clazz.isActive();
        model.addAttribute("class", clazz);
        model.addAttribute("showActivateButton", showActivateButton);
        model.addAttribute("showEndButton", showEndButton);
        model.addAttribute("attendances", attendances);
        return "class_attendance";
    }

    @GetMapping("/class/{id}/activate")
    public String activateClass(@PathVariable("id") final int id) {
        System.out.println(universityClassService.doesActiveClassExist());
        final UniversityClass clazz = universityClassService.getClassById(id);
        if(!universityClassService.doesActiveClassExist() &&
                clazz.getEndTime() == null) {
            clazz.setActive(true);
            universityClassService.update(clazz);
        }
        return "redirect:/class/show/" + id;
    }

    @GetMapping("/class/{id}/deactivate")
    public String endClass(@PathVariable("id") final int id) {
        final UniversityClass clazz = universityClassService.getClassById(id);
        clazz.setActive(false);
        clazz.setEndTime(LocalDateTime.now());
        universityClassService.update(clazz);
        return "redirect:/class/show/" + id;
    }

    @GetMapping("/classes")
    public String getClasses(final Model model) {
        model.addAttribute("classes", universityClassService.getClassesList());
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
        final User teacher = userService.findUserByLogin(principal.getName())
                .orElseThrow(RuntimeException::new);
        universityClass.setTeacher(teacher);
        universityClassService.save(universityClass);
        return "redirect:/classes";
    }
}
