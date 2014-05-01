package com.etech.ha.threadlocal;

public class TestClient extends Thread {
	private SequenceNumber sn;
	
	public TestClient(SequenceNumber sn) {
		this.sn = sn;
	}
	
	public void run() {
		for (int i=0; i<10; i++) {
			System.out.println("thread[" + Thread.currentThread().getName() + "] sn["
					+ sn.getNextNum() + "]");
		}
	}
}
