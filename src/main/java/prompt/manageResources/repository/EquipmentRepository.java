package prompt.manageResources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prompt.manageResources.model.entity.Equipment;
import prompt.manageResources.repository.customRepositroy.EquipmentCustomRepository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long>, EquipmentCustomRepository {

}
