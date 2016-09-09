package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/9/1.
 */
public class FollowRecord {


    /**
     * Status : 0
     * Msg : Success
     */

    private HeadBean Head;
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
         * AlongUserName : 13533638462
         * AlongAmount : 50000
         * AlongTime : 2016-09-08 16:37:26
         */

        private List<AlongRecordsBean> AlongRecords;

        public List<AlongRecordsBean> getAlongRecords() {
            return AlongRecords;
        }

        public void setAlongRecords(List<AlongRecordsBean> AlongRecords) {
            this.AlongRecords = AlongRecords;
        }

        public static class AlongRecordsBean {
            private String AlongUserName;
            private int AlongAmount;
            private String AlongTime;

            public String getAlongUserName() {
                return AlongUserName;
            }

            public void setAlongUserName(String AlongUserName) {
                this.AlongUserName = AlongUserName;
            }

            public int getAlongAmount() {
                return AlongAmount;
            }

            public void setAlongAmount(int AlongAmount) {
                this.AlongAmount = AlongAmount;
            }

            public String getAlongTime() {
                return AlongTime;
            }

            public void setAlongTime(String AlongTime) {
                this.AlongTime = AlongTime;
            }
        }
    }
}
