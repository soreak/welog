package com.soreak.dao;

import com.soreak.pojo.Tag;
import com.soreak.pojo.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagsRepository extends JpaRepository<Tag,Long> {
        Tag findByName(String name);

        @Query("select t from t_tag t")
        List<Tag> findTop(Pageable pageable);
}
