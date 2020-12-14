package net.toujoustudios.hichatplus.string;

/**
 * This file was created by IanToujou.
 * Date: 14.12.2020
 * Time: 12:53
 * Project: HiChatPlus
 */
public class StringTools {

    public static String dropLastChar(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == 'x') {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

}
