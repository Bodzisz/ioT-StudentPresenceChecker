package pwr.studentpresencesystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pwr.studentpresencesystem.repository.UniversityClassRepository;

@Service
@RequiredArgsConstructor
public class UniversityClassService {

    private final UniversityClassRepository classRepository;

    public void addNewClass(String className, String roomNumber) {

    }
}
