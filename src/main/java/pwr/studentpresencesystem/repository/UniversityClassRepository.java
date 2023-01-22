package pwr.studentpresencesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.studentpresencesystem.entity.UniversityClass;

import java.util.List;
import java.util.Optional;

@Repository
public interface UniversityClassRepository extends JpaRepository<UniversityClass, Integer> {

    boolean existsByActiveIsTrue();

    Optional<UniversityClass> findAllByActiveIsTrue();

    List<UniversityClass> findAllByTeacherLogin(final String login);
}
