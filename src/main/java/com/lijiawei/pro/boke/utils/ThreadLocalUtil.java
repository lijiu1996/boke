package com.lijiawei.pro.boke.utils;

import com.lijiawei.pro.boke.bean.entity.User;

public class ThreadLocalUtil {

    private static ThreadLocal<User> tUser = new ThreadLocal<>();

    public static void saveUser(User user) {
        tUser.set(user);
    }

    public static User getUser() {
        return tUser.get();
    }

    public static void removeUser() {
        tUser.remove();
    }

}
