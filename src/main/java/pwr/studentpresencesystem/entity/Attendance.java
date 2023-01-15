package pwr.studentpresencesystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "attendances")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_StudentID")
    private User student;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_ClassesID")
    private UniversityClass universityClass;
    private LocalDateTime comingTime;
    private LocalDateTime leavingTime;
    private String comments;

    public Attendance(User student, UniversityClass universityClass, LocalDateTime comingTime, LocalDateTime leavingTime, String comments) {
        this.student = student;
        this.universityClass = universityClass;
        this.comingTime = comingTime;
        this.leavingTime = leavingTime;
        this.comments = comments;
    }
}
