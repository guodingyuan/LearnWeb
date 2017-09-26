package com.web.test.ThreadLocal;


 /**
 * @author Administrator
 * 关于线程安全问题
 * 另外：请务必记住，千万别用Junit4对多线程进行测试，不然会被坑惨的
 */
public class MyCount {
	public int num=5;  
    public void count() {  
    	System.out.println(Thread.currentThread().getName() + "前：" + num);  
    	num--;
    	try {
 			Thread.sleep(1000);
 		} catch (InterruptedException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    	System.out.println(Thread.currentThread().getName() + "后：" + num);  	
    }  

    public MyThread myThread1=new MyThread(); 
    public MyThread myThread2=new MyThread(); 
    public MyThread myThread3=new MyThread(); 
    public MyThread myThread4=new MyThread(); 
    public class MyThread extends Thread{

		@Override
		public void run() {
			super.run();
			//不加同步锁
			/*while (num>0) {
			   count();	
			}*/
			
			//加对象同步锁
			/*while (num>0) {	
				synchronized (this) {
					count();
				}				
			}*/
		
			//加类同步锁
			/*while (num>0) {
				synchronized (MyThread.class) {
					count();
				}												
			}	*/	
			
			//使用ThreadLocal解决线程安全的问题
		    while (seqNum.get()>0) {
		    	System.out.println(Thread.currentThread().getName() + "前：" + seqNum.get());  
		    	seqNum.set(seqNum.get()-1); 
		    	try {
		 			Thread.sleep(1000);
		 		} catch (InterruptedException e) {
		 			// TODO Auto-generated catch block
		 			e.printStackTrace();
		 		}
		    	System.out.println(Thread.currentThread().getName() + "后：" + seqNum.get()); 
			}
		}    	
    }
    
    
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){  
        public Integer initialValue(){  
            return 5;  
        }  
    };  
      
    public static void main(String[] args) {
    	MyCount myCount=new MyCount();
    	myCount.myThread1.start();
    	myCount.myThread2.start();
	}
}    
/*    结论：
 *（1）当单线程时，运行结果正常
 *      Thread-0前：5
		Thread-0后：4
		Thread-0前：4
		Thread-0后：3
		Thread-0前：3
		Thread-0后：2
		Thread-0前：2
		Thread-0后：1
		Thread-0前：1
		Thread-0后：0
 *（2）当多线程，不加同步锁时，运行结果异常
 *      Thread-1前：5
		Thread-0前：5
		Thread-0后：3
		Thread-0前：3
		Thread-1后：2
		Thread-1前：2
		Thread-0后：1
		Thread-0前：1
		Thread-1后：1
		Thread-0后：0		
 *（3）当多线程，加对象同步锁时，运行结果异常
 *    注意：synchronized (this)锁定的是当前对象，此时相当两个线程各有一把锁，但锁的都是自己的，两把锁互不干扰
 *      Thread-0前：5
		Thread-1前：5
		Thread-0后：3
		Thread-0前：3
		Thread-1后：3
		Thread-1前：2
		Thread-1后：1
		Thread-1前：1
		Thread-0后：1
		Thread-1后：0
 *（4）当多线程，加类同步锁时，运行结果正常 
 *    注意：有以下两种运行结果，一种出现了-1（如左边），一种没有出现-1（如右边）
 *    原因：加上同步锁之后，会维持有一个线程队列， 然后线程与线程之间进行切换
 *    当结果出现为0时，如果此时线程队列刚好为空，那么结果就是0
 *    如果此时线程队列里还有线程，那么该线程仍然会继续执行，那么结果就是-1
 *    为避免该结果，可在count()方法里再一次对num进行判定，但在此处需要理解线程队列这个概念
 *      Thread-0前：5            Thread-0前：5
	    Thread-0后：4            Thread-0后：4
	    Thread-1前：4            Thread-1前：4
	    Thread-1后：3            Thread-1后：3
	    Thread-0前：3            Thread-0前：3
	    Thread-0后：2            Thread-0后：2
	    Thread-1前：2            Thread-0前：2
	    Thread-1后：1            Thread-0后：1
	    Thread-0前：1            Thread-1前：1
	    Thread-0后：0            Thread-1后：0
		Thread-1前：0
		Thread-1后：-1
* （5）当多线程，使用ThreadLocal时，运行结果正常 
*     以下结果可以看出，线程0和线程1其实有两份数据，数据之间不互相影响
*       Thread-0前：5
		Thread-1前：5
		Thread-0后：4
		Thread-0前：4
		Thread-1后：4
		Thread-1前：4
		Thread-0后：3
		Thread-0前：3
		Thread-1后：3
		Thread-1前：3
		Thread-0后：2
		Thread-0前：2
		Thread-1后：2
		Thread-1前：2
		Thread-0后：1
		Thread-0前：1
		Thread-1后：1
		Thread-1前：1
		Thread-0后：0
		Thread-1后：0
*/    
    
    