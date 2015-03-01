package com.iwood.web.model;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * Created with IntelliJ IDEA.
 * User: yongleixiao
 * Date: 14-1-16
 * Time: 上午9:53
 * 保存登录用户的cookies
 */
public class AuthedUser {

    private static final long LIFE = 60 * 60 * 1000; // 60分钟过期
    private static final long REFRESH_THRESHOLD = 15 * 60 * 1000; // 15分钟通知刷新
    private long id;
    private String name;
    private long time;
    private int level;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
    }

    public static long getLife() {
        return LIFE;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * 返回是否需要刷新登陆信息
     * @return
     */
    public boolean refresh() {
        long cur = System.currentTimeMillis();
        if(cur - time > REFRESH_THRESHOLD) {
            time = cur;
            return true;
        } else {
            return false;
        }
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - time > LIFE;
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

    public static AuthedUser parse(String json) {
        try {
            AuthedUser user = JSON.parseObject(json, new TypeReference<AuthedUser>() {
            });
            if(user.isExpired()) {
                return null;
            } else {
                return user;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isAdmin() {
        return this.level >= UserType.ADMIN.value();
    }

    /* 登录用户类型 */
    public static enum UserType {
        COMMON(1), //普通用户
        ADMIN(2);  //管理员
        private int value = 0;

        private UserType(int value) {
            this.value = value;
        }

        public static UserType valueOf(int value) {
            switch (value) {
                case 1:
                    return COMMON;
                case 2:
                    return ADMIN;
                default:
                    return null;
            }
        }

        public int value() {
            return this.value;
        }
    }
}
