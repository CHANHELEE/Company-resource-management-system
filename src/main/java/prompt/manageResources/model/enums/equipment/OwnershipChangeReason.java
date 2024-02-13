package prompt.manageResources.model.enums.equipment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OwnershipChangeReason {
    APPROVE_REQUEST("요청승인"),
    CHANGE_BY_ADMIN("관리자수정");

    private final String changeReason;
}
