package prompt.manageResources.repository.customRepositoryImpl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
        QueryResults<AccountDto> results = queryFactory
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
                .fetchResults();

        List<AccountDto> contents = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(contents,pageable,total);
    }

    // ========================================================================================
    // BooleanExpressions

    private BooleanExpression userNameLike(String userName) {
        return StringUtils.hasText(userName) ? account.userName.like("%".concat(userName).concat("%")) : null;
    }

    private BooleanExpression nameLike(String name) {
        return StringUtils.hasText(name) ? account.name.like("%".concat(name).concat("%")) : null;
    }

    private BooleanExpression isDeleted(Boolean flag) {
        return flag != null ? account.is_deleted.eq(flag) : null;
    }
}
