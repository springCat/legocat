package org.springcat.legocat.filter;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.log.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Description Filter
 * @Author springCat
 * @Date 2021-7-30 8:45
 */
public class Filter<T>  {

    private Invoker<T> invoker;

    private ErrorHandler errorHandler;

    private List<Before> beforeList = new ArrayList<>();

    private List<After> afterList = new ArrayList<>();

    public static <T> Filter<T> create(){
        return new Filter<T>();
    }

    public Filter<T> before(Before consumer){
        beforeList.add(consumer);
        return this;
    }

    public Filter<T> after(After consumer){
        afterList.add(consumer);
        return this;
    }

    public Filter<T> invoker(Invoker<T> invoker){
        this.invoker = invoker;
        return this;
    }

    public Filter<T> errorHandler(ErrorHandler errorHandler){
        this.errorHandler = errorHandler;
        return this;
    }


    public Optional<T> get(){
        return get(Dict.create());
    }

    public Optional<T> get(Dict dict){
        if(ObjectUtil.isEmpty(invoker)){
            return Optional.empty();
        }

        T result = null;
        try {
            for (Before before : beforeList) {
                if(!before.execute(dict)){
                    return Optional.empty();
                }
            }

            result = invoker.execute(dict);

            for (After after : afterList) {
                after.execute(dict);
            }

        }catch (Exception exception){
            if(errorHandler != null){
                errorHandler.invoke(exception);
            }
        }
        return Optional.ofNullable(result);
    }

}
