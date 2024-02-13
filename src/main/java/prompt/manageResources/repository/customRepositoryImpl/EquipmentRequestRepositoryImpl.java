package prompt.manageResources.repository.customRepositoryImpl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import prompt.manageResources.model.dto.EquipmentRequestDto;
import prompt.manageResources.model.dto.QEquipmentRequestDto;
import prompt.manageResources.model.entity.QAccount;
import prompt.manageResources.model.enums.equipment.Type;
import prompt.manageResources.repository.customRepositroy.EquipmentRequestCustomRepository;

import java.util.List;

import static prompt.manageResources.model.entity.QEquipmentRequest.equipmentRequest;
import static prompt.manageResources.model.entity.QAccount.account;
import static prompt.manageResources.model.entity.QEquipment.equipment;

@RequiredArgsConstructor
public class EquipmentRequestRepositoryImpl implements EquipmentRequestCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<EquipmentRequestDto> findAllByConditions(EquipmentRequestDto equipmentRequestDto, Pageable pageable) {
        QAccount account = new QAccount("account");
        QAccount confirmAccount = new QAccount("confirmAccount");
        List<EquipmentRequestDto> results = queryFactory
                .select(new QEquipmentRequestDto(
                                equipmentRequest.id,
                                equipmentRequest.requestCn,
                                equipmentRequest.rejectReason,
                                equipmentRequest.type,
                                equipmentRequest.requestStatus,
                                equipmentRequest.createDt,
                                equipmentRequest.updateDt,
                                account.id,
                                account.userName,
                                account.name,
                                account.position,
                                account.dept,
                                equipment.id,
                                equipment.name,
                                confirmAccount.id,
                                confirmAccount.name
                        )
                )
                .from(equipmentRequest)
                .innerJoin(equipmentRequest.account, account)
                .leftJoin(equipmentRequest.confirmAccount, confirmAccount)
                .leftJoin(equipmentRequest.equipment, equipment)
                .where(
                        userIdLike(equipmentRequestDto.getUserName()),
                        userNameLike(equipmentRequestDto.getUserName()),
                        typeEq(equipmentRequestDto.getType())
                )
                .orderBy(equipmentRequest.createDt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(equipmentRequest.count())
                .from(equipmentRequest)
                .innerJoin(equipmentRequest.account, account).fetchJoin()
                .where(
                        userIdLike(equipmentRequestDto.getUserId()),
                        userNameLike(equipmentRequestDto.getUserName()),
                        typeEq(equipmentRequestDto.getType())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());


        return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);
    }

    BooleanExpression userIdLike(String userId) {
        return StringUtils.hasText(userId) ? account.userName.like("%".concat(userId).concat("%")) : null;
    }

    BooleanExpression userNameLike(String UserName) {
        return StringUtils.hasText(UserName) ? account.name.like("%".concat(UserName).concat("%")) : null;
    }

    BooleanExpression typeEq(Type type) {
        return !ObjectUtils.isEmpty(type) ? equipmentRequest.type.eq(type) : null;
    }


}
