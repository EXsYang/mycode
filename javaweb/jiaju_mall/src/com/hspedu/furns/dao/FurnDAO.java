package com.hspedu.furns.dao;

import com.hspedu.furns.entity.Furn;

import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-07-17-11:13
 */
public interface FurnDAO {


    /**
     * 返回所有的家居信息集合: 后面我们再考虑分页
     * @return
     */
    public List<Furn> queryFurnList();

    /**
     *
     * @param furn
     * @return 返回受影响的行数 如果失败返回0
     */
    public int saveFurn(Furn furn);

    /**
     * 判断传入的Furn对象是否存在
     * @param furn
     * @return
     */
    public Furn isExistsFurn(Furn furn);

    public int deleteFurnById(Integer id);

    //public int deleteFurnById(int id);

    //public Furn deleteFurnById(Furn furn);

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
     * 根据传进来的Furn对象 到数据库查询库存并返回
     * @param furn
     * @return 库存
     */
    //public Integer queryStock(Furn furn);




    //page的哪些属性可以从数据库中直接获取的 就把这个填充任务放在DAO层

    /**
     * 获取数据总行数
     * @return
     */
    public int getTotalRow();

    /**
     * 从数据库中获取当前页，要显示的数据
     * @param begin 从第begin+1条数据开始取 limit begin,pageSize begin:从0开始     limit start,rows start:从0开始
     * @param pageSize 每页的取出多少记录
     * @return
     */
    public List<Furn> getPageItems(int begin,int pageSize);

    /**
     * 根据传入的家居名 查询总页数
     * @param name
     * @return
     */
    public int getTotalRowByName(String name);
    /**
     * 根据传入的家居名 包含此家居名的 家居信息
     * @param name
     * @param begin 从第begin+1条数据开始取
     * @param pageSize 每页的取出多少记录
     * @return 返回根据名字查询出来的结果 分页后的
     */
    public List<Furn> getPageItemsByName(String name,int begin, int pageSize);






}
