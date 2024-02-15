package prompt.manageResources.model.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import prompt.manageResources.model.enums.Dept;
import prompt.manageResources.model.enums.Position;
import prompt.manageResources.model.enums.equipment.Status;
import prompt.manageResources.model.enums.equipment.Type;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class EquipmentDto {
    private Long id;

    private String uniqueNum;

    private String name;

    private String specification;

    private Type type;

    private Status status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate purchasedDt;

    private Instant updateDt;

    private Instant createDt;

    // account information
    private Long accountId;

    private String userId;

    private String userName;

    private Position position;

    private Dept dept;

    @QueryProjection
    public EquipmentDto(Long id, String uniqueNum, String name, String specification, Type type, Status status, LocalDate purchasedDt, Instant updateDt, Instant createDt, Long accountId, String userId, String userName, Position position, Dept dept) {
        this.id = id;
        this.uniqueNum = uniqueNum;
        this.name = name;
        this.specification = specification;
        this.type = type;
        this.status = status;
        this.purchasedDt = purchasedDt;
        this.updateDt = updateDt;
        this.createDt = createDt;
        this.accountId = accountId;
        this.userId = userId;
        this.userName = userName;
        this.position = position;
        this.dept = dept;
    }
}
