package pwr.studentpresencesystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pwr.studentpresencesystem.entity.Attendance;
import pwr.studentpresencesystem.service.AttendanceService;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @GetMapping("/attendances")
    public String getStudentAttendance(final Principal principal, final Model model) {
        List<Attendance> studentAttendances = attendanceService.getStudentAttendances(principal);
        model.addAttribute("attendances", studentAttendances);
        return "student_attendance";
    }
}
