package prompt.manageResources.model.dto;


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

    public String userId;

    public String name;

    public String email;

    private Auth auth;

    private Dept dept;

    private Position position;

    private Instant updateDt;

    private Instant createDt;

    private String is_deleted;

}
