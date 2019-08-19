package cn.itcast.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {
    public static String BCryptPasswordEncoder(String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(password);
        return encode;
    }

    public static void main(String[] args) {
        String s = BCryptPasswordEncoder("123");//$2a$10$zURUJag1RiL5wnzNmIo4KOJAVn/EUQw31q/cO5V7wg22N2tmaPduW
        System.out.println(s);
    }
}
