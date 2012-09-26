package edu.ufl.cise.cop5618fa12.hw1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class PetersonLock implements Lock
{
    final private ThreadLocal<Integer> THREAD_ID = new ThreadLocal<Integer>(){
        final private AtomicInteger id = new AtomicInteger(0);
        @Override
        protected Integer initialValue(){
            return id.getAndIncrement();
        }
    };
    
    final private AtomicBoolean[] flag = new AtomicBoolean[2];
    private volatile int victim;
    
    public PetersonLock(){
        for(int i=0 ; i<flag.length ; ++i)
            flag[i] = new AtomicBoolean();
    }
    
    public void lock() {
        int i = THREAD_ID.get() % 2;
        int j = 1 - i;
        flag [i].set(true); // I'm interested
        victim = i ; // you go first
        while ( flag[j].get() && victim == i) {}; // wait
    }
    
    public void unlock() {
        int i = THREAD_ID.get() % 2;
        flag[i].set(false); // I'm not interested
    }

	@Override
	public void lockInterruptibly() throws InterruptedException {
	}

	@Override
	public Condition newCondition() {
		return null;
	}

	@Override
	public boolean tryLock() {
		return false;
	}

	@Override
	public boolean tryLock(long arg0, TimeUnit arg1)
			throws InterruptedException {
		return false;
	}

}
