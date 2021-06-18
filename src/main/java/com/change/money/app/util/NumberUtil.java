package com.change.money.app.util;


import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class NumberUtil {

    public static final int TRUNCATE_DECIMAL_ZERO = 0;
    public static final int TRUNCATE_DECIMAL_TWO = 2;

    private static final Pattern PATTERN_DECIMAL_NO_NEGATIVE = Pattern.compile("^(\\d*\\.)?\\d+$");
    private static final Pattern PATTERN_ZERO = Pattern.compile("^(0)");
    private static final String ZERO = "0";
    private static final String POINT_DECIMAL = ".";

    private NumberUtil() {
    }


    private static boolean isPositiveNumber(String strNumber) {
        return strNumber != null && PATTERN_DECIMAL_NO_NEGATIVE.matcher(strNumber).matches() && !PATTERN_ZERO.matcher(strNumber).matches();
    }

    private static String truncateDecimal(String strNumber, int scale) {

        if (!NumberUtils.isParsable(strNumber)) {
            return null;
        }

        int positionDecimal = strNumber.indexOf(POINT_DECIMAL);

        if (positionDecimal == -1) {
            if (scale <= 0) {
                return strNumber;
            }
            return strNumber + POINT_DECIMAL + String.format("%" + ZERO + scale + "d", 0);
        }

        int fillZero = positionDecimal + scale + 1 - strNumber.length();

        if (fillZero > 0) {
            return strNumber + String.format("%" + ZERO + fillZero + "d", 0);
        }

        if (scale <= 0) {
            return strNumber.substring(0, positionDecimal);
        }

        return strNumber.substring(0, positionDecimal + scale + 1);
    }


    public static String multiply(String strNumber1, String strNumber2, int truncateDecimals) {
        if (!isPositiveNumber(strNumber1) || !isPositiveNumber(strNumber2)) {
            return null;
        }

        BigDecimal result = new BigDecimal(strNumber1).multiply(new BigDecimal(strNumber2));
        return truncateDecimal(result.toString(), truncateDecimals);
    }

    public static Double parseDouble(String strNumber) {
        if (strNumber != null) {
            return Double.valueOf(strNumber);
        }
        return null;
    }

    public static String divide(String strNumber1, String strNumber2, int truncateDecimals) {
        if (!isPositiveNumber(strNumber1) || !isPositiveNumber(strNumber2)) {
            return null;
        }

        BigDecimal result = new BigDecimal(strNumber1).divide(new BigDecimal(strNumber2));
        return truncateDecimal(result.toString(), truncateDecimals);
    }
}
