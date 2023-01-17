package pwr.studentpresencesystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.studentpresencesystem.entity.Attendance;
import pwr.studentpresencesystem.entity.UniversityClass;
import pwr.studentpresencesystem.entity.User;
import pwr.studentpresencesystem.repository.AttendanceRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserService userService;

    public void saveAttendance(String payload) {
        Optional<User> userByCardNumber = userService.findUserByCardNumber(payload);
        if (userByCardNumber.isEmpty())
            return;

        Attendance attendance = Attendance.builder()
                .student(userByCardNumber.get())
                .comingTime(LocalDateTime.now())
                .universityClass(new UniversityClass()) //TODO Trzeba poprawic klase
                .build();
       // attendanceRepository.save(attendance);
    }

}
