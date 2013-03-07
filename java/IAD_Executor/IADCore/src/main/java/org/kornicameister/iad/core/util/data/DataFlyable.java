package org.kornicameister.iad.core.util.data;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Interface for strategy pattern.
 * All IO based classes should implement it
 * as it defines consistent API for reading
 * and writing IAD data
 *
 * @author kornicameister
 * @since 0.0.1
 */
public interface DataFlyable<T extends IAData> {
    List<T> read(Class<T> beanClass) throws FileNotFoundException;

    void write();
}
