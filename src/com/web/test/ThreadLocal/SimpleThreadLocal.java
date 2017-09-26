package com.web.test.ThreadLocal;

/**
 * @author Administrator
 * 使用ThreadLocal解决线程安全问题
 * ThreadLocal采用了“以空间换时间”的方式：访问并行化，对象独享化
 * 为每一个线程都提供了一份变量，因此可以同时访问而互不影响
 */
public class SimpleThreadLocal {
    //①通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值  
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){  
        public Integer initialValue(){  
            return 0;  
        }  
    };  
       
        //②获取下一个序列值  
    public int getNextNum(){  
        seqNum.set(seqNum.get()+1);  
        return seqNum.get();  
    }        
    public static int i=2;
    public static void main(String[] args) {
    	 SimpleThreadLocal sn = new SimpleThreadLocal();             
         //③ 3个线程共享sn，各自产生序列号  
         TestClient t1 = new TestClient(sn);    
         TestClient t2 = new TestClient(sn);  
         TestClient t3 = new TestClient(sn);  
         t1.start();  
         System.out.println("t1启动");
         t2.start();  
         System.out.println("t2启动");
         t3.start(); 
         System.out.println("t3启动");
	}
    
    private static class TestClient extends Thread  {  
    	private SimpleThreadLocal sn;  
        public TestClient(SimpleThreadLocal sn) {  
            this.sn = sn;  
        }  
        public void run()  {  
            //④每个线程打出3个序列值  
            for (int i = 0; i < 3; i++) {  
               System.out.println("thread["+Thread.currentThread().getName()+  "] sn["+sn.getNextNum()+"]");                    
               try {
   				Thread.sleep(500);
   				} catch (InterruptedException e) {
   					// TODO Auto-generated catch block
   					e.printStackTrace();
   				}
            }  
        }  
    }  
    
      /* 打印结果，但Thread间的顺序不固定的
        t1启动
		t2启动
		t3启动
        thread[Thread-0] sn[1]
		thread[Thread-2] sn[1]
		thread[Thread-1] sn[1]
		thread[Thread-0] sn[2]
		thread[Thread-1] sn[2]
		thread[Thread-2] sn[2]
		thread[Thread-1] sn[3]
		thread[Thread-2] sn[3]
		thread[Thread-0] sn[3]
	  */
}
