package pwr.studentpresencesystem.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AttendanceController {

    @GetMapping("/class/{id}")
    public String getClassAttendance() {
        SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return "class_attendance";
    }

    @GetMapping("/student/{id}")
    public String getStudentAttendance() {
        return "student_attendance";
    }
}
