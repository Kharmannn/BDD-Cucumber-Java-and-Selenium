package stepsDefinition;

public class Test {
    public static void main(String[] args) {
        String input = "Rp 7.200.000,00";
        String checkPriceRaw = input.replaceAll("[^0-9]", "");
        String finalCheckoutPrice = checkPriceRaw.substring(0, checkPriceRaw.length() - 2);

        System.out.println(finalCheckoutPrice);
    }
}
