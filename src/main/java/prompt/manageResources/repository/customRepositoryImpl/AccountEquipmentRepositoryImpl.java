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
import prompt.manageResources.model.dto.AccountEquipmentDto;
import prompt.manageResources.model.dto.QAccountEquipmentDto;
import prompt.manageResources.model.entity.QAccount;
import prompt.manageResources.model.enums.equipment.Type;
import prompt.manageResources.repository.customRepositroy.AccountEquipmentCustomRepository;

import java.util.List;

import static prompt.manageResources.model.entity.QAccountEquipment.accountEquipment;
import static prompt.manageResources.model.entity.QAccount.account;
import static prompt.manageResources.model.entity.QEquipment.equipment;

@RequiredArgsConstructor
public class AccountEquipmentRepositoryImpl implements AccountEquipmentCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<AccountEquipmentDto> findAllByConditions(AccountEquipmentDto accountEquipmentDto, Pageable pageable) {
        QAccount account = new QAccount("account");
        QAccount confirmAccount = new QAccount("confirmAccount");
        List<AccountEquipmentDto> results = queryFactory
                .select(new QAccountEquipmentDto(
                                accountEquipment.id,
                                accountEquipment.requestCn,
                                accountEquipment.rejectReason,
                                accountEquipment.type,
                                accountEquipment.requestStatus,
                                accountEquipment.createDt,
                                accountEquipment.updateDt,
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
                .from(accountEquipment)
                .innerJoin(accountEquipment.account, account)
                .leftJoin(accountEquipment.confirmAccount, confirmAccount)
                .leftJoin(accountEquipment.equipment, equipment)
                .where(
                        userIdLike(accountEquipmentDto.getUserName()),
                        userNameLike(accountEquipmentDto.getUserName()),
                        typeEq(accountEquipmentDto.getType())
                )
                .orderBy(accountEquipment.createDt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(accountEquipment.count())
                .from(accountEquipment)
                .innerJoin(accountEquipment.account, account).fetchJoin()
                .where(
                        userIdLike(accountEquipmentDto.getUserId()),
                        userNameLike(accountEquipmentDto.getUserName()),
                        typeEq(accountEquipmentDto.getType())
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
        return !ObjectUtils.isEmpty(type) ? accountEquipment.type.eq(type) : null;
    }


}
