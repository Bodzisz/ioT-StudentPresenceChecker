package pwr.studentpresencesystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.studentpresencesystem.entity.Attendance;
import pwr.studentpresencesystem.entity.UniversityClass;
import pwr.studentpresencesystem.entity.User;
import pwr.studentpresencesystem.repository.AttendanceRepository;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserService userService;
    private final UniversityClassService universityClassService;

    public void saveAttendance(String payload) {
        Optional<User> userByCardNumber = userService.findUserByCardNumber(payload);
        Optional<UniversityClass> activeClass = universityClassService.getActiveClass();
        if (userByCardNumber.isEmpty() || activeClass.isEmpty())
            throw new RuntimeException();

        Attendance attendance = Attendance.builder()
                .student(userByCardNumber.get())
                .comingTime(LocalDateTime.now())
                .universityClass(activeClass.get())
                .build();
        attendanceRepository.save(attendance);
    }

    public List<Attendance> getStudentAttendances(final Principal principal) {
        return attendanceRepository.findAllByStudent_Name(principal.getName());
    }

}
