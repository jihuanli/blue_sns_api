/**
 * $Id$
 * Copyright @2012  Qunar.com Inc. All rights reserved.
 */
package com.dabanniu.core.utils;

import java.util.Random;

public final class UserAgentUtils {

    private final static String[] mozillas = new String[] { "4", "5", "6", "7" };

    private final static String[] nts = new String[] { "5.0", "5.1", "5.2", "6.0", "6.1" };

    private final static String[] ies = new String[] { "6", "7", "8", "9" };

    private final static String[] firefoxs = new String[] { "3", "4", "5", "6", "7", "8", "9", "10", "11" };

    private final static String[] chromes = new String[] { "18.0.1025.151", "10.0.648.204", "11.0.696.16",
            "12.0.768.332", "13.0.738.392", "14.0.880.32", "15.0.211.44", "16.0.810.327", "17.0.669.24" };

    private final static Random random = new Random();

    public final static String getRandomUserAgent() {
        int index = random.nextInt(3);
        if (index == 0) {
            return getIEUserAgent();
        } else if (index == 2) {
            return getChromeUserAgent();
        } else {
            return getFirefoxUserAgent();
        }
    }

    private final static String getIEUserAgent() {
        return String
                .format("Mozilla/%s.0 (compatible; MSIE %s.0; Windows NT %s; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; InfoPath.2)",
                        mozillas[random.nextInt(mozillas.length)], ies[random.nextInt(ies.length)],
                        nts[random.nextInt(nts.length)]);
    }

    private final static String getChromeUserAgent() {
        return String.format(
                "Mozilla/%s.0 (Windows NT %s) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/%s Safari/535.19",
                mozillas[random.nextInt(mozillas.length)], nts[random.nextInt(nts.length)],
                chromes[random.nextInt(chromes.length)]);
    }

    private final static String getFirefoxUserAgent() {
        return String.format("Mozilla/%s.0 (Windows NT %s; rv:11.0) Gecko/20100101 Firefox/%s.0",
                mozillas[random.nextInt(mozillas.length)], nts[random.nextInt(nts.length)],
                firefoxs[random.nextInt(firefoxs.length)]);
    }
}
