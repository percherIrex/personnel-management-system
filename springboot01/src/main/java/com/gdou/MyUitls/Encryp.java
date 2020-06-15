package com.gdou.MyUitls;

public class Encryp {
    public static String encryption(String phone, String password) {
        char[] p = phone.toCharArray();
        char[] pwd = password.toCharArray();

        int key_size = phone.length();
        int pwd_size = password.length();

        int max = Math.max(key_size, pwd_size);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < max; i++) {
            int a = p[i % key_size] & pwd[i % pwd_size]; //两位同时为1才是1
            result.append(a);
        }
        return result.toString();
    }
}
