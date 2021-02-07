package pl.junit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.FileNotFoundException;
import java.io.IOException;

@ExtendWith(BeforeAfterExtension.class)
class OrderBackupTest {

    static private OrderBackup orderBackup;

    @BeforeAll
    static void initializeOrderBackup() throws FileNotFoundException {
        System.out.println("Before all");
        orderBackup = new OrderBackup();
        orderBackup.createFile();
    }

    @BeforeEach
    void addBackupPrefixText() throws IOException {
        System.out.println("Before each");
        orderBackup.getWriter().append("New order: ");
    }

    @AfterEach
    void addBackupSuffixText() throws IOException {
        System.out.println("After each");
        orderBackup.getWriter().append(" backed up");
    }

    @AfterAll
    static void closeBackupFile() throws IOException {
        System.out.println("After all");
        orderBackup.closeFIle();
    }

    @Test
    void backupOrderWIthOneMeal() throws IOException {
        //given
        Meal meal = new Meal(15,"kurczak chrupiący");
        Order order = new Order();
        order.addMealToOrder(meal);

        //when
        orderBackup.backupOrder(order);

        //then
        System.out.println("Order: " + order.toString() + " backed up");
    }

}