package com.hspedu.hspmybatis.config;

/**
 * @author yangda
 * @create 2023-10-20-14:43
 * @description: 记录对应的Mapper的方法的信息 指的是Mapper.xml 中的实现方法的信息
 */
public class Function {

    // 属性
    private String sqlType; //sql类型，比如select，insert，update,delete
    private String funcName; //方法名
    private String sql; //执行的sql语句
    private Object resultType; //返回类型
    private String parameterType; //参数类型

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Object getResultType() {
        return resultType;
    }

    public void setResultType(Object resultType) {
        this.resultType = resultType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }


    @Override
    public String toString() {
        return "Function{" +
                "sqlType='" + sqlType + '\'' +
                ", funcName='" + funcName + '\'' +
                ", sql='" + sql + '\'' +
                ", resultType=" + resultType +
                ", parameterType='" + parameterType + '\'' +
                '}';
    }
}
