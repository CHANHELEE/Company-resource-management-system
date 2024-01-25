package prompt.manageResources.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Position {
    HEADER("대표"),
    SENIOR_MANAGER("수석"),
    MANAGER("책임"),
    SENIOR_ASSISTANT("선임"),
    ASSISTANT("전임");

    private final String position;

    public String getPosition() {
        return this.position;
    }

}
