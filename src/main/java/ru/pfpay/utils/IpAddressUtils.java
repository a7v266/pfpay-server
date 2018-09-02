package ru.pfpay.utils;

public class IpAddressUtils {

    private static final String IP_ADDRESS_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";


    public static Long parse(String ipAddress) {
        if (ipAddress == null || ipAddress.isEmpty()) {
            return null;
        }
        if (!ipAddress.matches(IP_ADDRESS_PATTERN)) {
            return null;
        }
        String[] ipAddressInArray = ipAddress.split("\\.");
        long result = 0;
        for (int i = 0; i < ipAddressInArray.length; i++) {
            int power = 3 - i;
            int ip = Integer.parseInt(ipAddressInArray[i]);
            result += ip * Math.pow(256, power);
        }

        return result;
    }

    public static String format(Long ip) {
        if (ip == null) {
            return null;
        }
        StringBuilder result = new StringBuilder(15);
        for (int i = 0; i < 4; i++) {
            result.insert(0, Long.toString(ip & 0xff));
            if (i < 3) {
                result.insert(0, '.');
            }
            ip = ip >> 8;
        }
        return result.toString();
    }
}
