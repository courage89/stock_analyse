package com.qingqing.stock_analyse.dao.common;

import com.qingqing.common.intf.HasValueInterface;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 必须是枚举类型，且继承HasValueInterface
 * Created by yangzirong on 9/7/2016.
 */
public class GenericEnumTypeHandler<T extends HasValueInterface> extends BaseTypeHandler<T> {
    private Map<Integer, T> valueMap = Collections.emptyMap();
    private Class<T> type;

    private static final Logger logger = LoggerFactory.getLogger(GenericEnumTypeHandler.class);

    public GenericEnumTypeHandler(Class<T> type) throws IllegalAccessException, InstantiationException {
        if (type == null) throw new IllegalArgumentException("Type argument cannot be null");
        T ts[] = type.getEnumConstants();
        if (null == ts) throw new IllegalArgumentException("Type argument cannot be non enum class");
        valueMap = new HashMap<>(ts.length << 1);
        for (T t : ts) {
            HasValueInterface hasValueInterface = (HasValueInterface)t;
            valueMap.put(hasValueInterface.getValue(), t);
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        // baseTypeHandler已经帮我们做了parameter的null判断
        ps.setInt(i, parameter.getValue());

    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // 根据数据库存储类型决定获取类型，本例子中数据库中存放INT类型
        int i = rs.getInt(columnName);

        if (rs.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值，定位EnumStatus子类
            return locateEnumStatus(i);
        }
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // 根据数据库存储类型决定获取类型，本例子中数据库中存放INT类型
        int i = rs.getInt(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值，定位EnumStatus子类
            return locateEnumStatus(i);
        }
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        // 根据数据库存储类型决定获取类型，本例子中数据库中存放INT类型
        int i = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值，定位EnumStatus子类
            return locateEnumStatus(i);
        }
    }

    /**
     * 枚举类型转换
     *
     * @param code 数据库中存储的自定义code属性
     * @return code对应的枚举类
     */
    private T locateEnumStatus(int code) {
        T t = valueMap.get(code);
        if (null == t) {
            logger.warn("value:{} not convertable from class:{}", code, type.getCanonicalName());
        }
        return t;
    }
}
