package prompt.manageResources.repository.customRepositroy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import prompt.manageResources.model.dto.AccountDto;

public interface AccountCustomRepository {

    Page<AccountDto> findAllByConditions(AccountDto accountDto, Pageable pageable);
}
