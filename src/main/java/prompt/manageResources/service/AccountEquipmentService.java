package prompt.manageResources.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import prompt.manageResources.model.dto.AccountEquipmentDto;
import prompt.manageResources.model.entity.Account;
import prompt.manageResources.model.entity.AccountEquipment;
import prompt.manageResources.model.entity.Equipment;
import prompt.manageResources.model.entity.EquipmentOwnershipHist;
import prompt.manageResources.model.enums.equipment.OwnershipChangeReason;
import prompt.manageResources.model.enums.equipment.Status;
import prompt.manageResources.model.enums.accountEquipment.RequestStatus;
import prompt.manageResources.repository.AccountRepository;
import prompt.manageResources.repository.EquipmentOwnershipHistRepository;
import prompt.manageResources.repository.EquipmentRepository;
import prompt.manageResources.repository.AccountEquipmentRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountEquipmentService {
    private final AccountEquipmentRepository accountEquipmentRepository;
    private final EquipmentOwnershipHistRepository equipmentOwnershipHistRepository;
    private final EquipmentRepository equipmentRepository;
    private final AccountRepository accountRepository;

    public Page<AccountEquipmentDto> findAllByConditions(AccountEquipmentDto accountEquipmentDto, Pageable pageable) {
        return accountEquipmentRepository.findAllByConditions(accountEquipmentDto, pageable);
    }

    public AccountEquipment findById(Long id) {
        return accountEquipmentRepository.findById(id).orElse(null);
    }

    public AccountEquipment confirmEquipmentRequest(AccountEquipmentDto accountEquipmentDto, Account confirmedAccount) {
        AccountEquipment accountEquipment = this.findById(accountEquipmentDto.getId());
        accountEquipment.setConfirmAccount(confirmedAccount);
        accountEquipment.setRequestStatus(accountEquipmentDto.getRequestStatus());

        switch (accountEquipmentDto.getRequestStatus()) {
            case REJECTED:
                accountEquipment.setRejectReason(accountEquipmentDto.getRejectReason());
                break;
            case APPROVED:
                Equipment equipment = equipmentRepository.findById(accountEquipmentDto.getEquipmentId()).orElse(null);
                Account account = accountRepository.findById(accountEquipmentDto.getAccountId()).orElse(null);
                EquipmentOwnershipHist equipmentOwnershipHist = new EquipmentOwnershipHist();

                equipment.setAccount(account);
                equipment.setStatus(Status.USING);
                equipment = equipmentRepository.save(equipment);

                equipmentOwnershipHist.setAccount(account);
                equipmentOwnershipHist.setEquipment(equipment);
                equipmentOwnershipHist.setOwnershipChangeReason(OwnershipChangeReason.APPROVE_REQUEST);
                equipmentOwnershipHistRepository.save(equipmentOwnershipHist);

                accountEquipment.setEquipment(equipment);
                break;
            default:
                log.error("================================================");
                log.error("Equipment Request Status is not set properly.");
                log.error("================================================");
                throw new EnumConstantNotPresentException(RequestStatus.class , accountEquipmentDto.getRequestStatus().toString());
        }
        return accountEquipmentRepository.save(accountEquipment);
    }
}
