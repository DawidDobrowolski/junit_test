package pl.junit.order;

import org.junit.jupiter.api.Test;
import pl.junit.order.Order;
import pl.junit.order.OrderBackup;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderBackupExecutionBackupTest {

    @Test
    void callingBackupWithoutCreatingFileShouldThrowException(){
        //given
        Order order = new Order();
        OrderBackup orderBackup = new OrderBackup();

        //then
        assertThrows(IOException.class, () -> orderBackup.backupOrder(order));
    }
}
