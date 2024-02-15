package prompt.manageResources.repository.customRepositroy;

import prompt.manageResources.model.dto.EquipmentOwnershipHistDto;

import java.util.List;

public interface EquipmentOwnershipHistCustomRepository {

    List<EquipmentOwnershipHistDto> findAllByConditions(EquipmentOwnershipHistDto equipmentOwnershipHistDto);

}
