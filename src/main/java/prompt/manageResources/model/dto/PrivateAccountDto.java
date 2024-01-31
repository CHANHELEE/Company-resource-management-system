package prompt.manageResources.model.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import prompt.manageResources.model.enums.Auth;
import prompt.manageResources.model.enums.Dept;
import prompt.manageResources.model.enums.Position;

@Getter
@Setter
@Builder
public class PrivateAccountDto {
    private Long id;

    private String userId;

    private String name;

    private String password;

    private String email;

    private Auth auth;

    private Dept dept;

    private Position position;

}
