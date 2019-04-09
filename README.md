# 介绍

有创建动态定时任务需求，调研了两天时间，没有找到springboot+quartz+mongo的DEMO可以研究。
索性就自己写了demo，踩了一些坑: 
    1. springboot 2.x 对quartz 做了自动配置设计， 但不支持 mongodb 数据源
    2. 使用 [michaelklishin/quartz-mongodb](https://github.com/michaelklishin/quartz-mongodb)
    作为数据源桥接
