package com.hspedu.mapper;

import com.hspedu.entity.Pet;

import java.util.List;

/**
 * @author yangda
 * @create 2023-11-03-14:33
 * @description:
 */
public interface PetMapper {

    //通过 User 的 id 来获取 pet 对象，可能有多个，因此使用 List 接收
    public List<Pet> getPetByUserId(Integer userId);

    //通过 pet 的 id 获取 Pet 对象, 同时会查询到pet对象关联的user对象
    public Pet getPetById(Integer id);

}
