package com.stproj.demo.repository;

import com.stproj.demo.entity.Group;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface GroupRepository extends PagingAndSortingRepository<Group, UUID>, JpaSpecificationExecutor<Group> {
    @Query(value = "select groups.id, groups.number from groups inner join student on groups.id=student.group_id group by groups.id having count(*) > 10", nativeQuery = true)
    List<Group> getGroupsWithMoreThanTenStudentsNative();

    @Query("select g from Group g inner join Student s on g.id = s.stGroup.id group by g.id having count(*) > 10")
    List<Group> getGroupsWithMoreThanTenStudentsJPQL();
}