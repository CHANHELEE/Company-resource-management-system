package prompt.manageResources.model.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import prompt.manageResources.model.enums.Auth;
import prompt.manageResources.model.enums.Dept;
import prompt.manageResources.model.enums.Position;

import java.time.Instant;

@Getter
@Setter
@Builder
public class AccountDto {
    private Long id;

    private String userId;

    private String name;

    private String email;

    private Auth auth;

    private Dept dept;

    private Position position;

    private Instant updateDt;

    private Instant createDt;

    private Boolean is_deleted;

//    private EquipmentRequestDto equipmentRequestDto;

    @QueryProjection
    public AccountDto(Long id, String userId, String name, String email, Auth auth, Dept dept, Position position, Instant updateDt, Instant createDt, Boolean is_deleted) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.auth = auth;
        this.dept = dept;
        this.position = position;
        this.updateDt = updateDt;
        this.createDt = createDt;
        this.is_deleted = is_deleted;
    }
}
