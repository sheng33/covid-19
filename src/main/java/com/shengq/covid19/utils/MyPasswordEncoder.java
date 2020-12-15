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
//        String encodeStr= DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
        String encodeStr= charSequence+"---";
        System.out.println("endcode:"+encodeStr);
//        System.out.println("MD5加密后的字符串为:encodeStr="+encodeStr);
        return encodeStr;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return encode(charSequence).equals(s);
    }
}
