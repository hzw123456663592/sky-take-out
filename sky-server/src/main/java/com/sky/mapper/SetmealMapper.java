package com.sky.mapper;


import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.SetmealVO;
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


    /**
     * 根据id修改套餐
     */
    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);


    /**
     * 新增套餐
     */
    @AutoFill(OperationType.INSERT)
    void insert(Setmeal setmeal);

    /**
     * 分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);
}
