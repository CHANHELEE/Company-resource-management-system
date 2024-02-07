package prompt.manageResources.model.enums.equipment;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Type {
    DESKTOP("데스크탑"),
    NOTEBOOK("노트북"),
    MONITOR("모니터");

    private final String type;

    public String getType() {
        return this.type;
    }
}
