package com.stproj.demo.specification;

import com.stproj.demo.entity.Group;
import com.stproj.demo.entity.Student;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class GroupSpecification implements Specification<Group> {
    @Override
    public Predicate toPredicate(Root<Group> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Group, Student> fromTable = root.join("students", JoinType.INNER);
        query.groupBy(root.get("id"));
        Predicate predicate = criteriaBuilder.greaterThan(criteriaBuilder.count(fromTable), Long.valueOf(10));
        return  query.having(predicate).getRestriction();
    }
}
