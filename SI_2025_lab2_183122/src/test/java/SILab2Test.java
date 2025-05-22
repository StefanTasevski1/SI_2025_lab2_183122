package test.java;
import main.Item;
import main.SILab2;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class SILab2Test {


    @Test(expected = RuntimeException.class)
    public void testNullAllItems() {
        SILab2.checkCart(null, "1234567890123456");
    }


    @Test(expected = RuntimeException.class)
    public void testItemWithNullName() {
        List<Item> items = Arrays.asList(new Item(null, 1, 100, 0));
        SILab2.checkCart(items, "1234567890123456");
    }


    @Test(expected = RuntimeException.class)
    public void testItemWithEmptyName() {
        List<Item> items = Arrays.asList(new Item("", 1, 100, 0));
        SILab2.checkCart(items, "1234567890123456");
    }


    @Test(expected = RuntimeException.class)
    public void testInvalidCardNumberLength() {
        List<Item> items = Arrays.asList(new Item("Apple", 1, 100, 0));
        SILab2.checkCart(items, "123456789012345"); // only 15 digits
    }


    @Test(expected = RuntimeException.class)
    public void testInvalidCardNumberCharacter() {
        List<Item> items = Arrays.asList(new Item("Apple", 1, 100, 0));
        SILab2.checkCart(items, "12345678901234AB"); // contains 'A' and 'B'
    }


    @Test
    public void testItemPriceGreaterThan300() {
        List<Item> items = Arrays.asList(new Item("Expensive", 1, 350, 0));
        // sum = -30 + 350 = 320
        assertEquals(320, SILab2.checkCart(items, "1234567890123456"), 0.01);
    }


    @Test
    public void testItemWithDiscount() {
        List<Item> items = Arrays.asList(new Item("Discounted", 2, 100, 0.1));
        // sum = -30 + 2*100*0.9 = -30 + 180 = 150
        assertEquals(150, SILab2.checkCart(items, "1234567890123456"), 0.01);
    }


    @Test
    public void testItemWithLargeQuantity() {
        List<Item> items = Arrays.asList(new Item("Bulk", 11, 10, 0));
        // sum = -30 + 11*10 = -30 + 110 = 80
        assertEquals(80, SILab2.checkCart(items, "1234567890123456"), 0.01);
    }


    @Test
    public void testNormalItem() {
        List<Item> items = Arrays.asList(new Item("Normal", 2, 100, 0));
        // sum = 2*100 = 200
        assertEquals(200, SILab2.checkCart(items, "1234567890123456"), 0.01);
    }

    @Test
    public void testMultipleCondition() {
        // 1. A=F, B=F, C=F (не влегува во if)
        Item i1 = new Item("A", 1, 100, 0); // price=100, discount=0, quantity=1

        // 2. A=F, B=F, C=T
        Item i2 = new Item("B", 11, 100, 0); // price=100, discount=0, quantity=11

        // 3. A=F, B=T, C=F
        Item i3 = new Item("C", 1, 100, 0.1); // price=100, discount=0.1, quantity=1

        // 4. A=F, B=T, C=T
        Item i4 = new Item("D", 11, 100, 0.1); // price=100, discount=0.1, quantity=11

        // 5. A=T, B=F, C=F
        Item i5 = new Item("E", 1, 350, 0); // price=350, discount=0, quantity=1

        // 6. A=T, B=F, C=T
        Item i6 = new Item("F", 11, 350, 0); // price=350, discount=0, quantity=11

        // 7. A=T, B=T, C=F
        Item i7 = new Item("G", 1, 350, 0.1); // price=350, discount=0.1, quantity=1

        // 8. A=T, B=T, C=T
        Item i8 = new Item("H", 11, 350, 0.1); // price=350, discount=0.1, quantity=11

        String card = "1234567890123456";
        // Секој тест може да се повика вака:
        SILab2.checkCart(Arrays.asList(i1), card);
        SILab2.checkCart(Arrays.asList(i2), card);
        SILab2.checkCart(Arrays.asList(i3), card);
        SILab2.checkCart(Arrays.asList(i4), card);
        SILab2.checkCart(Arrays.asList(i5), card);
        SILab2.checkCart(Arrays.asList(i6), card);
        SILab2.checkCart(Arrays.asList(i7), card);
        SILab2.checkCart(Arrays.asList(i8), card);
    }

}
