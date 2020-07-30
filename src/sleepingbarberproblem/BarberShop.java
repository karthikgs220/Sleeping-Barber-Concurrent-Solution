package sleepingbarberproblem;
import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;


public class BarberShop {

	public static void main(String[] args) {
		System.out.println("Barber shop is now open");
		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<Integer> BarberChair = new ArrayList<Integer>();
		List<Integer> WaitingChair = new ArrayList<Integer>();
		Runnable ob1 = new Customer(BarberChair,WaitingChair);
		executor.execute(ob1);
		
		Runnable ob2 = new Barber(BarberChair,WaitingChair);
		executor.execute(ob2);
		
		executor.shutdown();
		while(executor.isTerminated()) {
			System.out.println("Barber shop is now closed");
		}
	}

}
