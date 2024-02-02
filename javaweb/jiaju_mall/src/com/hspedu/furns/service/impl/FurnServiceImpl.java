package com.hspedu.furns.service.impl;

import com.hspedu.furns.dao.FurnDAO;
import com.hspedu.furns.dao.daoimpl.FurnDAOImpl;
import com.hspedu.furns.entity.Furn;
import com.hspedu.furns.entity.Page;
import com.hspedu.furns.service.FurnService;

import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-07-17-11:29
 */
public class FurnServiceImpl implements FurnService {

    // 提供FurnDAO 属性
    private FurnDAO furnDAO = new FurnDAOImpl();

    @Override
    public List<Furn> furnList() {

        return furnDAO.queryFurnList();

    }

    /**
     * 添加家居
     * @param furn
     * @return
     */
    @Override
    public boolean addFurn(Furn furn) {

        return furnDAO.saveFurn(furn) > 0;
    }

    /**
     * 判断数据库中是否存在传入的furn对象
     * @param furn
     * @return  返回true 不存在
     */
    @Override
    public boolean isExistsFurn(Furn furn) {

       if(furnDAO.isExistsFurn(furn) != null){
           //System.out.println("furn is exists");
           return false;
       }else{
           //System.out.println("数据库中不存在 相同的furn信息");
           return true;
       }


    }

    /**
     * 根据Id删除 对应的家居信息 Furn数据
     * @param id
     * @return
     */
    @Override
    public int deleteFurnById(Integer id) {
        return furnDAO.deleteFurnById(id);
    }


    /**
     * 根据id查询家居信息
     * @param id
     * @return
     */
    @Override
    public Furn queryFurnById(int id) {
        return furnDAO.queryFurnById(id);
    }

    /**
     * 根据传进来的Furn对象 更新数据库相应的数据
     * @param furn
     * @return 受影响的行数
     */
    @Override
    public int updateFurn(Furn furn) {
        return furnDAO.updateFurn(furn);
    }

    /**
     * 根据传入的pageNo和 pageSize , 返回对应的page对象
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<Furn> page(int pageNo, int pageSize) {
/*        //因为每页显示多少条记录，是其它地方也可以使用
        //ctrl+shift+u => 切换大小写
        public static  final  Integer PAGE_SIZE = 3;

        //表示显示当前页[显示第几页]
        //前端页面来的
        private Integer pageNo;
        //表示每页显示几条记录
        private Integer pageSize = PAGE_SIZE;
        //表示共有多少页, 他是计算得到, 通过totalRow和pageSize
        private Integer pageTotalCount;
        //表示的是共有多少条记录
        //计算得到pageTotalCount
        //是可以同数据库DB来的->DAO
        private Integer totalRow;
        //表示当前页，要显示的数据
        //从DB来的->DAO
        private List<T> items;

        //分页导航的字符串
        private String url;*/

        //构建Page对象
        Page page = new Page();

        //表示显示当前页[显示第几页]
        page.setPageNo(pageNo);
        //表示每页显示几条记录
        page.setPageSize(pageSize);

        //表示的是共有多少条记录
        int totalRow = furnDAO.getTotalRow();
        page.setTotalRow(totalRow);

        //表示共有多少页, 他是计算得到 通过计算得到
        //总记录数pageTotalRow 每页数据条数pageSize 取模判断是否大于0
        //pageTotalCount
        //if(totalRow % pageSize > 0){
        //    page.setPageTotalCount(totalRow/pageSize + 1);
        //}else {
        //    page.setPageTotalCount(totalRow/pageSize);
        //}
//老师分析
        //比如 6 2  =》  6 / 2 = 3
        //比如 5 2  =》  5 / 2 = 2
        //验证 7 3 =>
        //验证 0 2 =>
         /*
         totalRow = 0 数据总行数为0 或者 totalRow < pageTotalCount时
         pageTotalCount = 0 此时 totalRow % pageSize > 0
         pageTotalCount++ 后 值为1
         totalRow = 0 时 模上 任何数都为0
         */
        int pageTotalCount = totalRow / pageSize; // 除法运算 只保留整数部分舍弃小数部分 截断运算
        //if (totalRow % pageSize > 0){
        //    pageTotalCount++;
        //}
        //if (totalRow % pageSize >= 0){  // 这里如果可以整除 也会+1 所以是不对的
        if (totalRow % pageSize > 0){  // 还是要按照老师写的来
            //totalRow = 0 时 模上 任何数都为0 此时判断条件totalRow % pageSize为0
            pageTotalCount++;
        }


