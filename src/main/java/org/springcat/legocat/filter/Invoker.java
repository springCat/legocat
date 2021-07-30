package org.springcat.legocat.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @Description Invoker
 * @Author springCat
 * @Date 2021-7-30 8:45
 */
public class Invoker<T>  {

    private BiConsumer<Exception,Context<T>> errorHandler;

    private List<Consumer<Context<T>>> decoratorList = new ArrayList<>();

    public static <T> Invoker<T> create(){
        return new Invoker<T>();
    }

    public Invoker<T> step(Consumer<Context<T>> consumer){
        decoratorList.add(consumer);
        return this;
    }

    public Invoker<T> invoker(Function<Context<T>,T> invoker){
        decoratorList.add(context -> {
            T result = invoker.apply(context);
            context.setResult(result);
        });
        return this;
    }

    public Invoker<T> errorHandler(BiConsumer<Exception,Context<T>> errorHandler){
        this.errorHandler = errorHandler;
        return this;
    }

    public Optional<T> get(){
        Context<T> context = new Context<T>();
        return get(context);
    }

    public Optional<T> get(Context<T> context){
        try {
            for (Consumer<Context<T>> decorator : decoratorList) {
                if(context.isFinish()){
                    return Optional.empty();
                }
                decorator.accept(context);
            }
        }catch (Exception exception){
            if(errorHandler != null){
                errorHandler.accept(exception,context);
            }
        }
        return Optional.ofNullable(context.getResult());
    }

}
