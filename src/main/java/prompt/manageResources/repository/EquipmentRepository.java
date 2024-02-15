package prompt.manageResources.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prompt.manageResources.model.entity.Equipment;
import prompt.manageResources.repository.customRepositroy.EquipmentCustomRepository;

import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long>, EquipmentCustomRepository {

    @Override
    @EntityGraph(attributePaths = {"account"})
    Optional<Equipment> findById(Long id);

}
