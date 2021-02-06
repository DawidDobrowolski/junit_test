package pl.junit;

import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;

class OrderBackupTest {

    static private OrderBackup orderBackup;

    @BeforeAll
    static void initializeOrderBackup() throws FileNotFoundException {
        orderBackup = new OrderBackup();
        orderBackup.createFile();
    }

    @BeforeEach
    void addBackupPrefixText() throws IOException {
        orderBackup.getWriter().append("New order: ");
    }

    @AfterEach
    void addBackupSuffixText() throws IOException {
        orderBackup.getWriter().append(" backed up");
    }

    @AfterAll
    static void closeBackupFile() throws IOException {
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