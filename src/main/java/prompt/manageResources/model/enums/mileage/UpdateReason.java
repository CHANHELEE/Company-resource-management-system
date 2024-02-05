package prompt.manageResources.model.enums.mileage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UpdateReason {
    NEW("계정생성"),
    ADMINUPDATE("관리자 수정"),
    REQUEST_ACCEPTED("요청승인");

    private final String reason;

}
