package pl.javastart.couponcalc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceCalculatorTest {

    @Test
    public void shouldReturnZeroForNoProducts() {
        // given
        PriceCalculator priceCalculator = new PriceCalculator();

        // when
        double result = priceCalculator.calculatePrice(null, null);

        // then
        assertThat(result).isEqualTo(0.);
    }

    @Test
    public void shouldReturnPriceForSingleProductAndNoCoupons() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 5.99, Category.FOOD));

        // when
        double result = priceCalculator.calculatePrice(products, null);

        // then
        assertThat(result).isEqualTo(5.99);
    }

    @Test
    public void shouldReturnPriceForSingleProductAndOneCoupon() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 5.99, Category.FOOD));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(Category.FOOD, 20));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result).isEqualTo(4.79);
    }

    @Test
    public void shouldReturnBestPrice5_99ForMultipleProductsAndCoupons() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 5.99, Category.FOOD));
        products.add(new Product("Mleko", 4, Category.FOOD));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(Category.FOOD, 20));
        coupons.add(new Coupon(null, 40));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result).isEqualTo(5.99);
    }

    @Test
    public void shouldReturnBestPrice21259_99ForMultipleProductsAndCoupons() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 5.99, Category.FOOD));
        products.add(new Product("Mleko", 4, Category.FOOD));
        products.add(new Product("Honda", 25000, Category.CAR));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(Category.FOOD, 20));
        coupons.add(new Coupon(null, 10));
        coupons.add(new Coupon(Category.CAR, 15));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result).isEqualTo(21259.99);
    }

    @Test
    public void shouldReturnBestPriceForOneProductAndMultipleCoupons() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 5.99, Category.FOOD));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(Category.FOOD, 20));
        coupons.add(new Coupon(null, 45));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result).isEqualTo(3.29);
    }

    @Test
    public void shouldReturnPriceForMultipleProductsAndOneCoupon() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 5.99, Category.FOOD));
        products.add(new Product("Honda", 25000, Category.CAR));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(Category.FOOD, 20));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result).isEqualTo(25004.79);
    }
}