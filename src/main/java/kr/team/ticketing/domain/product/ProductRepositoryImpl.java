package kr.team.ticketing.domain.product;


import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.team.ticketing.domain.product.display.Display;
import kr.team.ticketing.domain.product.display.LocationCode;
import kr.team.ticketing.web.admin.product.request.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;

import java.util.List;

import static kr.team.ticketing.domain.product.QProduct.product;
import static kr.team.ticketing.domain.product.display.QDisplay.display;


public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ProductRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Product> searchByDynamicCondition(SearchCondition searchCondition, Pageable pageable) {
        List<Product> content = queryFactory
                .select(product)
                .from(product, display)
                .where(product.id.eq(display.productId),
                        eqId(searchCondition.getCategoryId()),
                        eqLocation(searchCondition.getLocationCode()),
                        eqMonth(searchCondition.getMonth())
                )
                .orderBy(display.endDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Product> countQuery = queryFactory
                .selectFrom(product)
                .where(eqId(searchCondition.getCategoryId()),
                        eqLocation(searchCondition.getLocationCode()),
                        eqMonth(searchCondition.getMonth()));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }


    @Override
    public Page<Product> searchAll(Pageable pageable) {
        QueryResults<Product> results = queryFactory
                .selectFrom(product)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(product.id.desc())
                .fetchResults();

        List<Product> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression eqId(Long categoryId) {
        if (StringUtils.isEmpty(categoryId)) {
            return null;
        }
        return product.categoryId.eq(categoryId);
    }

    private BooleanExpression eqLocation(String location) {
        if (StringUtils.isEmpty(location)) {
            return null;
        }
        return display.address.locationCode.eq(LocationCode.valueOf(location));
    }

    private BooleanExpression eqMonth(int month) {
        if (month == 0) {
            return null;
        }
        return checkRun(month);
    }

    private BooleanExpression checkRun(int month) {
        NumberExpression<Integer> startMonth = display.startDate.month();
        NumberExpression<Integer> endMonth = display.endDate.month();
        return startMonth.loe(month).and(endMonth.goe(month));
    }
}
