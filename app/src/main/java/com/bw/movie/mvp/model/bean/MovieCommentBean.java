package com.bw.movie.mvp.model.bean;

import java.util.List;

public class MovieCommentBean
{

    /**
     * result : [{"commentContent":"电影是什么鬼","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":153,"commentTime":1552916633000,"commentUserId":12086,"commentUserName":"深海霸主皮皮虾丶","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"电影好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-03-12/20190312114311.jpg","commentId":136,"commentTime":1552360802000,"commentUserId":12090,"commentUserName":"ying","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"Aimee看过","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":131,"commentTime":1552360273000,"commentUserId":12095,"commentUserName":"Aimee","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"电影是什么鬼","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":127,"commentTime":1552359460000,"commentUserId":12085,"commentUserName":"阿么","greatNum":1,"hotComment":0,"isGreat":0,"replyNum":1}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commentContent : 电影是什么鬼
         * commentHeadPic : http://172.17.8.100/images/movie/head_pic/bwjy.jpg
         * commentId : 153
         * commentTime : 1552916633000
         * commentUserId : 12086
         * commentUserName : 深海霸主皮皮虾丶
         * greatNum : 0
         * hotComment : 0
         * isGreat : 0
         * replyNum : 0
         */

        private String commentContent;
        private String commentHeadPic;
        private int commentId;
        private long commentTime;
        private String commentUserId;
        private String commentUserName;
        private int greatNum;
        private String hotComment;
        private int isGreat;
        private String replyNum;

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentHeadPic() {
            return commentHeadPic;
        }

        public void setCommentHeadPic(String commentHeadPic) {
            this.commentHeadPic = commentHeadPic;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public String getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(String commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getCommentUserName() {
            return commentUserName;
        }

        public void setCommentUserName(String commentUserName) {
            this.commentUserName = commentUserName;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public String getHotComment() {
            return hotComment;
        }

        public void setHotComment(String hotComment) {
            this.hotComment = hotComment;
        }

        public int getIsGreat() {
            return isGreat;
        }

        public void setIsGreat(int isGreat) {
            this.isGreat = isGreat;
        }

        public String getReplyNum() {
            return replyNum;
        }

        public void setReplyNum(String replyNum) {
            this.replyNum = replyNum;
        }
    }
}
