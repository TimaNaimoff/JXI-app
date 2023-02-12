package edu.betty.JXIpet.business;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
public class Person {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="person_id")
    @Getter
    @Setter
    private Integer id;

    @NotEmpty(message="Stupid trying,name should not be empty!")
    @Size(min=2,max=30,message="Noob,name should be between 2 and 30 characters!")
    @Column(name="name")
    @Getter
    @Setter
    private String name;
//    @Min(value=0,message="Let's go,age should be greatet that 0")
    @Column(name="age")

    private int age;
//    @NotEmpty(message="Stupid trying,name should not be empty!")
//    @Email(message="Facepalm...")
    @Column(name="email")
    @Getter
    @Setter
    private String email;
    @Column(name="password")
    @Getter
    @Setter
    private Integer password;
    @Column(name="date_of_birth")
//    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    @Getter
    @Setter
    private LocalDate dateOfBirth;
    @Column(name="created_time")
    @Getter
    @Setter
    private LocalDateTime createdTime;
    @OneToMany(mappedBy="person")
    @Getter
    @Setter
    private List<Book> list;
    @Getter
    @Setter
    @Enumerated(EnumType.ORDINAL)
    private Mood mood;
    @Transient
    @Getter
    @Setter
    private Boolean status;

    public Person(Integer id,String name,int age,String email,LocalDate dateOfBirth/*String address*/) {
        this.id=id;
        this.name=name;
        this.age=age;
        this.email=email;
        this.dateOfBirth=dateOfBirth;
    }


    public Person(String name, int age, String email/*String address*/) {
        this.name=name;
        this.age=age;
        this.email=email;
//        this.address=address;
    }
    public Person(){}

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
