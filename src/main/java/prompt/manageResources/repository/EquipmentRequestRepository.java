package prompt.manageResources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prompt.manageResources.model.entity.EquipmentRequest;
import prompt.manageResources.repository.customRepositroy.EquipmentRequestCustomRepository;

@Repository
public interface EquipmentRequestRepository extends JpaRepository<EquipmentRequest, Long>, EquipmentRequestCustomRepository {

}
