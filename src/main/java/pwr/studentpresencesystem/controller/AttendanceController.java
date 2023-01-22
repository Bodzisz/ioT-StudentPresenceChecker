package pwr.studentpresencesystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AttendanceController {

    @GetMapping("/student/{id}")
    public String getStudentAttendance() {
        return "student_attendance";
    }
}
