package com.bw.movie.mvp.model.bean;

public class LoginBean {

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {

        private String sessionId;
        private String userId;
        private UserInfoBean userInfo;
        public ResultBean(String sessionId, String userId) {
            this.sessionId = sessionId;
            this.userId = userId;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserInfoBean {
            /**
             * birthday : 320256000000
             * headPic : http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg
             * id : 460
             * lastLogStringime : 1552387993000
             * nickName : 你的爸爸
             * phone : 15902460752
             * sex : 1
             */

            private long birthday;
            private String headPic;
            private String id;
            private long lastLogStringime;
            private String nickName;
            private String phone;
            private String sex;

            public UserInfoBean(String headPic, String nickName) {
                this.headPic = headPic;
                this.nickName = nickName;
            }

            public long getBirthday() {
                return birthday;
            }

            public void setBirthday(long birthday) {
                this.birthday = birthday;
            }

            public String getHeadPic() {
                return headPic;
            }

            public void setHeadPic(String headPic) {
                this.headPic = headPic;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public long getLastLogStringime() {
                return lastLogStringime;
            }

            public void setLastLogStringime(long lastLogStringime) {
                this.lastLogStringime = lastLogStringime;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }
        }
    }
}
