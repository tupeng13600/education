package com.xcjy.web.interceptors;

import com.xcjy.web.common.enums.DbOperationType;
import com.xcjy.web.util.ReflectUtil;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.util.Properties;
import java.util.UUID;

/**
 * Created by tupeng on 2017/7/22.
 * <p>
 * mybatis 拦截器，统一对sql进行处理
 */
@Intercepts({@Signature(method = "update", type = Executor.class, args = {MappedStatement.class, Object.class})})
public class MybatisUpdateInterceptors implements Interceptor {

    private static final String autoCreateIdKey = "id";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        if (null != args && args.length > 0 && isInsert(args)) {
            for (Object arg : args) {
                if (!(arg instanceof MappedStatement)) {
                    String id = UUID.randomUUID().toString().replaceAll("-", "");
                    ReflectUtil.setProperty(arg, autoCreateIdKey, id);
                }
            }
        }
        return invocation.proceed();
    }

    private Boolean isInsert(Object[] args) {
        if (null != args && args.length > 0) {
            for (Object arg : args) {
                if (arg instanceof MappedStatement) {
                    MappedStatement mappedStatement = (MappedStatement) arg;
                    SqlCommandType commandType = mappedStatement.getSqlCommandType();
                    if (commandType.name().equals(DbOperationType.INSERT.name())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
