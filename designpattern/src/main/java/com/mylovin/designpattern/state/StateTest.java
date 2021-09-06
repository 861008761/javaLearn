package com.mylovin.designpattern.state;

/**
 * 状态模式
 * 运行时类的行为由其状态决定；
 * <p>
 * 使用场景： 对象依赖装填，行为随状态改变而改变的情景，或者存在大量的if else和分支结构等；
 * <p>
 * 实现：将对象的状态封装成单个的类，每个状态处理该状态下的事务，并控制该状态到其他状态的转移；
 * <p>
 * 优点： 容易新加状态，封装了状态转移规则，每个状态可以被复用和共享，避免大量的if else结构。
 * <p>
 * 缺点： 该模式结构和实现相对复杂，状态过多导致增加类和对象个数。同时由于由每个状态控制向其他状态的转移，新加状态必须要修改现有的部分状态才能加入状态机中生效。
 */
public class StateTest {
    public static void main(String[] args) {
        Washing washing = new Washing();
        washing.setState(new Start());
        washing.request();
    }
}
