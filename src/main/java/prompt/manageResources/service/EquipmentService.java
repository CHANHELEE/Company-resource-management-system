package prompt.manageResources.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import prompt.manageResources.model.dto.EquipmentDto;
import prompt.manageResources.model.entity.Account;
import prompt.manageResources.model.entity.Equipment;
import prompt.manageResources.model.enums.equipment.Status;
import prompt.manageResources.model.mapper.EquipmentMapper;
import prompt.manageResources.repository.AccountRepository;
import prompt.manageResources.repository.EquipmentRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final AccountRepository accountRepository;

    public Page<EquipmentDto> findAllByConditions(EquipmentDto equipmentDto, Pageable pageable) {
        return equipmentRepository.findAllByConditions(equipmentDto, pageable);
    }

    public EquipmentDto findById(Long id) {
        return EquipmentMapper.INSTANCE.toDto(equipmentRepository.findById(id).orElse(null));
    }

    public List<EquipmentDto> findByAccountId(Long id) {
        return Optional.ofNullable(equipmentRepository.findByAccountId(id))
                .map(EquipmentMapper.INSTANCE::toDto)
                .orElse(Collections.emptyList());
    }

    public Equipment save(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public Equipment update(Long id, EquipmentDto equipmentDto) {
        Equipment equipment = equipmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Equipment is not found"));

        equipment.setUniqueNum(equipmentDto.getUniqueNum());
        equipment.setPurchasedDt(equipmentDto.getPurchasedDt());
        equipment.setName(equipmentDto.getName());
        equipment.setSpecification(equipmentDto.getSpecification());
        equipment.setType(equipmentDto.getType());

        return equipmentRepository.save(equipment);
    }

    public Equipment updateAccountNull(Long accountId, Long equipmentId) {
        Equipment equipment = equipmentRepository.findByIdAndAccountId(equipmentId, accountId).orElseThrow(() -> new IllegalArgumentException("Equipment is not found"));

        equipment.setStatus(Status.UNUSING);
        equipment.setAccount(null);
        return equipmentRepository.save(equipment);
    }

    public Equipment delete(Long id) {
        Equipment equipment = equipmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Equipment is not found"));

        equipment.setAccount(null);
        equipment.setStatus(Status.DISCARDED);
        return equipmentRepository.save(equipment);
    }
}
