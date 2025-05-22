package main;

import java.util.List;


public class SILab2 {
    public static double checkCart(List<Item> allItems, String cardNumber){
        if (allItems == null){
            throw new RuntimeException("allItems list can't be null!");
        }

        double sum = 0;

        for (int i = 0; i < allItems.size(); i++){
            Item item = allItems.get(i);
            if (item.getName() == null || item.getName().length() == 0){
                throw new RuntimeException("Invalid item!");
            }

            if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10){
                sum -= 30;
            }

            if (item.getDiscount() > 0){
                sum += item.getPrice()*(1-item.getDiscount())*item.getQuantity();
            }
            else {
                sum += item.getPrice()*item.getQuantity();
            }

        }
        if (cardNumber != null && cardNumber.length() == 16) {
            String allowed = "0123456789";
            char[] chars = cardNumber.toCharArray();
            for (int j = 0; j < cardNumber.length(); j++) {
                char c = cardNumber.charAt(j);
                if (allowed.indexOf(c) == -1) {
                    throw new RuntimeException("Invalid character in card number!");
                }
            }
        }
        else{
            throw new RuntimeException("Invalid card number!");
        }

        return sum;

    }
}
