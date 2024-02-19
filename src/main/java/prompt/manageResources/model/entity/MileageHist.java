package prompt.manageResources.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import prompt.manageResources.model.enums.mileage.UpdateReason;

import java.time.Instant;

@Entity
@Table(name = "mileage_hist")
@Getter
@Setter
@NoArgsConstructor
public class MileageHist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int amount;

    private int used_amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "update_reason")
    private UpdateReason updateReason;

    @UpdateTimestamp
    private Instant updateDt;

    @CreationTimestamp
    private Instant createDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @OneToOne
    @JoinColumn(name = "equipment_request_id")
    private AccountEquipment accountEquipment;
}


