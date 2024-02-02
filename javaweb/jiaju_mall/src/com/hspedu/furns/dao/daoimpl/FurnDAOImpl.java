package com.hspedu.furns.dao.daoimpl;

import com.hspedu.furns.dao.BasicDAO;
import com.hspedu.furns.dao.FurnDAO;
import com.hspedu.furns.entity.Furn;

import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-07-17-11:15
 */
public class FurnDAOImpl extends BasicDAO<Furn> implements FurnDAO {

    /**
     * 返回所有的家居信息
     *
     * @return
     */
    @Override
    public List<Furn> queryFurnList() {

        // 构建sql
        // 这样写会出现问题 解决不了(写成* img_path字段就不能给别名imgPath了)
        // "SELECT * FROM `furn`" 在数据库端 是按照创建表时的字段获取的各个列的信息
        // 按照传入的sql 查询(即sql在数据库执行得到的结果)回来的字段
        // 去JavaBean(Furn)找对应的属性 结果发现没有，所以映射失败，
        //
        // queryMulti(sql, Furn.class)对各个字段进行反射，生成JavaBean对象时
        // 找不到setImg_path() 从而反射失败 会是null或imgPath属性的默认值
        //
        //String sql = "SELECT * FROM `furn`";

        // 字段在查询时写具体的值
        //String sql = "SELECT `id`,`name`,`maker`,`price`,`sales`,`stock`,`img_path` FROM `furn`";
        // 使用别名解决 数据库字段名和JavaBean属性名不一致的问题
        String sql = "SELECT `id`,`name`,`maker`,`price`,`sales`,`stock`,`img_path` `imgPath` FROM `furn`";
        //List<Furn> furns = queryMulti(sql, Furn.class);
        //return furns;

        return queryMulti(sql, Furn.class);
    }

    /**
     * 根据传进来的Furn对象 将数据保存到数据库
     *
     * @param furn
     * @return
     */
    @Override
    public int saveFurn(Furn furn) {
        // 构建sql
        // 默认的图片路径 直接写在sql语句里不合适 写死了 后面想改也改不了
        // 在构建Furn对象时指定imgPath 更合理一些！
        //String sql = "INSERT INTO furn( `id` ,`name` , `maker` , `price` , `sales` , `stock`, `img_path`) " +
        //        "VALUES(NULL , ? , ? , ? , ? , ?, 'assets/images/product-image/4.jpg' )";
        String sql = "INSERT INTO furn( `id` ,`name` , `maker` , `price` , `sales` , `stock`, `img_path`) " +
                "VALUES(NULL , ? , ? , ? , ? , ?, ? )";
        // 返回结果
        return update(sql, furn.getName(), furn.getMaker(),
                furn.getPrice(), furn.getSales(), furn.getStock(), furn.getImgPath());

    }

    @Override
    public Furn isExistsFurn(Furn furn) {
        //构建sql
        String sql = "SELECT * FROM `furn` WHERE  `name` =? AND `maker` =? AND `price` =? AND" +
                " `sales` =?  AND `stock` =? AND `img_path` ='assets/images/product-image/4.jpg'";

        return querySingle(sql, Furn.class, furn.getName(), furn.getMaker(), furn.getPrice(), furn.getSales(), furn.getStock());


    }

    /**
     * 根据Id删除数据库中对应的Furn数据信息
     *
     * @param id
     * @return 受影响的行数 删除失败返回0 成功返回1
     */
    @Override
    public int deleteFurnById(Integer id) {

        // 构建Sql
        // 注意：Mysql的where语句中不可以使用别名！
        //String sql = "SELECT * FROM `furn` WHERE  `name` =? AND `maker` =? AND `price` =? AND" +
        //        " `sales` =?  AND `stock` =? AND `img_path` `imgPath` =?"; //sql语法错误

        //String sql = "SELECT * FROM `furn` WHERE  `name` =? AND `maker` =? AND `price` =? AND" +
        //        " `sales` =?  AND `stock` =? AND `img_path` =?"; // 这里没必要使用别名

        // 因为通过list 获取到了furn的id 所以只需要将要删除的furn的id传进来 进行查询即可
        String sql = "DELETE FROM `furn` WHERE `id` = ?"; //

        return update(sql, id);

    }

    /**
     * 根据id查询家居信息
     *
     * @param id
     * @return
     */
    @Override
    public Furn queryFurnById(int id) {

        //构建sql
        // 这里使用* 反射时会失败 因为找不到setImg_Path()方法
        //String sql = "SELECT * FROM `furn` WHERE `id`=100";

        String sql = "SELECT `id`,`name`,`maker`,`price`,`sales`,`stock`,`img_path` `imgPath` FROM `furn` WHERE `id` = ?";
        return querySingle(sql, Furn.class, id);
    }

