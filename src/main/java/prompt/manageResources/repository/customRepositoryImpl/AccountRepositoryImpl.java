package prompt.manageResources.repository.customRepositoryImpl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;
import prompt.manageResources.model.dto.AccountDto;
import prompt.manageResources.model.dto.QAccountDto;
import prompt.manageResources.repository.customRepositroy.AccountCustomRepository;

import java.util.List;

import static prompt.manageResources.model.entity.QAccount.account;

@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<AccountDto> findAllByConditions(AccountDto accountDto, Pageable pageable) {
        List<AccountDto> results = queryFactory
                .select(new QAccountDto(
                        account.id,
                        account.userName,
                        account.name,
                        account.email,
                        account.auth,
                        account.dept,
                        account.position,
                        account.updateDt,
                        account.createDt,
                        account.is_deleted
                ))
                .from(account)
                .where(
                        userNameLike(accountDto.getUserId()),
                        nameLike(accountDto.getName()),
                        isDeleted(false)
                )
                .orderBy(account.createDt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(account.count())
                .from(account)
                .where(
                        userNameLike(accountDto.getUserId()),
                        nameLike(accountDto.getName()),
                        isDeleted(false)
                );

        return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);
    }

    // ========================================================================================
    // BooleanExpressions start

    private BooleanExpression userNameLike(String userName) {
        return StringUtils.hasText(userName) ? account.userName.like("%".concat(userName).concat("%")) : null;
    }

    private BooleanExpression nameLike(String name) {
        return StringUtils.hasText(name) ? account.name.like("%".concat(name).concat("%")) : null;
    }

    private BooleanExpression isDeleted(Boolean flag) {
        return flag != null ? account.is_deleted.eq(flag) : null;
    }

    // ========================================================================================
    // BooleanExpressions end
}
