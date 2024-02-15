package prompt.manageResources.repository.customRepositoryImpl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import prompt.manageResources.model.dto.EquipmentDto;
import prompt.manageResources.model.dto.QEquipmentDto;
import prompt.manageResources.repository.customRepositroy.EquipmentCustomRepository;

import java.util.List;

import static prompt.manageResources.model.entity.QEquipment.equipment;
import static prompt.manageResources.model.entity.QAccount.account;

@RequiredArgsConstructor
public class EquipmentRepositoryImpl implements EquipmentCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<EquipmentDto> findAllByConditions(EquipmentDto equipmentDto, Pageable pageable) {

        List<EquipmentDto> results = queryFactory
                .select(new QEquipmentDto(
                        equipment.id,
                        equipment.uniqueNum,
                        equipment.name,
                        equipment.specification,
                        equipment.type,
                        equipment.status,
                        equipment.purchasedDt,
                        equipment.updateDt,
                        equipment.createDt,
                        account.id,
                        account.userName,
                        account.name,
                        account.position,
                        account.dept
                ))
                .from(equipment)
                .leftJoin(equipment.account, account)
//                .where(
//
//                )
                .orderBy(equipment.createDt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(equipment.count())
                .from(equipment)
                .leftJoin(equipment.account, account)
//                .where(
//
//                )
                .orderBy(equipment.createDt.desc());


        return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);
    }

}
