package prompt.manageResources.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import prompt.manageResources.model.entity.Account;
import prompt.manageResources.repository.AccountRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserName(username);

        if (account == null) {
            throw new UsernameNotFoundException("USERNAME NOT FOUND");
        }
        return User.builder()
                .username(account.getUserName())
                .password(account.getPassword())
                .roles(account.getAuth().toString())
                .build();
    }

}
