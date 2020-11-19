package com.shengq.covid19.utils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import sun.security.provider.MD5;

/**
 * @author shengQ
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        String encodeStr= DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
//        System.out.println("MD5加密后的字符串为:encodeStr="+encodeStr);
        return encodeStr;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        System.out.println("1");
        return encode(charSequence).equals(encode(s));
    }
}
