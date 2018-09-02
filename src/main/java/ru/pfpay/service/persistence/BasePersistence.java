package ru.pfpay.service.persistence;

import org.hibernate.FlushMode;
import org.hibernate.criterion.Projection;
import ru.pfpay.hibernate.Scroll;
import ru.pfpay.service.search.Search;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface BasePersistence<T, K extends Serializable> {

    T get(K id);

    T get(K id, Consumer<K> errorHandler);

    T load(K id);

    K save(T object);

    void update(T object);

    void saveOrUpdate(T object);

    T merge(T object);

    void delete(T object);

    List<T> list(Search search);

    List<Map<String, Object>> list(Projection projection, Search search);

    Long count();

    Long count(Search search);

    Scroll<T> scroll(Search search);

    Scroll<T> scroll(Search search, boolean readOnly);

    void evict(T object);

    T uniqueResult(Search search);

    T uniqueResult(Search search, FlushMode flushMode);

    List<K> getIds(Search search);

    boolean isExist(Search search);

    boolean isEmpty();

    Long getMaxId();

    <T> T getMaxValue(String property);
}
