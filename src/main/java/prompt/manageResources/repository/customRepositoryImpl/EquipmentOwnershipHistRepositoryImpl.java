package prompt.manageResources.repository.customRepositoryImpl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import prompt.manageResources.model.dto.EquipmentOwnershipHistDto;
import prompt.manageResources.model.dto.QEquipmentOwnershipHistDto;
import prompt.manageResources.model.entity.QAccount;
import prompt.manageResources.repository.customRepositroy.EquipmentOwnershipHistCustomRepository;

import static prompt.manageResources.model.entity.QAccount.account;
import static prompt.manageResources.model.entity.QEquipmentOwnershipHist.equipmentOwnershipHist;
import static prompt.manageResources.model.entity.QEquipment.equipment;
import static prompt.manageResources.model.entity.QEquipmentRequest.equipmentRequest;

import java.util.List;

@RequiredArgsConstructor
public class EquipmentOwnershipHistRepositoryImpl implements EquipmentOwnershipHistCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<EquipmentOwnershipHistDto> findAllByConditions(EquipmentOwnershipHistDto equipmentOwnershipHistDto) {
        QAccount account = new QAccount("account");
        QAccount confirmAccount = new QAccount("confirmAccount");
        List<EquipmentOwnershipHistDto> results = queryFactory
                .select(new QEquipmentOwnershipHistDto(
                        equipmentOwnershipHist.id,
                        equipmentOwnershipHist.ownershipChangeReason,
                        equipmentOwnershipHist.comments,
                        equipmentOwnershipHist.updateDt,
                        equipmentOwnershipHist.createDt,
                        equipment.id,
                        equipment.uniqueNum,
                        equipment.name,
                        equipment.specification,
                        equipment.type,
                        equipment.status,
                        account.id,
                        account.userName,
                        account.name,
                        account.position,
                        account.dept,
                        confirmAccount.id,
                        confirmAccount.name
                ))
                .from(equipmentOwnershipHist)
                .innerJoin(equipmentOwnershipHist.account, account)
                .innerJoin(equipmentOwnershipHist.equipment, equipment)
                .innerJoin(equipmentRequest)
                    .on(equipmentRequest.equipment.eq(equipmentOwnershipHist.equipment))
                    .on(equipmentRequest.account.eq(account))
                .innerJoin(equipmentRequest.confirmAccount, confirmAccount)
                .where(
                        equipmentIdEq(equipmentOwnershipHistDto.getEquipmentId()),
                        accountIdEq(equipmentOwnershipHistDto.getAccountId())
                )
                .orderBy(equipmentOwnershipHist.createDt.desc())
                .fetch();
        return results;
    }

    private BooleanExpression equipmentIdEq(Long id) {
        return !ObjectUtils.isEmpty(id) ? equipmentOwnershipHist.equipment.id.eq(id) : null;
    }

    private BooleanExpression accountIdEq(Long id) {
        return !ObjectUtils.isEmpty(id) ? equipmentOwnershipHist.account.id.eq(id) : null;
    }
}
