package Helpers;

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

    public static BigDecimal convert(CurrencyCode fromCurrency, CurrencyCode toCurrency, BigDecimal money) {
        if (fromCurrency == CurrencyCode.RUB && toCurrency == CurrencyCode.USD) {
            return RUBtoUSD(money);
        }
        if (fromCurrency == CurrencyCode.RUB && toCurrency == CurrencyCode.EUR) {
            return RUBtoEUR(money);
        }
        if (fromCurrency == CurrencyCode.EUR && toCurrency == CurrencyCode.RUB) {
            return EURtoRUB(money);
        }
        if (fromCurrency == CurrencyCode.USD && toCurrency == CurrencyCode.RUB) {
            return USDtoRUB(money);
        }
        if (fromCurrency == CurrencyCode.USD && toCurrency == CurrencyCode.EUR) {
            return USDtoEUR(money);
        }
        if (fromCurrency == CurrencyCode.EUR && toCurrency == CurrencyCode.USD) {
            return EURtoUSD(money);
        }
        return null;
    }
}
