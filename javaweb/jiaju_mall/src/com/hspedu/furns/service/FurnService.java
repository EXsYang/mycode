package com.hspedu.furns.service;

import com.hspedu.furns.entity.Furn;
import com.hspedu.furns.entity.Page;

import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-07-17-11:27
 */
public interface FurnService {

    // 提供获取所有家居信息的方法
    public List<Furn> furnList();

    /**
     * 根据传入的furn对象 添加家居
     *
     * @param furn
     * @return
     */
    public boolean addFurn(Furn furn);

    public boolean isExistsFurn(Furn furn);

    public int deleteFurnById(Integer id);

    /**
     * 根据id查询家居信息
     * @param id
     * @return
     */
    public Furn queryFurnById(int id);

    /**
     * 根据传进来的Furn对象 更新数据库相应的数据
     * @param furn
     * @return 受影响的行数
     */
    public int updateFurn(Furn furn);

    /**
     * 根据传入的begin和pageSize 返回对应的page对象
     * @param pageNo 显示第几页。要大于0 否则sql语句报错 ：FROM `furn` LIMIT ? , ? Parameters: [-2, 2]
     * @param pageSize
     * @return
     */
    public Page<Furn> page(int pageNo, int pageSize);

    /**
     * 查询数据库中记录数
     * @return
     */
    public int getTotalRow();

    /**
     * 根据传入的家居名 返回对应的page对象
     * @param name
     * @param pageNo 显示第几页。要大于0 否则sql语句报错
     * @param pageSize 每页有几条数据
     * @return
     */
    public Page<Furn> pageByName(String name,int pageNo, int pageSize);



}