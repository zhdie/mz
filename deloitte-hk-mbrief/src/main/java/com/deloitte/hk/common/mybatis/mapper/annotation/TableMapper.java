package com.deloitte.hk.common.mybatis.mapper.annotation;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

public class TableMapper {

    private Annotation tableMapperAnnotation;

    private Map<String, FieldMapper> fieldMapperCache;

    private List<FieldMapper> fieldMapperList;

    public List<FieldMapper> getFieldMapperList() {
        return fieldMapperList;
    }

    public void setFieldMapperList(List<FieldMapper> fieldMapperList) {
        this.fieldMapperList = fieldMapperList;
    }

    public Annotation getTableMapperAnnotation() {
        return tableMapperAnnotation;
    }

    public void setTableMapperAnnotation(Annotation tableMapperAnnotation) {
        this.tableMapperAnnotation = tableMapperAnnotation;
    }

    public Map<String, FieldMapper> getFieldMapperCache() {
        return fieldMapperCache;
    }

    public void setFieldMapperCache(Map<String, FieldMapper> fieldMapperCache) {
        this.fieldMapperCache = fieldMapperCache;
    }

}
