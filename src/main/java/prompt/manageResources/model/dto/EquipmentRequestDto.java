package prompt.manageResources.model.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import prompt.manageResources.model.enums.Dept;
import prompt.manageResources.model.enums.Position;
import prompt.manageResources.model.enums.equipment.Type;
import prompt.manageResources.model.enums.equipmentRequest.RequestStatus;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class EquipmentRequestDto {

    private Long id;

    private String requestCn;

    private String rejectReason;

    private Type type;

    private RequestStatus requestStatus;

    private Instant createDt;

    private Instant updateDt;

    // account information
    private Long accountId;

    private String userId;

    private String userName;

    private Position position;

    private Dept dept;

    // confirm account informantion
    private Long confirmAccountId;

    private String confirmUserName;


    // equipment information
    private Long equipmentId;

    private String equipmentName;


    @QueryProjection
    public EquipmentRequestDto(final Long id, final String requestCn, final String rejectReason, final Type type, final RequestStatus requestStatus, final Instant createDt, final Instant updateDt, final Long accountId, final String userId, final String userName, final Position position, final Dept dept, final Long confirmAccountId, final String confirmUserName, final Long equipmentId, final String equipmentName) {
        this.id = id;
        this.requestCn = requestCn;
        this.rejectReason = rejectReason;
        this.type = type;
        this.requestStatus = requestStatus;
        this.createDt = createDt;
        this.updateDt = updateDt;
        this.accountId = accountId;
        this.userId = userId;
        this.userName = userName;
        this.position = position;
        this.dept = dept;
        this.confirmAccountId = confirmAccountId;
        this.confirmUserName = confirmUserName;
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
    }
}
