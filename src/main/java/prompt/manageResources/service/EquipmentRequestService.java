package prompt.manageResources.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import prompt.manageResources.model.dto.EquipmentRequestDto;
import prompt.manageResources.model.entity.Account;
import prompt.manageResources.model.entity.Equipment;
import prompt.manageResources.model.entity.EquipmentOwnershipHist;
import prompt.manageResources.model.entity.EquipmentRequest;
import prompt.manageResources.model.enums.equipment.OwnershipChangeReason;
import prompt.manageResources.model.enums.equipment.Status;
import prompt.manageResources.model.enums.equipmentRequest.RequestStatus;
import prompt.manageResources.repository.AccountRepository;
import prompt.manageResources.repository.EquipmentOwnershipHistRepository;
import prompt.manageResources.repository.EquipmentRepository;
import prompt.manageResources.repository.EquipmentRequestRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class EquipmentRequestService {
    private final EquipmentRequestRepository equipmentRequestRepository;
    private final EquipmentOwnershipHistRepository equipmentOwnershipHistRepository;
    private final EquipmentRepository equipmentRepository;
    private final AccountRepository accountRepository;

    public Page<EquipmentRequestDto> findAllByConditions(EquipmentRequestDto equipmentRequestDto, Pageable pageable) {
        return equipmentRequestRepository.findAllByConditions(equipmentRequestDto, pageable);
    }

    public EquipmentRequest findById(Long id) {
        return equipmentRequestRepository.findById(id).orElse(null);
    }

    public EquipmentRequest confirmEquipmentRequest(EquipmentRequestDto equipmentRequestDto, Account confirmedAccount) {
        EquipmentRequest equipmentRequest = this.findById(equipmentRequestDto.getId());
        equipmentRequest.setConfirmAccount(confirmedAccount);
        equipmentRequest.setRequestStatus(equipmentRequestDto.getRequestStatus());

        switch (equipmentRequestDto.getRequestStatus()) {
            case REJECTED:
                equipmentRequest.setRejectReason(equipmentRequestDto.getRejectReason());
                break;
            case APPROVED:
                Equipment equipment = equipmentRepository.findById(equipmentRequestDto.getEquipmentId()).orElse(null);
                Account account = accountRepository.findById(equipmentRequestDto.getAccountId()).orElse(null);
                EquipmentOwnershipHist equipmentOwnershipHist = new EquipmentOwnershipHist();

                equipment.setAccount(account);
                equipment.setStatus(Status.USING);
                equipment = equipmentRepository.save(equipment);

                equipmentOwnershipHist.setAccount(account);
                equipmentOwnershipHist.setEquipment(equipment);
                equipmentOwnershipHist.setOwnershipChangeReason(OwnershipChangeReason.APPROVE_REQUEST);
                equipmentOwnershipHistRepository.save(equipmentOwnershipHist);

                equipmentRequest.setEquipment(equipment);
                break;
            default:
                log.error("================================================");
                log.error("Equipment Request Status is not set properly.");
                log.error("================================================");
                throw new EnumConstantNotPresentException(RequestStatus.class , equipmentRequestDto.getRequestStatus().toString());
        }
        return equipmentRequestRepository.save(equipmentRequest);
    }
}
