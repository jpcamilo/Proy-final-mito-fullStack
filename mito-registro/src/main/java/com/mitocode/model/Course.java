package  com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCourse;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 5)
    private String acronym;

    @Column(nullable = false)
    private boolean state;
}
