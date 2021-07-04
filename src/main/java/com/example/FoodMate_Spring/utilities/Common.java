package com.example.FoodMate_Spring.utilities;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * -------------------------------------------------------------------------- <br>
 *  Các hàm dùng chung toàn hệ thống <br>
 * -------------------------------------------------------------------------- <br><br>
 *
 *
 */
public class Common {
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String toSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }
}
