package prompt.manageResources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prompt.manageResources.model.entity.AccountEquipment;
import prompt.manageResources.repository.customRepositroy.AccountEquipmentCustomRepository;

@Repository
public interface AccountEquipmentRepository extends JpaRepository<AccountEquipment, Long>, AccountEquipmentCustomRepository {

}
