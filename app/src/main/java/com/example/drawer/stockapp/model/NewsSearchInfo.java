package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/9/10.
 */
public class NewsSearchInfo {


    /**
     * reason : 查询成功
     * result : [{"title":"Hurom惠人原汁机怎么样? HU9026WN好吗","content":"韩国惠人多功能原汁机采用O2O的经营模式,线下在全国各地建立专卖店,线上与天猫合作设立官方旗舰店。<em>hu<\/em>rom惠人官方旗舰店地址:下面为小编整理的用户对韩国惠人多功能原汁机的真实评价: 【1】物流很给力,服务态度好,简单快捷,果汁口感很好,棒棒的,孩子也很爱喝,物超所值,还送...","img_width":"600","full_title":"Hurom惠人原汁机怎么样? HU9026WN好吗","pdate":"4天前","src":"it168","img_length":"670","img":"http://p7.qhimg.com/t0159df95e462c9ba19.jpg","url":"http://elec.it168.com/a2016/0905/2900/000002900808.shtml","pdate_src":"2016-09-05 16:47:00"},{"title":"惠人原汁机HU18WN3L这款怎么样 正品价格","content":"代表型号:惠人小天使三代,<em>hu<\/em>18wnm3L等    惠人三代原汁机<em>hu<\/em>18wnm3L: (大促进行中,非常值得一看)  惠人二代--  标志:43转,450ML,粗细双网。  代表型号:小金主,<em>hu<\/em>880sgm1,<em>hu<\/em>o16sg等    惠人一代--  标志:70转,300-450ML  代表型号:<em>hu<\/em>780,<em>hu<\/em>es790,<em>hu<\/em>zk899等  惠人3代新款代表型号:  惠...","img_width":"195","full_title":"惠人原汁机HU18WN3L这款怎么样 正品价格","pdate":"13天前","src":"电脑之家","img_length":"553","img":"http://p0.qhimg.com/t01532b81f55f857149.jpg","url":"http://article.pchome.net/content-1950525.html","pdate_src":"2016-08-27 23:01:00"},{"title":"HDS副总裁及全球首席技术官 Hu Yoshida:数字化转型是企业获得新力量的源泉","content":"让他们的传统IT资产,更加高效,更加敏捷,另外一方面,对于他们的一些新业务,新的应用,他们要能够把这些业务和应用,去部署在一个更加创新,更加灵活的互联网或者是云环境当中。\"具有丰富实战经验的<em>Hu<\/em> Yoshida更懂得企业转型需要循序渐进的道理。 当谈到具体案例时,<em>Hu<\/em> Yoshida讲到...","img_width":"750","full_title":"HDS副总裁及全球首席技术官 Hu Yoshida:数字化转型是企业获得新力量的源泉","pdate":"16天前","src":"财经网","img_length":"500","img":"http://p3.qhimg.com/t012c457275d0d95f65.jpg","url":"http://tech.caijing.com.cn/20160824/4167476.shtml","pdate_src":"2016-08-24 22:25:24"},{"title":"Hu Yoshida:数字化转型是企业获得新力量的源泉","content":"物联网时代已经距离我们越来越近,每个人都能感受到物联网给我们生活所带来的变化,但同时问题也来了,面对每天产生的海量数据,物联网生态链上的各个企业又该如何把握。<em>Hu<\/em> Yoshida坦言,物联网时代会产生巨量的数据,我们永远不可能每天把物联网当中的产生的所有的数据都存储下...","img_width":"750","full_title":"Hu Yoshida:数字化转型是企业获得新力量的源泉","pdate":"16天前","src":"财经网","img_length":"500","img":"http://p0.qhimg.com/t012c457275d0d95f65.jpg","url":"http://m.caijing.com.cn/article/74827?target=blank","pdate_src":"2016-08-24 22:24:57"}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    /**
     * title : Hurom惠人原汁机怎么样? HU9026WN好吗
     * content : 韩国惠人多功能原汁机采用O2O的经营模式,线下在全国各地建立专卖店,线上与天猫合作设立官方旗舰店。<em>hu</em>rom惠人官方旗舰店地址:下面为小编整理的用户对韩国惠人多功能原汁机的真实评价: 【1】物流很给力,服务态度好,简单快捷,果汁口感很好,棒棒的,孩子也很爱喝,物超所值,还送...
     * img_width : 600
     * full_title : Hurom惠人原汁机怎么样? HU9026WN好吗
     * pdate : 4天前
     * src : it168
     * img_length : 670
     * img : http://p7.qhimg.com/t0159df95e462c9ba19.jpg
     * url : http://elec.it168.com/a2016/0905/2900/000002900808.shtml
     * pdate_src : 2016-09-05 16:47:00
     */

    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String title;
        private String pdate;
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPdate() {
            return pdate;
        }

        public void setPdate(String pdate) {
            this.pdate = pdate;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
