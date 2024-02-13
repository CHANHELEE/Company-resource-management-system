package prompt.manageResources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prompt.manageResources.model.entity.Equipment;
import prompt.manageResources.model.entity.EquipmentOwnershipHist;

@Repository
public interface EquipmentOwnershipHistRepository extends JpaRepository<EquipmentOwnershipHist, Long> {
}
