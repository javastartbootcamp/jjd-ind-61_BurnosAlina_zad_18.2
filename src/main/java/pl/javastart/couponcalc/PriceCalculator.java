package pl.javastart.couponcalc;

import java.util.ArrayList;
import java.util.List;

public class PriceCalculator {

    private static final int ONLY_ONE_COUPON = 1;

    public double calculatePrice(List<Product> products, List<Coupon> coupons) {
        double sum = 0;
        if (products == null) {
            return sum;
        }
        if (coupons == null) {
            return sumPrices(products, sum);
        }
        if (coupons.size() == ONLY_ONE_COUPON && coupons.get(0).getCategory() == null) {
            return calculatePriceForCouponWithNoCategory(products, coupons, sum);
        }
        if (coupons.size() == ONLY_ONE_COUPON && coupons.get(0).getCategory() != null) {
            return calculatePriceForCouponWithCategory(products, sum, coupons.get(0).getCategory(),
                    coupons.get(0).getDiscountPercentageInDecimalFraction());
        }
        if (coupons.size() > ONLY_ONE_COUPON) {
            return calculateBestPriceForMultipleCoupons(products, coupons, sum);
        }
        return sum;
    }

    private double calculateBestPriceForMultipleCoupons(List<Product> products, List<Coupon> coupons, double sum) {
        List<Double> prices = new ArrayList<>();
        prices.add(calculatePriceWithProvidedCoupon(products, coupons, sum, null));
        prices.add(calculatePriceWithProvidedCoupon(products, coupons, sum, Category.FOOD));
        prices.add(calculatePriceWithProvidedCoupon(products, coupons, sum, Category.HOME));
        prices.add(calculatePriceWithProvidedCoupon(products, coupons, sum, Category.CAR));
        prices.add(calculatePriceWithProvidedCoupon(products, coupons,
                sum, Category.ENTERTAINMENT));
        double theSmallestPrice = 100000;
        for (Double price : prices) {
            if (price != 0 && price < theSmallestPrice) {
                theSmallestPrice = price;
            }
        }
        return theSmallestPrice;
    }

    private Coupon findCouponIfIsProvided(List<Coupon> coupons, Category category) {
        for (Coupon coupon : coupons) {
            if (category == null){
                if (coupon.getCategory() == null) {
                    return coupon;
                }
            } else {
                if (coupon.getCategory() == category) {
                    return coupon;
                }
            }
        }
        return null;
    }

    private double calculatePriceWithProvidedCoupon(List<Product> products, List<Coupon> coupons,
                                                    double sum, Category category) {
        Coupon providedCoupon = findCouponIfIsProvided(coupons, category);
        if (providedCoupon != null) {
            double discount = providedCoupon.getDiscountPercentageInDecimalFraction();
            if (providedCoupon.getCategory() == null) {
                sum = sumPrices(products, sum);
                return Math.round(discount * sum * 100.0) / 100.0;
            } else {
                return calculatePriceForCouponWithCategory(products, sum, category, discount);
            }
        }
        return 0;
    }

    private double calculatePriceForCouponWithCategory(List<Product> products, double sum, Category category, double discount) {
        for (Product product : products) {
            if (product.getCategory() == category) {
                sum += Math.round(discount * product.getPrice() * 100.0) / 100.0;
            } else {
                sum += product.getPrice();
            }
        }
        return sum;
    }
    private double calculatePriceForCouponWithNoCategory(List<Product> products, List<Coupon> coupons,
                                                         double sum) {
        double discount = coupons.get(0).getDiscountPercentageInDecimalFraction();
        sum = sumPrices(products, sum);
        sum = Math.round(discount / 100 * sum * 100.0) / 100.0;
        return sum;
    }

    private double sumPrices(List<Product> products, double sum) {
        for (Product product : products) {
            sum += product.getPrice();
        }
        return sum;
    }
}
