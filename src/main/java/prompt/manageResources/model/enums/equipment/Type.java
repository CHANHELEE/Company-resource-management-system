package prompt.manageResources.model.enums.equipment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Type {
    DESKTOP("데스크탑"),
    NOTEBOOK("노트북");

    private final String status;
}
