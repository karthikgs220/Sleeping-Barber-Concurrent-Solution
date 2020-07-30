package sleepingbarberproblem;

import java.util.List;

public class Barber implements Runnable{
	List<Integer> BarberChair = null;
	List<Integer> WaitingChair = null;
	private Integer chairNo, customerNo;
	
	public Barber(List<Integer> BarberChair,List<Integer> WaitingChair) { //Constructor for customer
		this.BarberChair = BarberChair;
		this.WaitingChair = WaitingChair;
	}
	

	@SuppressWarnings("deprecation")
	public void dohairCut(int chairNo, int customerNo) throws InterruptedException {
		synchronized (BarberChair) {
			while(BarberChair.isEmpty()) {
				System.out.println("Barbers are sleeping as no customers");
				BarberChair.wait();
			}
		}
		
		synchronized (BarberChair) {
			if(WaitingChair.equals(customerNo)) {
				WaitingChair.remove(new Integer(customerNo));
			}
			Thread.sleep(1000);
			System.out.println("Doing haircut for customer: " +customerNo + " on chair: " +chairNo);
			BarberChair.remove(0);
			BarberChair.notify();
		}
	}
	
	
	@Override
	public void run() {
		while(true) {
			try {
				dohairCut(chairNo++, customerNo++);
				if(chairNo == 10){
					chairNo = 0;
				}
				else if(customerNo == 10) {
					customerNo = 0;
				}
			} catch (InterruptedException e) {
				System.out.println();
			}
		}
		
	}
}
