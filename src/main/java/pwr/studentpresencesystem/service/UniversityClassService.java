package pwr.studentpresencesystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.studentpresencesystem.entity.UniversityClass;
import pwr.studentpresencesystem.repository.UniversityClassRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UniversityClassService {

    private final UniversityClassRepository classRepository;

    public void save(final UniversityClass universityClass) {
        universityClass.setAttendances(new HashSet<>());
        universityClass.setStartTime(LocalDateTime.now());
        universityClass.setNumberOfStudents(0);
        universityClass.setActive(false);
        classRepository.save(universityClass);
    }

    public void update(final UniversityClass universityClass) {
        classRepository.save(universityClass);
    }

    public List<UniversityClass> getClassesList() {
        return classRepository.findAll();
    }

    public UniversityClass getClassById(final int id) {
        return classRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public boolean doesActiveClassExist() {
        return classRepository.existsByActiveIsTrue();
    }

    public Optional<UniversityClass> getActiveClass() {
        return classRepository.findAllByActiveIsTrue();
    }

    public List<UniversityClass> getTeachersClasses(final String login) {
        return classRepository.findAllByTeacherLogin(login);
    }
}
