package com.xcjy.web.interceptors;

import com.xcjy.web.common.XcjyThreadLocal;
import com.xcjy.web.controller.req.Page;
import com.xcjy.web.util.ReflectUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.util.Properties;

/**
 * Created by tupeng on 2017/7/22.
 * <p>
 * mybatis 拦截器，统一对sql进行处理
 */
@Intercepts({@Signature(method = "query", type = StatementHandler.class, args = {Statement.class, ResultHandler.class})})
public class MybatisQueryInterceptors implements Interceptor {

    private static final String whereCondition = " WHERE deleted = FALSE";

    private static final Integer defaultPageSize = 20;

    private static final String andCondition = " AND deleted = FALSE";

    private static final String andSchoolIdCondition = " AND school_id = ";

    private static final String whereSchoolIdCondition = " WHERE school_id = ";

    private static final String
            [] deletedMatches = new String[]{"wheredeleted=false", "anddeleted=false"};

    private static final String sqlSuffix = ";";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
        StatementHandler delegate = (StatementHandler) ReflectUtil.getProperty(handler, "delegate");
        BoundSql boundSql = delegate.getBoundSql();
        String sql = getPageSql(appendDeleted(appendSchoolId(boundSql.getSql())));
        ReflectUtil.setProperty(boundSql, "sql", sql);
        return invocation.proceed();
    }

    private String getPageSql(String sql){
        StringBuilder builder = new StringBuilder(replaceEndOfSql(sql));
        Page page = XcjyThreadLocal.getPage();
        if(null == page) {
            return sql;
        }
        Integer pageIndex = 0;
        Integer pageSize = defaultPageSize;
        if(null != page.getPage() && page.getPage() > 0) {
            pageIndex = page.getPage() - 1;
        }
        if(null != page.getPageSize() && page.getPageSize() > 0) {
            pageSize = page.getPageSize();
            pageIndex = pageIndex * pageSize;
        }
        XcjyThreadLocal.removePage();
        return builder.append(" LIMIT ").append(pageIndex.toString()).append(", ").append(pageSize.toString()).append(" ; ").toString();
    }

    private String appendSchoolId(String sql) {
        sql = replaceEndOfSql(sql);
        String schoolId = XcjyThreadLocal.getSchoolId();
        if (StringUtils.isNotBlank(schoolId)) {
            if (sql.toLowerCase().contains("where")) {
                return sql + andSchoolIdCondition + "'" + schoolId + "'";
            } else {
                return sql + whereSchoolIdCondition + "'" + schoolId + "'";
            }
        }
        return sql;
    }

    private String appendDeleted(String sql) {
        sql = replaceEndOfSql(sql);
        if (!matches(sql)) {
            if (sql.toLowerCase().contains("where")) {
                sql += andCondition;
            } else {
                sql += whereCondition;
            }
        }
        return appendEndOfSql(sql);
    }

    private String replaceEndOfSql(String sql) {
        return sql.replaceAll(sqlSuffix, "");
    }

    private String appendEndOfSql(String sql) {
        return sql + sqlSuffix;
    }

    private Boolean matches(String sql) {
        String tempSql = sql.replaceAll(" ", "").toLowerCase();
        for (String match : deletedMatches) {
            if (tempSql.contains(match)) {
                return true;
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
