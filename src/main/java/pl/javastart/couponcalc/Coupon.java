package pl.javastart.couponcalc;

public class Coupon {

    private final Category category;
    private final int discountValueInPercents;

    public Coupon(Category category, int discountValueInPercents) {
        this.category = category;
        this.discountValueInPercents = discountValueInPercents;
    }

    double getDiscountPercentageInDecimalFraction(){
        return 1 - (((double) discountValueInPercents) / 100) ;
    }

    public Category getCategory() {
        return category;
    }

    public int getDiscountValueInPercents() {
        return discountValueInPercents;
    }
}