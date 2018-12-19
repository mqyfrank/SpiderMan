# 航班可视化

## ✈获得代理池:

要爬取航空公司的航班信息，会造成对网站的大流量访问，现在很多网站都采取了防护措施，对于短时间内的高频度访问的IP地址会被封禁，或者强制刷新网页的时候输入验证码来阻止爬虫，而对于正常浏览网页的IP地址没有限制，因此要定期的更换自己的IP地址，本项目需要使用的IP地址均来自于http://www.kuaidaili.com ,该网站会定期扫描互联网，提供最新刷新有效的IP地址源，爬取的过程如下:

![ip](https://github.com/DotDashDotDash/CanWeFly/blob/master/extras/resources/ip.gif)

将爬取到的IP地址存储到数据库中，定期从 http://www.kuaidaili.com 抓取可用IP，用航空公司的网站 http://www.variflight.com/sitemap.html?AE71649A58c77= 验证IP地址的有效性，删除数据库中失效的IP地址，或者定期清除数据库中的IP，重新抓取，抓取到的IP样例如下:

![ipdb](https://github.com/DotDashDotDash/CanWeFly/blob/master/extras/resources/ipdb.png) 

