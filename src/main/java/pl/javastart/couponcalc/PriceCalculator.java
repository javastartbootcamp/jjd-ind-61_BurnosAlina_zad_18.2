package pl.javastart.couponcalc;

import java.util.List;

public class PriceCalculator {

    public double calculatePrice(List<Product> products, List<Coupon> coupons) {
        double sum = 0;
        if (products == null || products.isEmpty()) {
            return sum;
        }
        if (coupons == null || coupons.isEmpty()) {
            return sumPrices(products, sum);
        }
        return calculateCheapestPrice(products, coupons);
    }

    private double calculateCheapestPrice(List<Product> products, List<Coupon> coupons) {
        double bestPrice = 1000000000;
        for (Coupon coupon : coupons) {
            double sum = 0;
            if (coupon.getCategory() == null) {
                sum = calculatePriceNoCategory(products, coupon);
            } else {
                sum = calculatePriceWithCategory(products, coupon);
            }
            if (sum < bestPrice) {
                bestPrice = sum;
            }
        }
        return bestPrice;
    }

    private double calculatePriceWithCategory(List<Product> products, Coupon coupon) {
        double sum = 0;
        double discount = coupon.getDiscountPercentageInDecimalFraction();
        for (Product product : products) {
            if (coupon.getCategory() == product.getCategory()) {
                sum += Math.round(discount * product.getPrice() * 100.0) / 100.0;
            } else {
                sum += product.getPrice();
            }
        }
        return sum;
    }

    private double calculatePriceNoCategory(List<Product> products, Coupon coupon) {
        double sum = 0;
        for (Product product : products) {
            sum += Math.round(product.getPrice() * coupon.getDiscountPercentageInDecimalFraction() * 100.0) / 100.0;
        }
        return sum;
    }

    private double sumPrices(List<Product> products, double sum) {
        for (Product product : products) {
            sum += product.getPrice();
        }
        return sum;
    }
}
