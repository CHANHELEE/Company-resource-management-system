package prompt.manageResources.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import prompt.manageResources.model.dto.AccountDto;
import prompt.manageResources.model.dto.PrivateAccountDto;
import prompt.manageResources.model.entity.Account;
import prompt.manageResources.model.helper.AccountAdapter;
import prompt.manageResources.model.mapper.AccountMapper;
import prompt.manageResources.repository.AccountRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountMapper accountMapper;

    public Account findByUserName(String userName) {
        return accountRepository.findByUserName(userName);
    }

    public AccountDto findById(Long id) {
        return accountRepository.findById(id).map(accountMapper::toAccountDto).orElse(null);
    }

    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserName(username);

        if (account == null) {
            throw new UsernameNotFoundException("USERNAME NOT FOUND");
        }

        return new AccountAdapter(account);
    }

    public void save(PrivateAccountDto privateAccount) {
        privateAccount.setPassword(encodePassword(privateAccount.getPassword()));
        Account account = accountMapper.toAccount(privateAccount);
        accountRepository.save(account);
    }

    public String encodePassword(String pw) {
        return passwordEncoder.encode(pw);
    }

    public Page<AccountDto> findAllByConditions(AccountDto accountDto, Pageable pageable) {
        return accountRepository.findAllByConditions(accountDto, pageable);
    }

}
