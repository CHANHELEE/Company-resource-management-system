package prompt.manageResources.repository.customRepositroy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import prompt.manageResources.model.dto.EquipmentDto;

public interface EquipmentCustomRepository {

    Page<EquipmentDto> findAllByConditions(EquipmentDto equipmentDto, Pageable pageable);

}
