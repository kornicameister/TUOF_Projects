package org.kornicameister.iad.core.algorithm;

/**
 * Execution lock for threads
 *
 * @author kornicameister
 * @since 0.0.1
 */
public class IADExecutionLock {
    private Integer id;

    public IADExecutionLock() {
        this(0);
    }

    public IADExecutionLock(Integer id) {
        this.id = id;
    }

    public IADExecutionLock increment() {
        this.id++;
        return this;
    }

    public IADExecutionLock decrement() {
        --this.id;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IADExecutionLock{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
