package com.hspedu.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 1. Cookie 有效路径 Path 的设置
 * 2. Cookie 的 path 属性可以有效的过滤哪些 Cookie 可以发送给服务器。哪些不发。 path
 * 属性是通过请求的地址来进行有效的过滤
 * 3. 规则如下:
 * cookie1.setPath = /工程路径
 * cookie2.setPath = /工程路径/aaa
 * 请求地址: http://ip:端口/工程路径/资源
 * cookie1 会发给服务器
 * cookie2 不会发给服务器
 * 请求地址: http://ip:端口/工程路径/aaa/资源
 * cookie1 会发给服务器
 * cookie2 会发给服务器
 *
 * 结论：请求地址只要包含 cookie设置的有效路径 该cookie就会被浏览器携带给请求的资源
 * 如果我们没有设置cookie有效路径，默认就是 /工程路径，此时访问这个项目的资源默认都会携带 默认有效路径cookie
 * 即
 * cookie是否会携带给某个域名的服务器，
 * 取决于是否在设置的cookie生命周期内 和 cookie设置的有效路径是否匹配上(在默认情况下为当前工程路径)
 *
 *
 * 是的，一个cookie是否被携带到特定域名的服务器上，确实取决于以下几个关键因素：
 *
 * Cookie的生命周期：Cookie的生命周期指定了cookie存活的时间。只有在这个生命周期内，cookie才有效，能被浏览器存储和随请求发送到服务器。Cookie的生命周期可以通过Expires属性或Max-Age属性来设置：
 * Expires指定一个具体的日期和时间，cookie在这个时间点后将被视为过期。
 * Max-Age指定从cookie被创建开始，直到过多少秒后过期。
 * Cookie的路径（Path）：Cookie的路径属性定义了哪些URL路径可以访问该cookie。只有当发起请求的URL匹配这个路径时，cookie才会被浏览器包含在请求中发送给服务器。如果设置为/, 则网站上的所有页面都可以访问这个cookie。如果指定为特定路径，如/dir/，则只有以/dir/开头的路径才能访问该cookie。
 * Cookie的域（Domain）：此属性定义了哪些域可以接收cookie。如果不指定，默认为设置cookie的那个域名。如果指定了域名，例如设置为.example.com，那么所有以example.com结尾的域都可以接收到这个cookie，包括www.example.com、subdomain.example.com等。
 * 请求的类型：只有在发起HTTP请求时，如页面请求或API调用，cookie才会被包括在请求头中发送给服务器。而对于静态资源的请求（如图片、CSS文件、JavaScript文件等），是否携带cookie还取决于请求本身是否被标记为同源或者有相应的CORS（跨源资源共享）策略允许携带Credentials。
 * 示例
 * 假设你设置了以下cookie：
 *
 * http
 * Copy code
 * Set-Cookie: ID=12345; Max-Age=3600; Path=/; Domain=.example.com
 * 这意味着：
 *
 * Cookie ID 值为 12345。
 * Max-Age 为3600秒（1小时），在这之后cookie过期。
 * Path 为 /，cookie对整个网站有效。
 * Domain 为 .example.com，所有以example.com结尾的域都可以接收到这个cookie。
 * 只有当这些条件满足，且在有效时间内，cookie才会随请求发送到服务器。这种机制确保了cookie的使用既灵活又安全，适应了不同的网络应用场景。
 */
public class CookiePath extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CookiePath 被调用...");
        //1. 创建两个cookie
        Cookie cookie = new Cookie("address", "bj");
        Cookie cookie2 = new Cookie("salary", "20000");
        //2. 设置不同有效路径
        //   request.getContextPath() => /cs
        cookie.setPath(request.getContextPath());
        //   cookie2有效路径 /cs/aaa
        cookie2.setPath(request.getContextPath() + "/aaa");

        //老师说明：如果我们没有设置cookie有效路径，默认就是 /工程路径

        //3. 保存到浏览器
        response.addCookie(cookie);
        response.addCookie(cookie2);

        //4. 给浏览器返回信息
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println("<h1>设置cookie有效路径成功</h1>");
        writer.flush();
        writer.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
