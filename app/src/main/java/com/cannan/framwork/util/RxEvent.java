package com.cannan.framwork.util;


import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by Cannan on 2017/7/28 0028.
 * rx 实现类似EventBus 的功能
 * 可代替广播等通讯
 */

public class RxEvent<T> {

    private static volatile RxEvent instance;

    private final Subject<Object>  bus= PublishSubject.create().toSerialized();

    private Disposable dis;

    private RxEvent(){ }

    //内部类
    private static class RxBusInstance {
        private static final RxEvent rxBus = new RxEvent();
    }

    /**
     * 单例模式RxBus
     */
    public static RxEvent getInstance() {
        return RxBusInstance.rxBus;
    }

    /**
     * 发送消息
     *
     * @param t
     */
    public void post(Object t) {
        bus.onNext(t);
    }

    /**
     * 接收消息
     *
     * @param eventType
     * @return
     */
    public  void register(Class<T> eventType, Consumer<T> consumer) {
         dis = bus.ofType(eventType).subscribe(consumer);
    }

    /**
     * 判断是否有订阅者
     * @return
     */
    public boolean hasSubscribers() {
        return bus.hasObservers();
    }

    /**
	 * 取消订阅，防内存泄漏
     */
    public void unSubscrib(){
        if(dis != null  && dis.isDisposed()){
            dis.dispose();
        }
    }
}
