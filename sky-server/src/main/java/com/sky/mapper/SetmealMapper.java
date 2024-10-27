package com.sky.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SetmealMapper {

    /**
     * 根据id查询套餐数量
     * @param CategoryId
     * @return
     */
    @Select("select count(id) from setmeal where category_id = #{CategoryId}")
    Integer countBycategoryId(Long CategoryId);
}
