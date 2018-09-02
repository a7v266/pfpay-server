package ru.pfpay.service.search;


import ru.pfpay.config.Format;
import ru.pfpay.domain.BaseEntity;
import ru.pfpay.utils.CollectionUtils;
import ru.pfpay.utils.MapUtils;
import ru.pfpay.utils.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Search implements Serializable {

    public static final String SORT_ID = BaseEntity.ID;

    private boolean nothing = false;

    private int offset = 0;

    private int limit = 10;

    private Map<String, String> aliases = MapUtils.createHashMap();

    private List<Filter> filters = CollectionUtils.createArrayList();

    private List<Sort> sorts = CollectionUtils.createArrayList();

    private List<String> lazyList = CollectionUtils.createArrayList();

    public void setNothing(boolean nothing) {
        this.nothing = nothing;
    }

    public boolean isNothing() {
        return nothing;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        if (limit != null) {
            this.limit = limit;
        }
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        if (offset != null) {
            this.offset = offset;
        }
    }

    public Search addFilter(Filter filter) {
        if (filter != null) {
            aliasingFilter(filter);
            filters.add(filter);
            nothing = false;
        }
        return this;
    }

    public Map<String, String> getAliases() {
        return aliases;
    }

    public boolean hasFilters() {
        return filters.size() > 0;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void addLazyPath(String lazyPath) {
        lazyList.add(lazyPath);
    }

    public List<String> getLazyList() {
        return lazyList;
    }

    public List<Sort> getSorts() {
        return sorts;
    }

    public final void setSort(String sort) {
        setSort(sort, Boolean.FALSE);
    }

    public final void setSort(String sort, Boolean desc) {
        if (isValidSort(sort)) {
            resetSort();
            if (addCustomSort(sort, desc)) {
                return;
            }
            addSort(Sort.create(sort, desc));
        }
    }

    public final void addSort(String sort) {
        addSort(sort, Boolean.FALSE);
    }

    public final void addSort(String sort, Boolean desc) {
        if (isValidSort(sort)) {
            if (addCustomSort(sort, desc)) {
                return;
            }
            addSort(Sort.create(sort, desc));
        }
    }

    protected boolean isValidSort(String sort) {
        return StringUtils.isNotEmpty(sort);
    }

    protected boolean addCustomSort(String sort, Boolean desc) {
        return false;
    }

    protected void resetSort() {
        sorts = new ArrayList<>();
    }

    protected final void setSort(Sort... sorts) {
        resetSort();
        for (Sort sort : sorts) {
            addSort(sort);
        }
    }

    protected final void addSort(Sort sort) {
        String property = sort.getProperty();
        if (property.contains(Format.DOT)) {
            sort.setProperty(aliasingProperty(property));
        }
        sorts.add(sort);
    }

    private void aliasingFilter(Filter filter) {
        if (filter.getFilters() != null && filter.getFilters().size() > 0) {
            filter.getFilters().forEach(this::aliasingFilter);
        }
        String property = filter.getProperty();
        if (property != null && property.contains(Format.DOT)) {
            filter.setProperty(aliasingProperty(property));
        }
    }

    private String aliasingProperty(String property) {
        int index = property.lastIndexOf(Format.DOT);
        if (index == -1) {
            return property;
        }
        return createAlias(property.substring(0, index)).concat(property.substring(index));
    }

    private String createAlias(String path) {
        int index = path.lastIndexOf(Format.DOT);
        if (index == -1) {
            aliases.put(path, path);
            return path;
        }
        String aliasName = path.replaceAll(Format.REGEX_DOT, Format.UNDERSCORE);
        String aliasPath = createAlias(path.substring(0, index)).concat(path.substring(index));
        aliases.put(aliasName, aliasPath);
        return aliasName;
    }
}
