package prompt.manageResources.repository.customRepositroy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import prompt.manageResources.model.dto.EquipmentRequestDto;

public interface EquipmentRequestCustomRepository {

    Page<EquipmentRequestDto> findAllByConditions(EquipmentRequestDto equipmentRequestDto, Pageable pageable);

}
