import com.YronJack.IronWars.unums.Score;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.security.Timestamp;
import java.time.Duration;
import java.util.List;

@Entity
@Table(name = "exams")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exam_id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Exercise> exercises;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Column(nullable = false, length =50)
    @NotNull(message= "The Language is mandatory")
    private Language language;

    @ManyToOne
    private Student student;

    private Timestamp startTime;

    private Timestamp endTime;
    @NotNull(message = "Duration is mandatory")
    private Duration duration;

    @Enumerated(EnumType.STRING)
    private Score score;

}