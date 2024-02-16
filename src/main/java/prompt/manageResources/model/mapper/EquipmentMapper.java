package prompt.manageResources.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import prompt.manageResources.model.dto.EquipmentDto;
import prompt.manageResources.model.entity.Equipment;

import java.util.List;

import static org.mapstruct.ap.internal.gem.MappingConstantsGem.ComponentModelGem.SPRING;

@Mapper(componentModel = SPRING)
public interface EquipmentMapper {
    EquipmentMapper INSTANCE = Mappers.getMapper(EquipmentMapper.class);

    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "account.userName", target = "userId")
    @Mapping(source = "account.name", target = "userName")
    @Mapping(source = "account.position", target = "position")
    @Mapping(source = "account.dept", target = "dept")
    EquipmentDto toDto(Equipment equipment);

    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "account.userName", target = "userId")
    @Mapping(source = "account.name", target = "userName")
    @Mapping(source = "account.position", target = "position")
    @Mapping(source = "account.dept", target = "dept")
    List<EquipmentDto> toDto(List<Equipment> equipment);
}
