package prompt.manageResources.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import prompt.manageResources.model.dto.AccountEquipmentDto;
import prompt.manageResources.model.entity.AccountEquipment;

import static org.mapstruct.ap.internal.gem.MappingConstantsGem.ComponentModelGem.SPRING;

@Mapper(componentModel = SPRING)
public interface AccountEquipmentMapper {

    AccountEquipmentMapper INSTANCE = Mappers.getMapper(AccountEquipmentMapper.class);

    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "account.userName", target = "userId")
    @Mapping(source = "account.name", target = "userName")
    @Mapping(source = "account.position", target = "position")
    @Mapping(source = "account.dept", target = "dept")
    @Mapping(source = "equipment.id", target = "equipmentId")
    @Mapping(source = "equipment.name", target = "equipmentName")
    @Mapping(source = "confirmAccount.id", target = "confirmAccountId", defaultExpression = "java(null)")
    @Mapping(source = "confirmAccount.name", target = "confirmUserName", defaultExpression = "java(null)")
    AccountEquipmentDto toDto(AccountEquipment accountEquipment);

    AccountEquipment toAccountEquipment(AccountEquipmentDto accountEquipmentDto);
}
