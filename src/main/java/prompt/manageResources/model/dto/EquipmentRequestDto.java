package prompt.manageResources.model.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import prompt.manageResources.model.enums.Position;
import prompt.manageResources.model.enums.equipment.Type;
import prompt.manageResources.model.enums.equipmentRequest.RequestStatus;

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

    // account information
    private Long accountId;

    private String userId;

    private String userName;

    private Position position;

    // confirm account informantion
    private Long confirmAccountId;

    private String confirmUserName;


    // equipment information
    private Long equipmentId;

    private String equipmentName;


    @QueryProjection
    public EquipmentRequestDto(Long id, String requestCn, String rejectReason, Type type, RequestStatus requestStatus, Long accountId, String userName, String name, Position position, Long confirmAccountId, String confirmUserName, Long equipmentId, String equipmentName) {
        this.id = id;
        this.requestCn = requestCn;
        this.rejectReason = rejectReason;
        this.type = type;
        this.requestStatus = requestStatus;
        this.accountId = accountId;
        this.userId = userName;
        this.userName = name;
        this.position = position;
        this.confirmAccountId = confirmAccountId;
        this.confirmUserName = confirmUserName;
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
    }
}
