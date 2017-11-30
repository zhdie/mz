package com.deloitte.hk.common.mybatis.mapper.annotation;

import org.apache.ibatis.type.JdbcType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface FieldMapperAnnotation {
    /**
     * 
     * 对应数据库表的字段名称
     */
    String dbFieldName();

    /**
     * 
     * 
     * 字段用JDBC接口存入数据库需要设置的数据类型,Integer,Long,Short,Float,Double,String,Date ,Timestamp,Time
     */
    JdbcType jdbcType();
}
