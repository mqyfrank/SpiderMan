# 航班可视化

## ✈获得代理池:

要爬取航空公司的航班信息，会造成对网站的大流量访问，现在很多网站都采取了防护措施，对于短时间内的高频度访问的IP地址会被封禁，或者强制刷新网页的时候输入验证码来阻止爬虫，而对于正常浏览网页的IP地址没有限制，因此要定期的更换自己的IP地址，本项目需要使用的IP地址均来自于http://www.kuaidaili.com ,该网站会定期扫描互联网，提供最新刷新有效的IP地址源，爬取的过程如下:

![ip](https://github.com/DotDashDotDash/CanWeFly/blob/master/extras/resources/ip.gif)

将爬取到的IP地址存储到数据库中，定期从 http://www.kuaidaili.com 抓取可用IP，用航空公司的网站 http://www.variflight.com/sitemap.html?AE71649A58c77= 验证IP地址的有效性，删除数据库中失效的IP地址，或者定期清除数据库中的IP，重新抓取，抓取到的IP样例如下:

![ipdb](https://github.com/DotDashDotDash/CanWeFly/blob/master/extras/resources/ipdb.png) 

通过每次抓取航网的一个页面就刷新一次代理IP，防止被封禁

## ✈ 解析variflight页面结构:

包含所有航班的页面链接: http://www.variflight.com/sitemap.html?AE71649A58c77= ,页面结构如下:

```html
<div class="list">
    <strong style="font-size:14px">2018-12-18航班列表</strong>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/sitemap/flight?AE71649A58c77=">国内航段列表</a>
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

* 当日同一航班号可能会有多批次的航班，因为航班号是共享的
* 当日对应航班号可能会没有待飞的航班，因为航班号是登记在航空公司，可以不使用
* 当日一个航班号只有一架航班

