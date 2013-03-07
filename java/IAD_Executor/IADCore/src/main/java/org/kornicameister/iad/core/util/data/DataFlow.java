package org.kornicameister.iad.core.util.data;

/**
 * @author kornicameister
 * @since 0.0.1
 */
public abstract class DataFlow<T extends IAData> implements DataFlyable<T> {
    protected final String filePath;

    protected DataFlow(String filePath) {
        this.filePath = filePath;
    }
}
