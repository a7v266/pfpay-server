package ru.pfpay.domain;

import ru.pfpay.utils.StringUtils;

import java.io.Serializable;

public abstract class BaseEntity implements Identifiable, Serializable, Comparable<BaseEntity> {

    public static final String ID = "id";

    @Override
    public String toString() {
        return StringUtils.toString(getId());
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (getId() == null) {
            return false;
        }
        if (object instanceof BaseEntity) {
            BaseEntity entity = (BaseEntity) object;
            if (entity.getId() == null) {
                return false;
            }
            return entity.getId().equals(getId());
        }
        return false;
    }

    @Override
    public int compareTo(BaseEntity baseEntity) {
        if (getId() == null) {
            return -1;
        }
        if (baseEntity.getId() == null) {
            return 1;
        }
        return getId().compareTo(baseEntity.getId());
    }
}
