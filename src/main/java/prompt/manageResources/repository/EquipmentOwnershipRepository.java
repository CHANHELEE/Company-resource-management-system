package prompt.manageResources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prompt.manageResources.model.entity.Equipment;

@Repository
public interface EquipmentOwnershipRepository extends JpaRepository<Equipment, Long> {
}
