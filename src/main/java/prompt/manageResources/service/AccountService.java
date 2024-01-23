package prompt.manageResources.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import prompt.manageResources.model.entity.Account;
import prompt.manageResources.model.helper.AccountAdapter;
import prompt.manageResources.repository.AccountRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserName(username);

        if (account == null) {
            throw new UsernameNotFoundException("USERNAME NOT FOUND");
        }

        return new AccountAdapter(account);
    }

    public void save(Account account) {
        account.setPassword(encodePassword(account.getPassword()));
        accountRepository.save(account);
    }

    public String encodePassword(String pw) {
        return passwordEncoder.encode(pw);
    }

}
