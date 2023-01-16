package pwr.studentpresencesystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String pesel;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private Date date_of_birth;
    private String email;
    private String phoneNumber;
    private String indexNumber;
    private String cardNumber;
    @ToString.Exclude
    @OneToMany(mappedBy = "student")
    private Set<Attendance> attendances;

}