    /**
     * 根据传进来的Furn对象 更新数据库相应的数据
     *
     * @param furn
     * @return 受影响的行数
     */
    @Override
    public int updateFurn(Furn furn) {
        //构建sql
        String sql = "UPDATE `furn` SET `name`=?,`maker` =?,`price`=?,`sales`=?,`stock`=? ,`img_path`=? WHERE `id` = ?";

        // 暴露出更新过程中 出现异常后导致一次事务对多张表操作 有的成功了有的没成功
        // 出现数据不一致问题
        //String sql = "UPDATEX `furn` SET `name`=?,`maker` =?,`price`=?,`sales`=?,`stock`=? ,`img_path`=? WHERE `id` = ?";

        return update(sql, furn.getName(), furn.getMaker(), furn.getPrice(), furn.getSales(), furn.getStock(), furn.getImgPath(), furn.getId());

    }


    //@Override
    //public int deleteFurnById(Furn furn) {
    //
    //    // 构建Sql
    //    String sql = "SELECT * FROM `furn` WHERE  `name` =? AND `maker` =? AND `price` =? AND" +
    //            " `sales` =?  AND `stock` =? AND `img_path` `imgPath` =?";
    //
    //    Furn furn1 = querySingle(sql, Furn.class, furn.getName(), furn.getMaker(), furn.getPrice(), furn.getSales(), furn.getStock(), furn.getImgPath());
    //
    //
    //    return 0;
    //}


    /**
     * 获取数据总行数
     *
     * @return
     */
    @Override
    public int getTotalRow() {
        //构建sql语句
        String sql = "SELECT COUNT(*) FROM `furn`";

        Object totalRow = queryScalar(sql);

        //totalRow的运行类型是Long 强转会抛异常 在数据库查询COUNT(*) 返回到java中是一个Long类型数据
        //java.lang.ClassCastException: java.lang.Long cannot be cast to java.lang.Integer
        //return (Integer)totalRow;

        //使用Number类 的方法 所有的数值型的包装类都继承于Number类 向上转型没问题
        return ((Number) totalRow).intValue();

        // 或者转为Long类型的数 返回值使用Long 但是Long类型太大了 用不到这么大的数，不划算
        //return  (Long)totalRow ;

    }

    /**
     * 从数据库中获取当前页，要显示的数据 需要提供开始的
     *
     * @param begin    从第begin条数据开始取
     * @param pageSize 每页的大小
     * @return 要显示的数据 是一个Furn的集合
     */
    @Override
    public List<Furn> getPageItems(int begin, int pageSize) {

        //构建sql语句 不可以使用 *
        //String sql = "SELECT * FROM `furn` LIMIT ? , ?";
        // 下面的语句使用在数据库查询完并返回的字段名进行反射 时 找不到setImg_path() 反射失败 填充默认值imgPath='assets/images/product-image/default.jpg'
        //List<Furn> furns = queryMulti(sql,Furn.class, begin, size);

        //构建sql语句
        String sql = "SELECT `id`,`name`,`maker`,`price`,`sales`,`stock`,`img_path` `imgPath` FROM `furn` LIMIT ? , ?";
        //查询
        List<Furn> furns = queryMulti(sql, Furn.class, begin, pageSize);
        return furns;
    }

    /**
     * 根据传入的家居名 查询包含此家居名的总记录数
     *
     * @param name
     * @return
     */
    @Override
    public int getTotalRowByName(String name) {
        //构建Sql
        String sql = "SELECT COUNT(*) FROM `furn` WHERE `name` LIKE ?"; // 正确写法

        //String sql = "SELECT COUNT(*) FROM `furn` WHERE `name` LIKE '%?%'"; // 报错
        //String sql = "SELECT COUNT(*) FROM `furn` WHERE `name` LIKE '%"+"?"+"%'"; // 报错

        // 返回包含name的总记录数totalCount
        Object totalCount = queryScalar(sql, "%"+name+"%");// 正确写法
        
        //Object totalCount = queryScalar(sql, name);// 报错
        // 因为这里的totalCount查询返回来的运行类型是Long类型 所以先要转成Number类型

        return ((Number) totalCount).intValue();
    }

    /**
     * 根据传入的家居名 包含此家居名的 家居信息
     *
     * @param name
     * @param begin 从第begin+1条数据开始取
     * @param pageSize 每页的取出多少记录
     * @return 返回根据名字查询出来的结果 分页后的
     * @return
     */
    @Override
    public List<Furn> getPageItemsByName(String name,int begin, int pageSize) {

        // 构建Sql
        String sql = "SELECT `id`,`name`,`maker`,`price`,`sales`,`stock`,`img_path` `imgPath` FROM `furn` where `name` LIKE ? LIMIT ?, ?";

        // 返回包含name的所有的furn数据的List集合
        List<Furn> furns = queryMulti(sql, Furn.class, "%"+name+"%",begin,pageSize);

        return furns;
    }


}
