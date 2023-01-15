package pwr.studentpresencesystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "classes")
public class UniversityClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "FK_TeacherID")
    private User teacher;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String roomNumber;
    @Column(name = "est_number_of_students")
    private int numberOfStudents;
    private String comments;
    @OneToMany(mappedBy = "universityClass")
    private Set<Attendance> attendances;

    public UniversityClass(User teacher, String name, LocalDateTime startTime, LocalDateTime endTime, String roomNumber, int numberOfStudents, String comments) {
        this.teacher = teacher;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomNumber = roomNumber;
        this.numberOfStudents = numberOfStudents;
        this.comments = comments;
    }
}