        // 按照老师写的 即上面注释掉的if语句 如果数据库中数据总行数为0 则 下面的总页数也为0
        page.setPageTotalCount(pageTotalCount);
        /*
        * 1： 0 ，3
        * 2： 3 ，3
        * 3： 5 ，3
        * */
        //begin = 第几页 - 1 * size
        //private List<T> items
        //老师开始计算begin-> 小小算法
        //验证: pageNo = 1 pageSize = 3 => begin =0
        //验证: pageNo = 3 pageSize = 2 => begin =4
        //OK => 但是注意这里隐藏一个坑, 现在你看不到, 后面会暴露
        int begin = (pageNo - 1) * pageSize;
        List<Furn> pageItems = furnDAO.getPageItems(begin, pageSize);
        page.setItems(pageItems);
        //还差一个url => 分页导航，先放一放
        page.setUrl("");
        return page;
    }

    /**
     * 查询数据库表总行数
     * @return
     */
    @Override
    public int getTotalRow() {
        return furnDAO.getTotalRow();
    }

    /**
     * 根据传入的家居名 返回对应的page对象
     * @param name
     * @param pageNo 显示第几页。要大于0 否则sql语句报错
     * @param pageSize 每页有几条数据
     * @return
     */
    @Override
    public Page<Furn> pageByName(String name, int pageNo, int pageSize) {
        /*        //因为每页显示多少条记录，是其它地方也可以使用
        //ctrl+shift+u => 切换大小写
        public static  final  Integer PAGE_SIZE = 3;

        //表示显示当前页[显示第几页]
        //前端页面来的
        private Integer pageNo;
        //表示每页显示几条记录
        private Integer pageSize = PAGE_SIZE;
        //表示共有多少页, 他是计算得到, 通过totalRow和pageSize
        private Integer pageTotalCount;
        //表示的是共有多少条记录
        //计算得到pageTotalCount
        //是可以同数据库DB来的->DAO
        private Integer totalRow;
        //表示当前页，要显示的数据
        //从DB来的->DAO
        private List<T> items;

        //分页导航的字符串
        private String url;*/

        //构建Page对象
        Page page = new Page();

        //表示显示当前页[显示第几页]
        page.setPageNo(pageNo);
        //表示每页显示几条记录
        page.setPageSize(pageSize);

        //表示的是家居名为name的共有多少条记录
        int totalRow = furnDAO.getTotalRowByName(name);
        page.setTotalRow(totalRow);

        //表示共有多少页, 他是计算得到 通过计算得到
        //总记录数pageTotalRow 每页数据条数pageSize 取模判断是否大于0
        //pageTotalCount
        //if(totalRow % pageSize > 0){
        //    page.setPageTotalCount(totalRow/pageSize + 1);
        //}else {
        //    page.setPageTotalCount(totalRow/pageSize);
        //}
//老师分析
        //比如 6 2  =》  6 / 2 = 3
        //比如 5 2  =》  5 / 2 = 2
        //验证 7 3 =>
        //验证 0 2 =>
         /*
         totalRow = 0 数据总行数为0 或者 totalRow < pageTotalCount时
         pageTotalCount = 0 此时 totalRow % pageSize > 0
         pageTotalCount++ 后 值为1
         totalRow = 0 时 模上 任何数都为0
         */
        int pageTotalCount = totalRow / pageSize; // 除法运算 只保留整数部分舍弃小数部分 截断运算

        //if (totalRow % pageSize > 0){
        //    pageTotalCount++;
        //}
        //if (totalRow % pageSize >= 0){  // 这里如果可以整除 也会+1 所以是不对的
        if (totalRow % pageSize > 0){  // 还是要按照老师写的来
            //totalRow = 0 时 模上 任何数都为0 此时判断条件totalRow % pageSize为0
            pageTotalCount++;
        }


        // 按照老师写的 即上面注释掉的if语句 如果数据库中数据总行数为0 则 下面的总页数也为0
        page.setPageTotalCount(pageTotalCount);
        /*
         * 1： 0 ，3
         * 2： 3 ，3
         * 3： 5 ，3
         * */
        //begin = 第几页 - 1 * size
        //private List<T> items
        //老师开始计算begin-> 小小算法
        //验证: pageNo = 1 pageSize = 3 => begin =0
        //验证: pageNo = 3 pageSize = 2 => begin =4
        //OK => 但是注意这里隐藏一个坑, 现在你看不到, 后面会暴露
        int begin = (pageNo - 1) * pageSize;
        //List<Furn> pageItems = furnDAO.getPageItems(begin, pageSize);
        List<Furn> pageItems = furnDAO.getPageItemsByName(name,begin, pageSize);
        page.setItems(pageItems);
        //还差一个url => 分页导航，先放一放
        page.setUrl("");
        return page;
    }


}
