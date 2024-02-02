package com.hspedu.furns.web;

import com.google.gson.Gson;
import com.hspedu.furns.entity.Cart;
import com.hspedu.furns.entity.CartItem;
import com.hspedu.furns.entity.Furn;
import com.hspedu.furns.service.FurnService;
import com.hspedu.furns.service.impl.FurnServiceImpl;
import com.hspedu.furns.utils.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangda
 * @description:
 * @create 2023-08-02-22:15
 */
public class CartServlet extends BasicServlet {

    private FurnService furnService = new FurnServiceImpl();

    /**
     * //增加一个添加家居数据到购物车的方法
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //http://localhost:8080/jiaju_mall/cartServlet?action=addCartItem&id=246
        // 得到添加家居的id 获取前端传过来的家居id 默认给一个0
        // queryFurnById() 查不到id为0的furn返回一个null
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        // 购物车中 被添加的购物项的数量
        // 第一次添加到购物车 或者缺货时thisCartItemCount=undefined 默认给一个0

        int thisCartItemCount = DataUtils.parseInt(req.getParameter("thisCartItemCount"), 0);

        // 根据id到数据库查询对应的furn信息 获取到id对应的Furn
        Furn furn = furnService.queryFurnById(id);

        //判断
        //老师，先把正常的逻辑走完，再处理异常情况
        //todo
        //根据查询到的furn对象构建对应的CartItem对象
        if (furn != null) {
            // 构建CartItem对象之前先进行库存检验 查看库存是否大于0
            // 如果大于0 才构建CartItem对象 进行添加到购物车操作
            // 否则给前端设置提示信息 "暂时缺货"

            // 这里保证了购物车里的商品数小于等于库存的商品数
            // 前提是 不可以购买超过库存量的商品
            //if (furn.getStock() <= 0){

            // 如果购物车中该商品的数量小于该商品的库存 就可以添加商品
            // 即购物车中该商品的数量大于等于该商品的库存 时不可以添加到购物车 直接返回！！

            // 缺货的商品因为 数量一直为0 所以一直进入if中
            if (thisCartItemCount >= furn.getStock()){
                //req.getSession().setAttribute("checkStock","暂时缺货");

                //重定向到原页面 刷新页面
                resp.sendRedirect(req.getHeader("Referer"));
                return;//直接返回


            }
            // 商品有库存 第一次添加会直接到这里 不会进入到上面的if语句 直接添加一个
            // 只有在购物车中该商品的数量等于库存时 会停止添加 直接返回

            //查询到了根据furn 构建CartItem对象
            // 默认一次添加一个 因此构造cartItem时 count填1 一个时单价等于总价
            CartItem cartItem = new CartItem(furn.getId(), furn.getName(),
                    1, furn.getPrice(), furn.getPrice());

            //从session中获取cart对象
            // 将该cartItem 放入到session的购物车cart对象中
            Cart cart = (Cart) req.getSession().getAttribute("cart");

            if (cart == null) {//说明当前用户的session没有cart
                // cart为空 就先创建一个cart对象 放入到session
                cart = new Cart();
                req.getSession().setAttribute("cart", cart);
            }




            //将cartItem 加入到cart
            // cart不为空 直接将cartItem添加到购物车对象cart中
            cart.addItem(cartItem);

            //添加完毕后，需要返回到添加家居的页面
            // 添加到购物车成功 返回到添加购物车的页面
            System.out.println("cart= " + cart);

            //将购物车对象设置到request域中
            //req.setAttribute("cart",cart);

            // 购物车对象cart已经在session域中 因此在前端可以直接取出来

            // 重定向到从哪个页面来的
            //Referer: http://localhost:8080/jiaju_mall/customerFurnServlet?action=pageByName&pageNo=5
            String referer = req.getHeader("Referer");
            //req.getRequestDispatcher(referer).forward(req,resp);

            // 使用重定向 刷新页面
            resp.sendRedirect(referer);


        } else {
            System.out.println("cartServlet addItem 根据前端传来的id 获取furn对象为null");


        }

    }
    /**
     * //ajax方式添加购物车 刷新购物车图标显示的数据
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItemByAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //http://localhost:8080/jiaju_mall/cartServlet?action=addCartItem&id=246
        // 得到添加家居的id 获取前端传过来的家居id 默认给一个0
        // queryFurnById() 查不到id为0的furn返回一个null
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        // 购物车中 被添加的购物项的数量
        // 第一次添加到购物车 或者缺货时thisCartItemCount=undefined 默认给一个0

        int thisCartItemCount = DataUtils.parseInt(req.getParameter("thisCartItemCount"), 0);

        // 根据id到数据库查询对应的furn信息 获取到id对应的Furn
        Furn furn = furnService.queryFurnById(id);

        //判断
        //老师，先把正常的逻辑走完，再处理异常情况
        //todo
        //根据查询到的furn对象构建对应的CartItem对象
        if (furn != null) {
            // 构建CartItem对象之前先进行库存检验 查看库存是否大于0
            // 如果大于0 才构建CartItem对象 进行添加到购物车操作
            // 否则给前端设置提示信息 "暂时缺货"

            // 这里保证了购物车里的商品数小于等于库存的商品数
            // 前提是 不可以购买超过库存量的商品
            //if (furn.getStock() <= 0){

            // 如果购物车中该商品的数量小于该商品的库存 就可以添加商品
            // 即购物车中该商品的数量大于等于该商品的库存 时不可以添加到购物车 直接返回！！
            //Map<String, Object> resultMap = null;
            // 缺货的商品因为 数量一直为0 所以一直进入if中
            //if (thisCartItemCount >= furn.getStock()){
            //    //req.getSession().setAttribute("checkStock","暂时缺货");
            //
            //    ////重定向到原页面 刷新页面
            //    //resp.sendRedirect(req.getHeader("Referer"));
            //
            //    // 使用ajax方式返回数据 前端购物车数据不变化
            //
            //    //resultMap = new HashMap<>();
            //    //// 将点击的此商品的数量放入到 ajax要返回的集合中
            //    //boolean flag = false;
            //    //resultMap.put("flag",flag);
            //
            //    return;//直接返回 而不往购物车中添加
            //
            //
            //}
            // 商品有库存 第一次添加会直接到这里 不会进入到上面的if语句 直接添加一个
            // 只有在购物车中该商品的数量等于库存时 会停止添加 直接返回

            //查询到了根据furn 构建CartItem对象
            // 默认一次添加一个 因此构造cartItem时 count填1 一个时单价等于总价
            CartItem cartItem = new CartItem(furn.getId(), furn.getName(),
                    1, furn.getPrice(), furn.getPrice());

            //从session中获取cart对象
            // 将该cartItem 放入到session的购物车cart对象中
            Cart cart = (Cart) req.getSession().getAttribute("cart");

            if (cart == null) {//说明当前用户的session没有cart
                // cart为空 就先创建一个cart对象 放入到session
                cart = new Cart();
                req.getSession().setAttribute("cart", cart);
            }




            //将cartItem 加入到cart
            // cart不为空 直接将cartItem添加到购物车对象cart中
            cart.addItem(cartItem);

            //添加完毕后，需要返回到添加家居的页面
            // 添加到购物车成功 返回到添加购物车的页面
            System.out.println("cart= " + cart);

            //将购物车对象设置到request域中
            //req.setAttribute("cart",cart);

            // 购物车对象cart已经在session域中 因此在前端可以直接取出来

            // 重定向到从哪个页面来的
            //Referer: http://localhost:8080/jiaju_mall/customerFurnServlet?action=pageByName&pageNo=5
            //String referer = req.getHeader("Referer");
            //req.getRequestDispatcher(referer).forward(req,resp);

            // 使用重定向 刷新页面
            //resp.sendRedirect(referer);

            //使用ajax返回数据 自己写的 对的
            // 获取现在的购物车中 商品总数
            //Integer totalCount = cart.getTotalCount();
            //resultMap = new HashMap<>();
            //resultMap.put("totalCount",totalCount);
            //String resultJson = new Gson().toJson(resultMap);
            //resp.setContentType("text/json;charset=utf-8");
            //resp.getWriter().write(resultJson);

            //老韩写的
            //添加完毕后，返回json数据, 给前端
            //前端得到json数据后，进行局部刷新即可
            //老师思路
            //1. 规定json格式 {"cartTotalCount", 3}
            //2. 创建Map
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("cartTotalCount", cart.getTotalCount());
            //3. 转成json
            String resultJson = new Gson().toJson(resultMap);
            //4. 返回
            resp.getWriter().write(resultJson);


        } else {
            System.out.println("cartServlet addItem 根据前端传来的id 获取furn对象为null");


        }

    }

    /**
     * //增加一个 修改某一项家居的count和totalPrice数据的方法
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //http://localhost:8080/jiaju_mall/cartServlet?action=addCartItem&id=246
        // 得到修改家居的id 获取前端传过来的家居id 默认给一个0
        // queryFurnById() 查不到id为0的furn返回一个null
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        String update = req.getParameter("update");

        // 根据id到数据库查询对应的furn信息 获取到id对应的Furn
        //Furn furn = furnService.queryFurnById(id);

        //判断
        //老师，先把正常的逻辑走完，再处理异常情况
        //todo
        //根据查询到的furn对象构建对应的CartItem对象
        //if (furn != null) {
            //查询到了根据furn 构建CartItem对象
            // 默认一次修改一个 因此构造cartItem时 count填1 一个时单价等于总价
            //CartItem cartItem = new CartItem(furn.getId(), furn.getName(),
            //        1, furn.getPrice(), furn.getPrice());

            //从session中获取cart对象
            Cart cart = (Cart) req.getSession().getAttribute("cart");

            if (cart == null) {//说明当前用户的session没有cart
                // cart为空 就先创建一个cart对象 放入到session
                cart = new Cart();
                req.getSession().setAttribute("cart", cart);
            }

            // 修改cart
            // cart不为空 获取购物车对象cart中的该id的商品项
            //HashMap items = cart.getItems();
            //get():根据键获取值
            //CartItem cartItemById = (CartItem) items.get(id);
            //if (cartItemById != null) {
                //说明购物车中有该购物车项 对其进行修改

            //}
            cart.updateItem(id, update);

            System.out.println("cart= " + cart);

            //将购物车对象设置到request域中
            //req.setAttribute("cart",cart);

            // 购物车对象cart已经在session域中 因此在前端可以直接取出来

            // 重定向到从哪个页面来的
            //Referer: http://localhost:8080/jiaju_mall/customerFurnServlet?action=pageByName&pageNo=5
            String referer = req.getHeader("Referer");
            //req.getRequestDispatcher(referer).forward(req,resp);

            // 使用重定向 刷新页面
            resp.sendRedirect(referer);


        //} else {
        //    System.out.println("cartServlet addItem 根据前端传来的id 获取furn对象为null");
        //
        //
        //}

    }

    /**
     * 更新某个CartItem的数量, 即更新购物车
     * 老韩的逻辑
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        //这里可以根据业务来处理
        int count = DataUtils.parseInt(req.getParameter("count"), 1);

        //获取session中购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null != cart) {
            cart.updateCount(id, count);
        }
        //回到请求更新购物车的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }



    /**
     * 删除购物车中的一项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = DataUtils.parseInt(req.getParameter("id"), 0);

        Cart cart = (Cart)req.getSession().getAttribute("cart");

        //Furn furn = furnService.queryFurnById(id);

        //if (furn != null){

        if (cart != null){
            cart.deleteItem(id);

            // 重定向到从哪个页面来的
            //Referer: http://localhost:8080/jiaju_mall/customerFurnServlet?action=pageByName&pageNo=5
            String referer = req.getHeader("Referer");
            //req.getRequestDispatcher(referer).forward(req,resp);

            // 使用重定向 刷新页面
            resp.sendRedirect(referer);
        }

    }

    protected void delItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        //获取到cart购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null != cart) {
            cart.delItem(id);
        }
        //返回到请求删除购物车的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }


    /**
     * 清空购物车中的一项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Cart cart = (Cart)req.getSession().getAttribute("cart");

            if (cart != null){
                cart.clear();
            }


            // 重定向到从哪个页面来的
            //Referer: http://localhost:8080/jiaju_mall/customerFurnServlet?action=pageByName&pageNo=5
            String referer = req.getHeader("Referer");
            //req.getRequestDispatcher(referer).forward(req,resp);

            // 使用重定向 刷新页面
            resp.sendRedirect(referer);

    }


}
