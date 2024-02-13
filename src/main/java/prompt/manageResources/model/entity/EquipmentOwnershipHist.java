package prompt.manageResources.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import prompt.manageResources.model.enums.equipment.OwnershipChangeReason;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "ownership_change_reason", nullable = false)
    private OwnershipChangeReason ownershipChangeReason;

    @Column(length = 1000)
    private String comments;

    @UpdateTimestamp
    private Instant updateDt;

    @CreationTimestamp
    private Instant createDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;


}
