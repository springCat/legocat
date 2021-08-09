package org.springcat.legocat.invoker;

import org.springcat.legocat.common.DictContext;
import org.springcat.legocat.common.ErrorHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @Description Invoker
 * @Author springCat
 * @Date 2021-7-30 8:45
 */
public class Invoker<T>  {

    private ErrorHandler<T> errorHandler;

    private List<Consumer<DictContext<T>>> decoratorList = new ArrayList<>();

    public static <T> Invoker<T> create(){
        return new Invoker<T>();
    }

    public Invoker<T> step(Consumer<DictContext<T>> consumer){
        decoratorList.add(consumer);
        return this;
    }

    public Invoker<T> invoker(Function<DictContext<T>,T> invoker){
        decoratorList.add(context -> {
            T result = invoker.apply(context);
            context.setResult(result);
        });
        return this;
    }

    public Invoker<T> errorHandler(ErrorHandler<T> errorHandler){
        this.errorHandler = errorHandler;
        return this;
    }

    public Optional<T> get(){
        DictContext<T> context = new DictContext<T>();
        return get(context);
    }

    public Optional<T> get(DictContext<T> context){
        try {
            for (Consumer<DictContext<T>> decorator : decoratorList) {
                if(context.isFinish()){
                    return Optional.empty();
                }
                decorator.accept(context);
            }
        }catch (Exception exception){
            errorHandler.execute(exception,context);
        }
        return Optional.ofNullable(context.getResult());
    }

}
