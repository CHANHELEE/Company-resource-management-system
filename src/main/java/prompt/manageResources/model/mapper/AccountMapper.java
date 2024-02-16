package prompt.manageResources.model.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import prompt.manageResources.model.dto.AccountDto;
import prompt.manageResources.model.dto.PrivateAccountDto;
import prompt.manageResources.model.entity.Account;

import static org.mapstruct.ap.internal.gem.MappingConstantsGem.ComponentModelGem.SPRING;

@Mapper(componentModel = SPRING)
public interface AccountMapper {

    @Mapping(source = "userName", target = "userId")
    @Mapping(source = "isDeleted", target = "isDeleted")
    AccountDto toAccountDto(Account account);

    @Mapping(source = "userId", target = "userName")
    Account toAccount(AccountDto accountDto);

    @Mapping(source = "userName", target = "userId")
    PrivateAccountDto toPrivateAccountDto(Account account);

    @Mapping(source = "userId", target = "userName")
    Account toAccount(PrivateAccountDto accountDto);
}
