package sustech.learn.consult.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(
        name = "tutors"
//        ,
//        indexes = {
//                @Index(name = "users_username", columnList = "username", unique = true),
//                @Index(name = "users_email", columnList = "email", unique = true)
//        }
)
@Data
public class Tutor {

    public enum Status {
        registering, normal, remove
    }

    public enum Sort {
        tutor, prof
    }

    @Id
    @GeneratedValue(generator = "contents",strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String advisorName;
    @Column
    private String gender;
    @Column
    private String college;
    @Column
    private String grade;
    @Column
    private String slogen;
    @Column
    private String advisorId;

    @Column
    private String intro;
    @Column
    private String prof;
    @Column
    private String prefer;

    @Column
    private String avatar;

    @Column
    private String place;


    private Sort sort;

    private Status status;
}
