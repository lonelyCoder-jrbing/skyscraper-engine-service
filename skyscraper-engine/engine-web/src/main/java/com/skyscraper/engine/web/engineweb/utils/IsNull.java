package com.skyscraper.engine.web.engineweb.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IsNull {
    //整个类都校验
    public static List<String> validateProperty(Object validateObj) {
        return validateProperty(validateObj,(String[])null);
    }
    //类中的某些字段不校验
    public static List<String> validateProperty(Object validateObj,String... ignoreProperties) {
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(validateObj.getClass());
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);
        List<String> errList = new ArrayList<>();
        for (PropertyDescriptor targetPd : targetPds) {
            Method readMethod = targetPd.getReadMethod();
            if (readMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                try {
                    if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                        readMethod.setAccessible(true);
                    }
                    Object value = readMethod.invoke(validateObj);
                    if (value instanceof String) {
                        if (StringUtils.isEmpty((String) value)) {
                            errList.add(validateObj.getClass().getSimpleName()+ "中的" + targetPd.getName() + "不能为空");
                            continue;
                        }
                    }
                    if (value instanceof Float || value instanceof Integer) {
                        if (StringUtils.isEmpty(value.toString())) {
                            errList.add(validateObj.getClass().getSimpleName()+ "中的" + targetPd.getName() + "不能为空");
                            continue;
                        }
                    }
                    if (value == null) {
                        errList.add(validateObj.getClass().getSimpleName() + "中的" + targetPd.getName() + "不能为空");
                    }
                } catch (Throwable ex) {
                    throw new FatalBeanException(
                            "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                }
            }
        }
        return errList;
    }

}