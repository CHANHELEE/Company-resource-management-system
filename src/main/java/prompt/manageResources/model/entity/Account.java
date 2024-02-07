package prompt.manageResources.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import prompt.manageResources.model.enums.Auth;
import prompt.manageResources.model.enums.Dept;
import prompt.manageResources.model.enums.Position;
import prompt.manageResources.util.BooleanToYNConverter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE account SET is_deleted = 'Y' WHERE id=?")
public class Account {

    public static final String ANONYMOUS = "ANONYMOUS";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @UpdateTimestamp
    private Instant updateDt;

    @CreationTimestamp
    private Instant createDt;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "author")
    private Auth auth;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private Position position;

    @Enumerated(EnumType.STRING)
    @Column(name = "dept")
    private Dept dept;

    @Column(name = "is_deleted")
    @Convert(converter = BooleanToYNConverter.class)
    private boolean is_deleted = false;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<Equipment> equipments = new ArrayList<>();

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<EquipmentOwnershipHist> equipmentOwnershipHists = new ArrayList<>();

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<EquipmentRequest> equipmentRequests = new ArrayList<>();

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<MileageHist> mileageHists = new ArrayList<>();

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "mileage_id", nullable = false)
    private Mileage mileage;


    public void setPassword(String pw) {
        this.password = pw;
    }

}
