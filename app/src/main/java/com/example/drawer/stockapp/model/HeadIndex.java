package com.example.drawer.stockapp.model;

/**
 * 资讯指数对象(和精选课程、合集，推荐人信息共用)
 *
 */
public class HeadIndex {
    private String indexName;    //指数名称   （精选课程/合集名字）（推荐人名字）(我的组合添加，策略名)
    private String indexImage;    //  （精选课程、合集图片）（推荐人图片）
    private String indexNum;     //数量     （定制精选数量）（我的组合添加，编号）
    private String indexPersent;    //百分比     （评论人数）（由谁推荐）（我的组合添加，百分数）

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(String indexNum) {
        this.indexNum = indexNum;
    }

    public String getIndexPersent() {
        return indexPersent;
    }

    public void setIndexPersent(String indexPersent) {
        this.indexPersent = indexPersent;
    }

    public String getIndexImage() {
        return indexImage;
    }

    public void setIndexImage(String indexImage) {
        this.indexImage = indexImage;
    }
}
