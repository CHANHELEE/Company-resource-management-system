package prompt.manageResources.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "mileage")
@Getter
@Setter
@NoArgsConstructor
public class Mileage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int amount;

    @UpdateTimestamp
    private Instant updateDt;

    @CreationTimestamp
    private Instant createDt;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

}
