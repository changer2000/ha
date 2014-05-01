package com.etech.ha.threadlocal;

public class SequenceNumber {
	
	private  ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {	//static
		@Override
		protected Integer initialValue() {
			return 0;
		}
	};
	
	public int getNextNum() {
		seqNum.set(seqNum.get()+1);
		return seqNum.get();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SequenceNumber sn = new SequenceNumber();
		
		TestClient t1 = new TestClient(sn);
		TestClient t2 = new TestClient(sn);
		TestClient t3 = new TestClient(sn);
		
		t1.start();
		t2.start();
		t3.start();
	}
	/*
	private static class TestClient extends Thread {	如果没有static，就会有编译错
		private SequenceNumber sn;
		
		public TestClient(SequenceNumber sn) {
			this.sn = sn;
		}
		
		public void run() {
			for (int i=0; i<3; i++) {
				System.out.println("thread[" + Thread.currentThread().getName() + "] sn["
						+ sn.getNextNum() + "]");
			}
		}
	}
	*/
}
