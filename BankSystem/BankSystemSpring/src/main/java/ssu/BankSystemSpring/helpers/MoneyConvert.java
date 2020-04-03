package ssu.BankSystemSpring.helpers;

import java.math.BigDecimal;

public class MoneyConvert {
    private static BigDecimal RUBtoUSD(BigDecimal money) {
        return money.multiply(BigDecimal.valueOf(0.013));
    }

    private static BigDecimal USDtoRUB(BigDecimal money) {
        return money.multiply(BigDecimal.valueOf(77.21));
    }

    private static BigDecimal RUBtoEUR(BigDecimal money) {
        return money.multiply(BigDecimal.valueOf(0.012));
    }

    private static BigDecimal EURtoRUB(BigDecimal money) {
        return money.multiply(BigDecimal.valueOf(83.83));
    }

    private static BigDecimal USDtoEUR(BigDecimal money) {
        return money.multiply(BigDecimal.valueOf(0.92));
    }

    private static BigDecimal EURtoUSD(BigDecimal money) {
        return money.multiply(BigDecimal.valueOf(1.09));
    }

    public static BigDecimal convert(String fromCurrency, String toCurrency, BigDecimal money) {
        if (fromCurrency.equals("RUB") && toCurrency.equals("USD")) {
            return RUBtoUSD(money);
        }
        if (fromCurrency.equals("RUB") && toCurrency.equals("EUR")) {
            return RUBtoEUR(money);
        }
        if (fromCurrency.equals("EUR") && toCurrency.equals("RUB")) {
            return EURtoRUB(money);
        }
        if (fromCurrency.equals("USD") && toCurrency.equals("RUB")) {
            return USDtoRUB(money);
        }
        if (fromCurrency.equals("USD") && toCurrency.equals("EUR")) {
            return USDtoEUR(money);
        }
        if (fromCurrency.equals("EUR") && toCurrency.equals("USD")) {
            return EURtoUSD(money);
        }
        return null;
    }
}
