package prompt.manageResources.model.enums.equipmentRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestStatus {
    PENDING("대기"),
    APPROVED("승인"),
    REJECTED("거부");

    private final String requestStatus;
}
