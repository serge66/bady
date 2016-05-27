package com.huweibady.baby.bean;

/**
 * 用户信息javaBean
 *
 * @author Administrator
 */
public class UserBean {

    /**
     * 用户id
     */
    public int uid;
    /**
     * 用户手机号
     */
    public int mobile;
    /**
     * 用户名
     */
    public String name;
    /**
     * 用户头像
     */
    public String portrait;

    /**
     * 用户类型，1是家长，2是教师
     */
    public int user_type;

    public String rongtoken;
    public String access_token;

    /**
     * 学生id
     */
    public int sid;
    /**
     * 积分，暂时没用
     */
    public int integralcount;

    /**
     * 班级id
     */
    public int cid;
    /**
     * 校园id
     */
    public int kid;

    public KindergartenBean kindergarten;

    /**
     * 幼儿园信息
     *
     * @author Administrator
     */
    public class KindergartenBean {

        /**
         * 校园id
         */
        public int kid;

        /**
         * 幼儿园名字
         */
        public String name;

        /**
         * 幼儿园图片
         */
        public String photourl;
        /**
         * 幼儿园地址
         */
        public String address;
        /**
         * 幼儿园电话
         */
        public String telphone;
        /**
         * 幼儿园详情
         */
        public String content;
        /**
         * 幼儿园总共的学生数
         */
        public int student_count;
        /**
         * 暂时没用
         */
        public int master_uid;

        /**
         * 视频URL
         */
        public String video_domainname;

        /**
         * 视频端口
         */
        public int video_port;
        /**
         * 观看视频登录名
         */
        public String video_username;
        /**
         * 观看视频登录密码
         */
        public String video_pwd;
        /**
         * 某周某天是否可以观看视频，解析为二进制
         */
        public int openweek;
        /**
         * 某天某小时是否可以观看视频，解析为二进制
         */
        public long openday;

    }

    /**
     *
     */
    public String hwurl;
    /**
     * 1是校长  0教师
     */
    public int ismaster;


    @Override
    public String toString() {
        return "UserBean{" +
                "uid=" + uid +
                ", mobile=" + mobile +
                ", name='" + name + '\'' +
                ", portrait='" + portrait + '\'' +
                ", user_type=" + user_type +
                ", rongtoken='" + rongtoken + '\'' +
                ", access_token='" + access_token + '\'' +
                ", sid=" + sid +
                ", integralcount=" + integralcount +
                ", cid=" + cid +
                ", kid=" + kid +
                ", kindergarten=" + kindergarten +
                ", hwurl='" + hwurl + '\'' +
                ", ismaster=" + ismaster +
                '}';
    }
}
