package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/8/9.
 */
public class CommnetInfo {

    /**
     * Status : 0
     * Msg : success
     */

    private HeadBean Head;
    /**
     * PageInfo : {"PageIndex":0,"PageCount":1,"PageSize":10}
     * Data : [{"Id":"0","NickName":"评论1","Likes":32,"Username":"老三","ImgUrl":"http://www.supwin.com/media/1009/logo.png","UpdateTime":null,"Content":"对对对，大师兄说的对"},{"Id":"1","NickName":"评论2","Likes":66,"Username":"老三","ImgUrl":"http://www.supwin.com/media/1009/logo.png","UpdateTime":null,"Content":"对对对，二师兄说的对"},{"Id":"2","NickName":"评论3","Likes":88,"Username":"老三","ImgUrl":"http://www.supwin.com/media/1009/logo.png","UpdateTime":null,"Content":"对对对，师傅师兄说的对"}]
     */

    private ResultBean Result;

    public HeadBean getHead() {
        return Head;
    }

    public void setHead(HeadBean Head) {
        this.Head = Head;
    }

    public ResultBean getResult() {
        return Result;
    }

    public void setResult(ResultBean Result) {
        this.Result = Result;
    }

    public static class HeadBean {
        private int Status;
        private String Msg;

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public String getMsg() {
            return Msg;
        }

        public void setMsg(String Msg) {
            this.Msg = Msg;
        }
    }

    public static class ResultBean {
        /**
         * Id : 0
         * NickName : 评论1
         * Likes : 32
         * Username : 老三
         * ImgUrl : http://www.supwin.com/media/1009/logo.png
         * UpdateTime : null
         * Content : 对对对，大师兄说的对
         */

        private List<DataBean> Data;

        public List<DataBean> getData() {
            return Data;
        }

        public void setData(List<DataBean> Data) {
            this.Data = Data;
        }

        public static class DataBean {
            private String Id;
            private String NickName;
            private String Username;
            private String ImgUrl;
            private String Content;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getNickName() {
                return NickName;
            }

            public void setNickName(String NickName) {
                this.NickName = NickName;
            }

            public String getUsername() {
                return Username;
            }

            public void setUsername(String Username) {
                this.Username = Username;
            }

            public String getImgUrl() {
                return ImgUrl;
            }

            public void setImgUrl(String ImgUrl) {
                this.ImgUrl = ImgUrl;
            }

            public String getContent() {
                return Content;
            }

            public void setContent(String Content) {
                this.Content = Content;
            }
        }
    }
}
