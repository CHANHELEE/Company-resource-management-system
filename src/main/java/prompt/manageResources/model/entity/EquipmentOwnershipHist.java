package prompt.manageResources.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "equipment_ownership_hist")
@Getter
@Setter
@NoArgsConstructor
public class EquipmentOwnershipHist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String change_reason;

    @UpdateTimestamp
    private Instant updateDt;

    @CreationTimestamp
    private Instant createDt;

    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


}
