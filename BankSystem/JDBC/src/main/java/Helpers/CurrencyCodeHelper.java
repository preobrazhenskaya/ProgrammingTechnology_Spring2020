package Helpers;

public class CurrencyCodeHelper {
    public static CurrencyCode convert(String currencyCode) {
        switch(currencyCode) {
            case "RUB":
                return CurrencyCode.RUB;
            case "EUR":
                return CurrencyCode.EUR;
            case "USD":
                return CurrencyCode.USD;
        }
        return null;
    }
}
