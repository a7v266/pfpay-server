package ru.pfpay.utils;

import ru.pfpay.config.Format;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class DecimalUtils {


    public static final MathContext MATH_CONTEXT = MathContext.DECIMAL64;


    public static BigDecimal toBigDecimal(Object object) {

        return valueOf(object, BigDecimal.ZERO);
    }

    public static BigDecimal valueOf(Object object) {

        return valueOf(object, null);
    }

    public static BigDecimal valueOf(Object value, BigDecimal defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }

        try {
            return new BigDecimal(value.toString());

        } catch (Exception exception) {

            return defaultValue;
        }
    }

    public static BigDecimal roundMoney(BigDecimal value) {
        return round(value, 2);
    }

    public static BigDecimal round(BigDecimal value) {
        return round(value, 8);
    }

    public static BigDecimal round(BigDecimal value, int fraction) {
        return round(value, fraction, RoundingMode.HALF_UP);
    }

    public static BigDecimal round(BigDecimal value, int fraction, RoundingMode roundingMode) {
        if (value == null) {
            return null;
        }
        if (roundingMode == null) {
            roundingMode = RoundingMode.HALF_UP;
        }
        return value.setScale(fraction, roundingMode);

    }

    public static BigDecimal add(BigDecimal... values) {
        BigDecimal result = BigDecimal.ZERO;
        for (BigDecimal value : values) {
            result = add(result, value);
        }
        return result;
    }

    public static BigDecimal add(BigDecimal value1, BigDecimal value2) {
        if (value1 == null && value2 == null) {
            return BigDecimal.ZERO;
        }
        if (value1 == null) {
            return value2;
        }
        if (value2 == null) {
            return value1;
        }
        return value1.add(value2, MATH_CONTEXT);
    }

    public static BigDecimal divide(BigDecimal value1, BigDecimal value2) {
        if (value1 == null) {
            return BigDecimal.ZERO;
        }
        if (value1.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (value2 == null) {
            return value1;
        }
        if (value2.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return value1.divide(value2, MATH_CONTEXT);
    }

    public static BigDecimal subtract(BigDecimal value1, BigDecimal value2) {

        if (value1 == null) {
            return BigDecimal.ZERO;
        }

        if (value2 == null) {
            return value1;
        }

        return value1.subtract(value2);
    }

    public static BigDecimal multiply(BigDecimal value1, BigDecimal value2) {

        if (value1 == null) {
            return BigDecimal.ZERO;
        }

        if (value2 == null) {
            return BigDecimal.ZERO;
        }

        return value1.multiply(value2, MATH_CONTEXT);
    }

    public static BigDecimal scale(BigDecimal value, Integer totalSize, Integer scaleSize) {
        if (totalSize == null) {
            return value;
        }
        if (scaleSize == null) {
            return value;
        }
        if (totalSize.equals(scaleSize)) {
            return value;
        }
        return divide(multiply(value, toBigDecimal(scaleSize)), toBigDecimal(totalSize));
    }

    public static BigDecimal scale(BigDecimal value, BigDecimal base, BigDecimal factor) {
        return value.multiply(base.divide(factor));
    }

    public static boolean greaterZero(BigDecimal value) {
        if (value == null) {
            return false;
        }
        return BigDecimal.ZERO.compareTo(value) < 0;
    }

    public static boolean greaterOrEqualZero(BigDecimal value) {
        if (value == null) {
            return false;
        }
        return BigDecimal.ZERO.compareTo(value) <= 0;
    }

    public static boolean lessZero(BigDecimal value) {
        if (value == null) {
            return false;
        }
        return BigDecimal.ZERO.compareTo(value) > 0;
    }

    public static boolean lessOrEqualZero(BigDecimal value) {
        if (value == null) {
            return false;
        }
        return BigDecimal.ZERO.compareTo(value) >= 0;
    }

    public static BigDecimal max(BigDecimal first, BigDecimal second) {
        if (first != null && second != null) {
            return first.compareTo(second) < 0 ? second : first;
        }
        if (first == null) {
            return first;
        }
        return second;
    }

    public static boolean gt(BigDecimal first, BigDecimal last) {
        if (first == null || last == null) {
            return false;
        }
        return first.compareTo(last) > 0;
    }

    public static boolean ge(BigDecimal first, BigDecimal last) {
        if (first == null || last == null) {
            return false;
        }
        return first.compareTo(last) >= 0;
    }

    public static boolean lt(BigDecimal first, BigDecimal last) {
        if (first == null || last == null) {
            return false;
        }
        return first.compareTo(last) < 0;
    }

    public static boolean le(BigDecimal first, BigDecimal last) {
        if (first == null || last == null) {
            return false;
        }
        return first.compareTo(last) <= 0;
    }

    public static boolean notZero(BigDecimal value) {

        return BigDecimal.ZERO.compareTo(value) != 0;
    }

    public static boolean notNullOrZero(BigDecimal value) {

        return value != null && notZero(value);
    }


    public static BigDecimal calculateTax(BigDecimal price) {
        if (price == null) {
            return null;
        }
        return price.multiply(Format.TAX, MATH_CONTEXT);
    }

    public static BigDecimal calculatePriceWithTax(BigDecimal price) {
        if (price == null) {
            return null;
        }
        return price.multiply(BigDecimal.ONE.add(Format.TAX, MATH_CONTEXT), MATH_CONTEXT);
    }
}
