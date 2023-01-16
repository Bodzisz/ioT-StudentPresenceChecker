package pwr.studentpresencesystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}
