package com.deloitte.hk.common.mybatis.page;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;


@Intercepts({@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})})
public class PageAfterInterceptor implements Interceptor {
    private static final Log logger = LogFactory.getLog(PageAfterInterceptor.class);
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();
    private static String defaultPageSqlId = ".*Page$"; // 需要拦截的ID(正则匹配)

    public Object intercept(Invocation invocation) throws Throwable {
        ResultSetHandler handler = (ResultSetHandler) invocation.getTarget();
        MetaObject metaHandler = MetaObject.forObject(handler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,
                DEFAULT_REFLECTOR_FACTORY);
        MappedStatement mappedStatement = (MappedStatement) metaHandler.getValue("mappedStatement");
        // 只重写需要分页的sql语句。通过MappedStatement的ID匹配，默认重写以Page结尾的MappedStatement的sql
        if (mappedStatement.getId().matches(defaultPageSqlId)) {
            PageParameter page = (PageParameter) metaHandler.getValue("boundSql.parameterObject.page");
            if (page == null) {
                return invocation.proceed();
            } else {
                // 将分页信息加入返回结果中
                Object rsultList = invocation.proceed();
                PageList pageList = new PageList();
                pageList.addAll((ArrayList) rsultList, page);
                return pageList;
            }
        }
        // 将执行权交给下一个拦截器
        return invocation.proceed();
    }

    public Object plugin(Object target) {
        // 当目标类是ResultSetHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数
        if (target instanceof ResultSetHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    public void setProperties(Properties properties) {
    }
}
