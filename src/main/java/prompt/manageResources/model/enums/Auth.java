package prompt.manageResources.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Auth {
    ADMIN("관리자"),
    USER("사용자");

    private final String auth;

}
