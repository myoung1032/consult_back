package sustech.learn.consult.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(
        name = "records2"
//        ,
//        indexes = {
//                @Index(name = "users_username", columnList = "username", unique = true),
//                @Index(name = "users_email", columnList = "email", unique = true)
//        }
)
@Data
public class Record {
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
    private Long semesterId;
    @Column
    private Long weedId;
    @Column
    private Long dayId;
    @Column
    private Long timeId;
    @Column
    private String remark;

    @Column
    private String advisorName;
    @Column
    private String advisorId;
    @Column
    private String studentName;
    @Column
    private String studentId;
    @Column
    private String studentPhone;
    @Column
    private String consultContent;

    @Column
    private String college;

    @Column
    private String qqNumber;
    @Column
    private String place;

    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    @UpdateTimestamp
    private Date cancelAt;

    private Status status;
    private Sort sort;

}
