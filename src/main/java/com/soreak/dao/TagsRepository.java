package com.soreak.dao;

import com.soreak.pojo.Tag;
import com.soreak.pojo.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsRepository extends JpaRepository<Tag,Long> {
        Tag findByName(String name);

}
