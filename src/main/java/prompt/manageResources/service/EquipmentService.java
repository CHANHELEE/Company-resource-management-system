package prompt.manageResources.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import prompt.manageResources.model.dto.EquipmentDto;
import prompt.manageResources.model.entity.Equipment;
import prompt.manageResources.model.enums.equipment.Status;
import prompt.manageResources.repository.EquipmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public Page<EquipmentDto> findAllByConditions(EquipmentDto equipmentDto, Pageable pageable) {
        return equipmentRepository.findAllByConditions(equipmentDto, pageable);
    }

    public Equipment findById(Long id) {
        return equipmentRepository.findById(id).orElse(null);
    }

    public Equipment save(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public Equipment update(Long id, EquipmentDto equipmentDto) {
        Equipment equipment = this.findById(id);
        equipment.setUniqueNum(equipmentDto.getUniqueNum());
        equipment.setPurchasedDt(equipmentDto.getPurchasedDt());
        equipment.setName(equipmentDto.getName());
        equipment.setSpecification(equipmentDto.getSpecification());
        equipment.setType(equipmentDto.getType());

        return equipmentRepository.save(equipment);
    }

    public Equipment delete(Long id) {
        Equipment equipment = this.findById(id);
        equipment.setAccount(null);
        equipment.setStatus(Status.DISCARDED);
        return equipmentRepository.save(equipment);
    }
}
