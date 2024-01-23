package prompt.manageResources.model.helper;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import prompt.manageResources.model.entity.Account;

import java.util.Arrays;
import java.util.Collection;

@Getter
public class AccountAdapter extends User {
    private Account account;

    public AccountAdapter(Account account) {
        super(account.getUserName(), account.getPassword(), authorities(account.getAuth().toString()));
        this.account = account;
    }

    private static Collection<? extends GrantedAuthority> authorities(String roles) {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_".concat(roles)));
    }
}
