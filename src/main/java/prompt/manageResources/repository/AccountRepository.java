package prompt.manageResources.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prompt.manageResources.model.entity.Account;
import prompt.manageResources.repository.customRepositroy.AccountCustomRepository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, AccountCustomRepository {

    Account findByUserName(String username);

    List<Account> findAllByNameLike(String name);

}
