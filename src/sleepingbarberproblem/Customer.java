package sleepingbarberproblem;

import java.util.List;

public class Customer implements Runnable {
	
	List<Integer> BarberChair = null;
	List<Integer> WaitingChair = null;
	final int LIMIT = 10;
	private int chairNo, customerNo;
	
	public Customer(List<Integer> BarberChair,List<Integer> WaitingChair) { //Constructor for customer
		this.BarberChair = BarberChair;
		this.WaitingChair = WaitingChair;
	}
	
	public void gethairCut(int chairNo, int customerNo) throws InterruptedException {
		synchronized (BarberChair) {
			while(BarberChair.size() == LIMIT) {
				WaitingChair.add(customerNo);
				System.out.println("All chairs are full");
				for(int i=0; i< WaitingChair.size(); i++) {
					System.out.println("Waiting Chair: " +i+ " Occupied by customer numer: " + WaitingChair.get(i));
				}
				BarberChair.wait();
			}
		}
		
		synchronized (BarberChair) {
			System.out.println("New customer: " +customerNo + " on chair: " +chairNo);
			BarberChair.add(chairNo);
			Thread.sleep(100);
			BarberChair.notify();
		}
	}
	
	
	
	@Override
	public void run() {
		while(true) {
			try {
				gethairCut(chairNo++, customerNo++);
				if(chairNo == 10){
					chairNo = 0;
				}
				else if(customerNo == 10) {
					customerNo = 0;
				}
			} catch (InterruptedException e) {
				System.out.println("Unable to generate customers");
			}
		}
		
	}

}
