package kr.team.ticketing.domain.product;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.team.ticketing.domain.product.display.LocationCode;
import kr.team.ticketing.web.index.request.SearchCondition;
import kr.team.ticketing.web.index.response.ProductSearchResponse;
import kr.team.ticketing.web.index.response.QProductSearchResponse;
import org.springframework.data.domain.Page;
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
    public Page<ProductSearchResponse> searchByDynamicCondition(SearchCondition searchCondition, Pageable pageable) {
        List<ProductSearchResponse> content = queryFactory
                .select(new QProductSearchResponse(
                        product.id,
                        product.event,
                        product.content,
                        product.description,
                        product.categoryId,
                        display.startDisplayDate,
                        display.endDisplayDate,
                        display.address.locationCode
                ))
                .from(product, display)
                .where(product.id.eq(display.productId),
                        eqId(searchCondition.getCategoryId()),
                        eqLocation(searchCondition.getLocationCode()),
                        eqMonth(searchCondition.getMonth())
                )
                .orderBy(display.endDisplayDate.asc())
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
        return isRunning(month);
    }

    private BooleanExpression isRunning(int month) {
        NumberExpression<Integer> startMonth = display.startDisplayDate.month();
        NumberExpression<Integer> endMonth = display.endDisplayDate.month();
        return startMonth.loe(month).and(endMonth.goe(month));
    }
}
