package prompt.manageResources.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import prompt.manageResources.model.enums.equipment.Type;
import prompt.manageResources.model.enums.accountEquipment.RequestStatus;

import java.time.Instant;

@Entity
@Table(name = "accounts_equipments")
@Getter
@Setter
@NoArgsConstructor
public class AccountEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_cn", length = 1000)
    private String requestCn;

    @Column(name = "reject_reason", length = 1000)
    private String rejectReason;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Type type;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status", nullable = false)
    private RequestStatus requestStatus;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "confirm_account_id")
    private Account confirmAccount;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @UpdateTimestamp
    private Instant updateDt;

    @CreationTimestamp
    private Instant createDt;
}
