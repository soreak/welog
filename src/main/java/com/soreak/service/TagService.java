package com.soreak.service;

import com.soreak.pojo.Tag;
import com.soreak.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    Tag saveTag(Tag tag);
    Tag getTag(Long id);
    Page<Tag> ListTag(Pageable pageable);

    Tag updateTag(Long id, Tag tag);

    void deleteTag(Long id);
    Tag getTagByName(String name);

    List<Tag> listTag();
    List<Tag> listTag(String ids);
}
