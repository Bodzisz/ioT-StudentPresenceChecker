package pwr.studentpresencesystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    @ToString.Exclude
    @JoinColumn(name = "FK_StudentID")
    private User student;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "FK_ClassesID")
    private UniversityClass universityClass;
    private LocalDateTime comingTime;
    private LocalDateTime leavingTime;
    private String comments;

    public String getFormattedComingTime() {
        return comingTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public String getFormattedLeavingTime() {
        return leavingTime != null ? leavingTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) : "";
    }
}
