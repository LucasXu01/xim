package com.lucas.xim.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Description:
 * @Auther: LucasXu
 * @email: 18140041@bjtu.edu.cn
 * @github: https://github.com/LucasXu01
 * @Date: 2021/12/09/14:35 下午
 */
public class JsonMapper {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        // 不序列化null字段
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * Object转json
     *
     * @param obj
     * @return
     */
    public static String toJSONString(Object obj) {
        if (obj == null) return null;
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> T parseObject(String json, Class<T> cls) {
        if (StringUtil.isEmpty(json)) return null;
        try {
            return mapper.readValue(json, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> T parseObject(String json, JavaType javaType) {
        if (StringUtil.isEmpty(json)) return null;
        try {
            return mapper.readValue(json, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static JavaType getCollectionType(Class<?> collectionCls, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionCls, elementClasses);
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUserId("26");
        user.setUserName("Lucas");
        String json = JsonMapper.toJSONString(user);
        System.out.println("json = " + json);

        user = JsonMapper.parseObject(json, User.class);
        System.out.println("user = " + user);
    }

    static class User {
        private String userId;
        private String userName;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        @Override
        public String toString() {
            return "User{" +
                    "userId='" + userId + '\'' +
                    ", userName='" + userName + '\'' +
                    '}';
        }
    }
}
