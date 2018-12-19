package com.ljb.utils;


/**
 * 常量
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年11月15日 下午1:23:52
 */
public class Constant {
    /**
     * 超级管理员ID
     */
    public static final String SUPER_ADMIN ="超级管理员";

    public static final String MEMBER_ROLE="注册会员";

    public static final Integer EXCELSIZE=1000000000;

    public static final String DAO_CACHE_NAME="DAO";

    public static final String SECURITY_CACHE_NAME="SECURITY";

    public static final String SERVICE_CACHE_NAME="SERVICE";

    public static final String LOGIN_URL="/account/login";

    public static final String LOGOUT_URL="/account/logout";

    public static final String SESSION_PARAMETER="sid";

    public static final String REMEMBER_ME_NAME="remember";

    public static final String COOKIE_NAME="platform";

    //总部
    public static final Integer HEADQUATERS=1;

    public static final String SESSIONNAME="user";

    public static final String[] NOAUTHORIZATIONURL={"","account"};

    /**
     * ORACLE、MYSQL
     */
    public static final String USE_DATA = "MYSQL";
    /**
     * 分页条数
     */
    public static final int pageSize=10;
    /**
     * 权限前缀
     */
    public static final String PERMS_LIST = "permsList";

    /**
     * 菜单类型
     *
     * @author lipengjun
     * @email 939961241@qq.com
     * @date 2016年11月15日 下午1:24:29
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     *
     * @author lipengjun
     * @email 939961241@qq.com
     * @date 2016年12月3日 上午12:07:22
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        private ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        private CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 是否类型
     */
    public enum YESNO{
        /**
         * 是
         */
        YES("0"),
        /**
         * 否
         */
        NO("1");
        private String value;

        private YESNO(String value){
            this.value=value;
        }
        public String getValue(){
            return value;
        }
    }
}
