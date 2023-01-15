package pwr.studentpresencesystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
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
    @OneToMany(mappedBy = "student")
    private Set<Attendance> attendances;

    public User(String login, String password, String name, String surname, String pesel, Gender gender, Date date_of_birth, String email, String phone, String index, String cardNumber) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.phoneNumber = phone;
        this.indexNumber = index;
        this.cardNumber = cardNumber;
    }
}
