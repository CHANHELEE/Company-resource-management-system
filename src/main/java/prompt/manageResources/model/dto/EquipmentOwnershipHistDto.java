package prompt.manageResources.model.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import prompt.manageResources.model.enums.Dept;
import prompt.manageResources.model.enums.Position;
import prompt.manageResources.model.enums.equipment.OwnershipChangeReason;
import prompt.manageResources.model.enums.equipment.Status;
import prompt.manageResources.model.enums.equipment.Type;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
public class EquipmentOwnershipHistDto {

    private Long id;

    private OwnershipChangeReason ownershipChangeReason;

    private String comments;

    private Instant updateDt;

    private Instant createDt;

    // equipment information
    private Long equipmentId;

    private String uniqueNum;

    private String name;

    private String specification;

    private Type type;

    private Status status;

    // account information
    private Long accountId;

    private String userId;

    private String userName;

    private Position position;

    private Dept dept;

    // confirm account informantion
    private Long confirmAccountId;

    private String confirmUserName;

    @QueryProjection
    public EquipmentOwnershipHistDto(Long id, OwnershipChangeReason ownershipChangeReason, String comments, Instant updateDt, Instant createDt, Long equipmentId, String uniqueNum, String name, String specification, Type type, Status status, Long accountId, String userId, String userName, Position position, Dept dept, Long confirmAccountId, String confirmUserName) {
        this.id = id;
        this.ownershipChangeReason = ownershipChangeReason;
        this.comments = comments;
        this.updateDt = updateDt;
        this.createDt = createDt;
        this.equipmentId = equipmentId;
        this.uniqueNum = uniqueNum;
        this.name = name;
        this.specification = specification;
        this.type = type;
        this.status = status;
        this.accountId = accountId;
        this.userId = userId;
        this.userName = userName;
        this.position = position;
        this.dept = dept;
        this.confirmAccountId = confirmAccountId;
        this.confirmUserName = confirmUserName;
    }
}
