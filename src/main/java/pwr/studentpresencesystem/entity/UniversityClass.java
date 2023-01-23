package pwr.studentpresencesystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
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
    private boolean active;
    @Column(name = "est_number_of_students")
    private int numberOfStudents;
    private String comments;
    @OneToMany(mappedBy = "universityClass")
    @ToString.Exclude
    private List<Attendance> attendances;
}
