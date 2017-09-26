package com.web.test.ThreadLocal;


/**
 * @author Administrator
 * 使用同步机制（synchronized）解决线程安全问题
 * 同步机制（synchronized）采用了“以时间换空间”的方式：访问串行化，对象共享化
 * 仅提供一份变量，让不同的线程排队访问
 */
public class SimpleSynchronized {

	private Integer seqNum=0;  
            
    public Integer getSeqNum() {
    	seqNum++;
		return seqNum;
	}

	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}

	public static void main(String[] args) {
		SimpleSynchronized sn = new SimpleSynchronized(); 
        //3个线程共享sn，各自产生序列号    
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
    
    public static class TestClient extends Thread  {  
        private SimpleSynchronized sn;  
        public TestClient(SimpleSynchronized sn) {  
            this.sn = sn; 
        }  
        public void run(){        	
        	synchronized (TestClient.class) {
        		//初始化值为0
            	sn.setSeqNum(0);
        		//每个线程打出3个序列值  	
                for (int i = 0; i < 3; i++) {  
                   System.out.println("thread["+Thread.currentThread().getName()+  "] sn["+sn.getSeqNum()+"]");              
                   try {
    				Thread.sleep(500);
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
                } 	
			}		 		
        }  
    }    
/* 不加synchronized打印结果
t1启动
t2启动
t3启动
thread[Thread-1] sn[1]
thread[Thread-0] sn[1]
thread[Thread-2] sn[1]
thread[Thread-2] sn[2]
thread[Thread-0] sn[3]
thread[Thread-1] sn[4]
thread[Thread-2] sn[5]
thread[Thread-0] sn[6]
thread[Thread-1] sn[7]
*/
    
/* 加synchronized打印结果
t1启动
t2启动
t3启动
thread[Thread-0] sn[1]
thread[Thread-0] sn[2]
thread[Thread-0] sn[3]
thread[Thread-1] sn[1]
thread[Thread-1] sn[2]
thread[Thread-1] sn[3]
thread[Thread-2] sn[1]
thread[Thread-2] sn[2]
thread[Thread-2] sn[3]
*/    
}
