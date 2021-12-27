package ma.pecherie.dao.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
//@Table(name = "task")
public class AppTask {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
   // @Column(name = "task_id")
    private Long id;
   // @Column(name = "task_name")
    private String taskName;
}
