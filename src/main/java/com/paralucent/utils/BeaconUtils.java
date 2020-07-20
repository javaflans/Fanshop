package com.paralucent.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.hibernate.loader.custom.Return;

public class BeaconUtils {

    public static double calculateAccuracy(double rssi) {
	if (rssi == 0) {
	    return -1.0; // if we cannot determine accuracy, return -1.
	}
	double ratio = rssi * 1.0 / -56;
	if (ratio < 1.0) {
	    return Math.pow(ratio, 10);
	} else {
	    double accuracy = (0.89976) * Math.pow(ratio, 7.7095) + 0.111;
	    return accuracy;
	}
    }

    public static BigDecimal calculateAccuracy(BigDecimal rssi) {
	DecimalFormat df = new DecimalFormat("##.00");
	if (rssi.compareTo(BigDecimal.ZERO) == 0) {
	    return new BigDecimal("-1.0"); // if we cannot determine accuracy, return -1.
	}
	BigDecimal ratio = rssi.multiply(BigDecimal.ONE).divide(new BigDecimal("-56"), 5, BigDecimal.ROUND_HALF_UP);
	if (ratio.compareTo(BigDecimal.ONE) == -1) {
	    return new BigDecimal(Math.pow(ratio.doubleValue(), 10)).divide(BigDecimal.ONE, 3, BigDecimal.ROUND_HALF_UP);
	} else {
	    BigDecimal accuracy = new BigDecimal("0.89976").multiply(new BigDecimal(Math.pow(ratio.doubleValue(), 7.7095)))
		    .add(new BigDecimal("0.111"));
	    return accuracy.divide(BigDecimal.ONE, 3, BigDecimal.ROUND_HALF_UP);
	}
    }
}
