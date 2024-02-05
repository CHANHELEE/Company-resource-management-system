package prompt.manageResources.model.enums.equipment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    USING("사용중"),
    DISCARDED("폐기"),
    UNUSING("미사용중");

    private final String status;
}
