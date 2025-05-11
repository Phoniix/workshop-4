package mainMenu;

import java.io.IOException;

public interface ThrowingSupplier<T> {
    T get() throws InterruptedException, NumberFormatException, IOException;
}
