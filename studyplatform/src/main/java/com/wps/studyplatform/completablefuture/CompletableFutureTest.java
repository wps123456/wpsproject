package com.wps.studyplatform.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Title CompletableFutureTest
 * @Description
 * @auther wps
 * @Date 2020/5/817:26
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws Exception {
        //runAsync();
      //  supplyAsync();
       // thenApply();
      //  handle();
        //handleSecond();
        thenAccept();
    }

    /**
     * public static CompletableFuture<Void> runAsync(Runnable runnable)
     * public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
     * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
     * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
     * 没有指定Executor的方法会使用ForkJoinPool.commonPool() 作为它的线程池执行异步代码。如果指定线程池，则使用指定的线程池运行。以下所有的方法都类同。
     * runAsync方法不支持返回值。
     * supplyAsync可以支持返回值。
     */
    //无返回值
    public static void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> result = CompletableFuture.runAsync(()->{

            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("暂停1分钟");
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("run 执行完毕");
        });
        result.get();

    }
    //有返回值
    public static void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run end ...");
            return System.currentTimeMillis();
        });

        long time = future.get();
        System.out.println("time = "+time);

    }
    /**
     * thenApply 方法
     * 当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化。
     *
     * public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
     * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
     * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
     * Function<? super T,? extends U> T：上一个任务返回结果的类型 U：当前任务的返回值类型
     */
    public static void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                long result = new Random().nextInt(100);
                System.out.println("result1="+result);
                return result;
            }
        }).thenApply(new Function<Long, Long>() {
            @Override
            public Long apply(Long t) {
                long result = t*5;
                System.out.println("result2="+result);
                return result;
            }
        });

        long result = future.get();
        System.out.println(result);
    }
    /**
     * handle 方法
     * handle 是执行任务完成时对结果的处理。 handle 方法和 thenApply 方法处理方式基本一样。
     *        不同的是
     *               handle 是在任务完成后再执行，还可以处理异常的任务。
     *               thenApply 只可以执行正常的任务，任务出现异常则不执行 thenApply 方法。
     * public <U> CompletionStage<U> handle(BiFunction<? super T, Throwable, ? extends U> fn);
     * public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn);
     * public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn,Executor executor)
     */
    public static void handle() throws Exception{
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {

            @Override
            public Integer get() {
                int i= 10/1;
                System.out.println("出现异常后");
                return i;
            }
        }).handle(new BiFunction<Integer, Throwable, Integer>() {
            //Throwable：上述执行过程中是否产生异常
            @Override
            public Integer apply(Integer param, Throwable throwable) {
                int result = -1;
                if(throwable==null){
                    result = param * 2;
                    System.out.println("无异常情况"+result);
                }else{
                    System.out.println(throwable.getMessage());
                }
                return result;
            }
        });
        System.out.println(future.get());
    }
    //handle的第二种用法
    public static void handleSecond() throws Exception{
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()-> {
                int i= 10/1;
                System.out.println("出现异常后");
                return i;
        }).handle(new BiFunction<Integer, Throwable, Integer>() {
            //Throwable：上述执行过程中是否产生异常
            @Override
            public Integer apply(Integer param, Throwable throwable) {
                int result = -1;
                if(throwable==null){
                    result = param * 2;
                    System.out.println("无异常情况"+result);
                }else{
                    System.out.println(throwable.getMessage());
                }
                return result;
            }
        });
        System.out.println(future.get());
    }
    /**
     *  thenAccept 消费处理结果
     * 接收任务的处理结果，并消费处理，无返回结果。
     * public CompletionStage<Void> thenAccept(Consumer<? super T> action);
     * public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action);
     * public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action,Executor executor);
     */
    public static void thenAccept() throws Exception{
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 1;
            }
        }).thenAccept(integer -> {
            System.out.println(integer);
        });
        System.out.println("future的返回值"+future.get());
    }
    /**
     * thenRun 方法
     * 跟 thenAccept 方法不一样的是，不关心任务的处理结果。只要上面的任务执行完成，就开始执行 thenAccept 。
     * public CompletionStage<Void> thenRun(Runnable action);
     * public CompletionStage<Void> thenRunAsync(Runnable action);
     * public CompletionStage<Void> thenRunAsync(Runnable action,Executor executor);
     * 该方法同 thenAccept 方法类似。不同的是上个任务处理完成后，并不会把计算的结果传给 thenRun 方法。只是处理玩任务后，执行 thenAccept 的后续操作。
     */
    public static void thenRun() throws Exception{
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt(10);
            }
        }).thenRun(() -> {
            System.out.println("thenRun ...");
        });
        future.get();
    }
}
