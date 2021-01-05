package com.live.bean;

public class StartLiveBean {

    /**
     * errno : 0
     * errmsg :
     * data : {"id":"3","owner":"f4718973-0a02-4f6b-aa9a-19a22d9aa9ad","title":"小白直播间","push_url":"rtmp://livepush.cdwan.cn/shop/f4718973-0a02-4f6b-aa9a-19a22d9aa9ad?txSecret=073b20fb9bd2ff5506baa9da361772f8&txTime=5ff50495","play_url":"rtmp://live.cdwan.cn/shop/f4718973-0a02-4f6b-aa9a-19a22d9aa9ad?txSecret=073b20fb9bd2ff5506baa9da361772f8&txTime=5ff50495","status":1}
     */

    private int errno;
    private String errmsg;
    private DataBean data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 3
         * owner : f4718973-0a02-4f6b-aa9a-19a22d9aa9ad
         * title : 小白直播间
         * push_url : rtmp://livepush.cdwan.cn/shop/f4718973-0a02-4f6b-aa9a-19a22d9aa9ad?txSecret=073b20fb9bd2ff5506baa9da361772f8&txTime=5ff50495
         * play_url : rtmp://live.cdwan.cn/shop/f4718973-0a02-4f6b-aa9a-19a22d9aa9ad?txSecret=073b20fb9bd2ff5506baa9da361772f8&txTime=5ff50495
         * status : 1
         */

        private String id;
        private String owner;
        private String title;
        private String push_url;
        private String play_url;
        private int status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPush_url() {
            return push_url;
        }

        public void setPush_url(String push_url) {
            this.push_url = push_url;
        }

        public String getPlay_url() {
            return play_url;
        }

        public void setPlay_url(String play_url) {
            this.play_url = play_url;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
