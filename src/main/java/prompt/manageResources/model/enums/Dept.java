package prompt.manageResources.model.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Dept {
    DESING("디자인"),
    DEV("개발"),
    SALES("영업"),
    HR("인사");

    private final String dept;
}
