package prompt.manageResources.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import prompt.manageResources.model.dto.AccountDto;
import prompt.manageResources.model.dto.PrivateAccountDto;
import prompt.manageResources.model.entity.Account;
import prompt.manageResources.model.entity.MileageHist;
import prompt.manageResources.model.helper.AccountAdapter;
import prompt.manageResources.model.mapper.AccountMapper;
import prompt.manageResources.repository.AccountRepository;
import prompt.manageResources.repository.MileageHistRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final MileageHistRepository mileageHistRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountMapper accountMapper;

    public Account findByUserName(String userName) {
        return accountRepository.findByUserName(userName);
    }

    public AccountDto findById(Long id) {
        return accountRepository.findById(id).map(accountMapper::toAccountDto).orElse(null);
    }

    @Transactional
    public void initSave(PrivateAccountDto privateAccountDto) {

        privateAccountDto.setPassword(encodePassword(privateAccountDto.getPassword()));
        Account account = accountMapper.toAccount(privateAccountDto);

        MileageHist mileageHist = new MileageHist();
        mileageHist.setAccount(accountRepository.save(account));
        mileageHistRepository.save(mileageHist);
    }

    @Transactional
    public void update(PrivateAccountDto privateAccount) {
        Account account = accountRepository.findById(privateAccount.getId()).orElseThrow(() -> new IllegalArgumentException("Account not found"));
        account.setAuth(privateAccount.getAuth());
        account.setEmail(privateAccount.getEmail());
        account.setPosition(privateAccount.getPosition());
        account.setDept(privateAccount.getDept());
        if(!ObjectUtils.isEmpty(privateAccount.getPassword())) {
            account.setPassword(encodePassword(privateAccount.getPassword()));
        }
        accountRepository.save(account);
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

    private String encodePassword(String pw) {
        return passwordEncoder.encode(pw);
    }

    public Page<AccountDto> findAllByConditions(AccountDto accountDto, Pageable pageable) {
        return accountRepository.findAllByConditions(accountDto, pageable);
    }

}
