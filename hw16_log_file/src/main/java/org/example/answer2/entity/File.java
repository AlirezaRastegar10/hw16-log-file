package org.example.answer2.entity;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
public class File {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String courseName;
    private String studentName;
    private Long timeStamp;
    private double rating;
    private String comment;

    public File() {
    }

    public File(String courseName, String studentName, Long timeStamp, double rating, String comment) {
        this.courseName = courseName;
        this.studentName = studentName;
        this.timeStamp = timeStamp;
        this.rating = rating;
        this.comment = comment;
    }
}