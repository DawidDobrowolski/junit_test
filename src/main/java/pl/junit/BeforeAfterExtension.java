package pl.junit;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class BeforeAfterExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        System.out.println("Inside before each Extension ");
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        System.out.println("Inside after each Extension ");
    }
}
