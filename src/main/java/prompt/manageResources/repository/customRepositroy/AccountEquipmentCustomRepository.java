package prompt.manageResources.repository.customRepositroy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import prompt.manageResources.model.dto.AccountEquipmentDto;

public interface AccountEquipmentCustomRepository {

    Page<AccountEquipmentDto> findAllByConditions(AccountEquipmentDto accountEquipmentDto, Pageable pageable);

}
