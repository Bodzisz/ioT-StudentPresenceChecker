package pwr.studentpresencesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.studentpresencesystem.entity.Attendance;
import pwr.studentpresencesystem.entity.UniversityClass;
import pwr.studentpresencesystem.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    List<Attendance> findAllByStudentLogin(String login);

    Optional<Attendance> findByStudentAndUniversityClass(User student, UniversityClass universityClass);
}
