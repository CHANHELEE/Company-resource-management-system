package prompt.manageResources.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import prompt.manageResources.model.enums.equipment.Status;
import prompt.manageResources.model.enums.equipment.Type;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "equipment")
@Getter
@Setter
@NoArgsConstructor
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uniqueNum;

    private String name;

    private String specification;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Type type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    private LocalDate purchasedDt;

    @UpdateTimestamp
    private Instant updateDt;

    @CreationTimestamp
    private Instant createDt;

    @OneToMany(mappedBy = "equipment", fetch = FetchType.LAZY)
    private List<EquipmentOwnershipHist> equipmentOwnershipHists = new ArrayList<>();

    @OneToMany(mappedBy = "equipment", fetch = FetchType.LAZY)
    private List<AccountEquipment> accountEquipments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
}

//equipment_hist 테이블과 1:n 관계 ,
//equipment_hist 1 - N equipment_hist_account N - 1 account
//                     join 테이블 생성