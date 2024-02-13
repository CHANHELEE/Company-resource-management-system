package prompt.manageResources.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import prompt.manageResources.model.dto.EquipmentRequestDto;
import prompt.manageResources.model.entity.EquipmentRequest;

import static org.mapstruct.ap.internal.gem.MappingConstantsGem.ComponentModelGem.SPRING;

@Mapper(componentModel = SPRING)
public interface EquipmentRequestMapper {

    EquipmentRequestMapper INSTANCE = Mappers.getMapper(EquipmentRequestMapper.class);

    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "account.userName", target = "userId")
    @Mapping(source = "account.name", target = "userName")
    @Mapping(source = "account.position", target = "position")
    @Mapping(source = "account.dept", target = "dept")
    @Mapping(source = "equipment.id", target = "equipmentId")
    @Mapping(source = "equipment.name", target = "equipmentName")
    @Mapping(source = "confirmAccount.id", target = "confirmAccountId", defaultExpression = "java(null)")
    @Mapping(source = "confirmAccount.name", target = "confirmUserName", defaultExpression = "java(null)")
    EquipmentRequestDto toDto(EquipmentRequest equipmentRequest);

    EquipmentRequest toEquipmentRequest(EquipmentRequestDto equipmentRequestDto);
}
