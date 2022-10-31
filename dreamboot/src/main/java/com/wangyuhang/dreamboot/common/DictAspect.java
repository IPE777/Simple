package com.wangyuhang.dreamboot.common;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.wangyuhang.dreamboot.mapper.SysDictDetailMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

@Aspect
@Component
@Slf4j
public class DictAspect {


    @Autowired
    private SysDictDetailMapper sysDictDetailMapper;
    public static final String suffix = "_dictText";

    //定义切点
    @Pointcut("execution(public * com.wangyuhang..*.*Controller.*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public Object translate(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        parseDict(result);
        return result;
    }

    /**
     * 1、遍历结果类的每个字段，查找有dict注解的字段
     * 2、根据dictCode，在数据字典中找到相应的k-v值
     * 3、添加到返回结果中
     */
    private void parseDict(Object result) {
        if(result instanceof Result){
            Object valueObj = ((Result) result).getResult();
            if(valueObj!=null){
                //将结果转成JSONObject
                ObjectMapper objectMapper = new ObjectMapper();
                String json = "{}";
                try {
                    log.info("进行数据字典翻译，返回值：{}", objectMapper.writeValueAsString(result));
                    json = objectMapper.writeValueAsString(valueObj);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Json转换失败"+e.getMessage());
                }
                JSONObject jsonObject =  JSONObject.parseObject(json);
                //获取所有字段
                Field[] fields = valueObj.getClass().getDeclaredFields();
                if(fields!=null && fields.length!=0){
                    Iterator<Field> iterator = Arrays.stream(fields).iterator();
                    //获取标注dict注解的字段
                    while (iterator.hasNext()){
                        Field field = iterator.next();
                        Dict dict = field.getAnnotation(Dict.class);
                        if(dict!=null){

                            //从数据字典中查询code对应的k-v值
                            //DictConfig config = dictService.select(dict.dictCode());

                            //模拟数据
                            HashMap<String, String> dictMap = new HashMap<>();

                            dictMap.put("1","男");
                            dictMap.put("2","女");


                            //字段转换
                            String key = String.valueOf(jsonObject.get(field.getName()));
                            String convertValue = dictMap.get(key);
                            jsonObject.put(field.getName()+suffix,convertValue);
                        }
                    }
                    ((Result) result).setResult(jsonObject);
                }
            }
        }
    }
}
