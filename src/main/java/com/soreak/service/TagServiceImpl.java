package com.soreak.service;

import com.soreak.NotFoundException;
import com.soreak.dao.TagsRepository;
import com.soreak.pojo.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagsRepository tagsRepository;


    @Transactional
    @Override
    public Tag saveTag(Tag tag) {

        return tagsRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagsRepository.getOne(id);
    }

    @Transactional
    @Override
    public Page<Tag> ListTag(Pageable pageable) {
        return tagsRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagsRepository.getOne(id);
        if (t ==null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(tag,t);
        return tagsRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagsRepository.deleteById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagsRepository.findByName(name);
    }

    @Override
    public List<Tag> listTag() {

        return tagsRepository.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) {

        return tagsRepository.findAllById(convertToList(ids));
    }

    private List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();
        if ("".equals(ids) && ids !=null){
            String[] idarray = ids.split(",");
            for (int i=0;i<idarray.length;i++){
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }
}
