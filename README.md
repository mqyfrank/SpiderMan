# 航班可视化

## ✈获得代理池:

要爬取航空公司的航班信息，会造成对网站的大流量访问，现在很多网站都采取了防护措施，对于短时间内的高频度访问的IP地址会被封禁，或者强制刷新网页的时候输入验证码来阻止爬虫，而对于正常浏览网页的IP地址没有限制，因此要定期的更换自己的IP地址，本项目需要使用的IP地址均来自于http://www.kuaidaili.com ,该网站会定期扫描互联网，提供最新刷新有效的IP地址源，爬取的过程如下:

![ip](https://github.com/DotDashDotDash/CanWeFly/blob/master/extras/resources/ip.gif)

将爬取到的IP地址存储到数据库中，定期从 http://www.kuaidaili.com 抓取可用IP，用航空公司的网站 http://www.variflight.com/sitemap.html?AE71649A58c77= 验证IP地址的有效性，删除数据库中失效的IP地址，或者定期清除数据库中的IP，重新抓取，抓取到的IP样例如下:

![ipdb](https://github.com/DotDashDotDash/CanWeFly/blob/master/extras/resources/ipdb.png) 

通过每次抓取航网的一个页面就刷新一次代理IP，防止被封禁

## ✈ 解析variflight页面结构:

包含所有航班的页面链接: http://www.variflight.com/sitemap.html?AE71649A58c77= ,页面显示如下:

![mainpage](https://github.com/DotDashDotDash/CanWeFly/blob/master/extras/resources/mainpage.png)

用Google Chrome显示网页源代码，可以找到下面片段:

```html
<div class="list">
    <strong style="font-size:14px">2018-12-18航班列表</strong>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/sitemap/flight?AE71649A58c77=">国内航段列表</a>
    <br>
    <br>
    <a href="/flight/fnum/036925.html?AE71649A58c77=">036925</a>
    <a href="/flight/fnum/036926.html?AE71649A58c77=">036926</a>
    <a href="/flight/fnum/3K691.html?AE71649A58c77=">3K691</a>
    <a href="/flight/fnum/3K692.html?AE71649A58c77=">3K692</a>
    <a href="/flight/fnum/3K695.html?AE71649A58c77=">3K695</a>
    <a href="/flight/fnum/3K696.html?AE71649A58c77=">3K696</a>
    .......
```

页面要解析的页面内容很简单，一个是**航班名**，一个是对应航班名的链接**URL**。之后需要解析每一个航班的当日航班状态:

* 当日同一航班号可能会有一个或者多批次的航班
* 当日对应航班号可能会没有待飞的航班

以3U3047航班为例，其页面如下:

![3U3047](https://github.com/DotDashDotDash/CanWeFly/blob/master/extras/resources/3U3047.png)

可以得到一架航班包含了如下信息:

* 航空公司名称
* 航班号
* 预计起飞时间
* 实际起飞时间
* 预计降落时间
* 实际降落时间
* 出发地
* 目的地
* 航班状态(延误，到达等)

用Google Chrome检查网页源代码，观察得到上述对应的信息包含在`div class=f_content`标签当中，(Google Chrome显示的html格式比较乱，标签之间的联系不容易看，可以把对应的html源代码复制到IDEA中的html文本当中，层次结构看的比较清楚)

![htmltext](https://github.com/DotDashDotDash/CanWeFly/blob/master/extras/resources/htmltext.png)

