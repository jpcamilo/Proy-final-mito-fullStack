package com.mitocode.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EnrrollmentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnrolllmentDetail;

    @ManyToOne
    @JoinColumn(name = "id_course", nullable = false, foreignKey = @ForeignKey(name = "FK_ENROLLDETAIL_COURSE"))
    private Course course;

    @Column(nullable = false, length = 5)
    private String classroom;

    @ManyToOne
    @JoinColumn(name = "id_enrollment", nullable = false, foreignKey = @ForeignKey(name = "FK_ENROLLDETAIL_ENROLLMENT"))
    private Enrollment enrollment;

}
