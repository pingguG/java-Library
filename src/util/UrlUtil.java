package util;

import java.net.URLDecoder;
import static java.nio.charset.StandardCharsets.UTF_8;

public class UrlUtil {

    public static String decode(String s) {
        if (s == null) return "";
        return URLDecoder.decode(s, UTF_8);
    }
}
