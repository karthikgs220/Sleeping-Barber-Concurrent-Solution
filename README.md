# Sleeping-Barber-Concurrent-Solution

Sleeping Barber Problem

Design:

•	The code involves three Java classes i.e. Barber, Customer and the BarberShop.

•	We use two Lists to demonstrate the Barber’s chair and the Waiting chair, which are the common Lists to be used amongst the threads. 

•	The executor framework is used to demonstrate an asynchronous concurrent execution of the methods passed to the threads. The executor framework automatically associates the threads and schedules them and enables inter thread communication.

•	We use two objects, which we use as Customer and the Barber objects, which in turn are passed to the executor framework object to automatically spawn and handle the execution.

•	The barber class is expected to do the hair cut to customers that arrive randomly on the chairs and when there are no customers, the barbers sleep on the chair.

•	This is demonstrated by the doHairCut method, which takes the chairNo and the customerNo as parameters.

•	The doHaircut method uses the common lists BarberChair & WaitingChair to update and facilitate the barber shop.

•	It uses two synchronized blocks:

o	For demonstrating the situation when there are no customers in the barber chair nor in the waiting queue. When there is no customer the barber shop, the barber sleeps using the wait method, waiting for a customer to arrive.

o	For demonstrating the situation when there is a customer to be attended to or if there is a customer waiting in the waiting queue whose hair must be cut. The next arriving customer must be checked against the waitinglist and in case he came from the waiting list, he is first removed from the waiting list. Then his hair is cut and then removed from the Barber chair and escorted out by the barber. Once completed, the barber notifies that his job is finished, and he is ready to take on the next customer.

•	The customer class is expected to demonstrate a customer, who gets his hair cut from the barber. The customers arrive in random intervals.

•	The LIMIT variable denotes the maximum number of customers that can be seated at an instant. 

•	Like the barber class, in case all the barber chairs are currently full, the customers are expected to enter the waiting list and wait for their turn. This is demonstrated in the first synchronized block of the getHairCut method.

•	If the customer has come out of the waiting list, he is automatically added into the barber chair list by the getHairCut method and then the barber is notified that the customer is ready to get his hair cut.

•	Hence, the usage of synchronization which enforces the usage of implicit locks on the shared resources at a particular instant and usage of wait and notify to efficiently enforce communication between the threads will provide a solution to the problem.


Correctness Properties:
	Safety Properties: 

•	Mutual Exclusion: According to this, two threads must not interfere with each other’s execution of a block of code or interfere with a common updation of a collection variable. In our code, we use a synchronized block on the lists to prevent the threads from performing updations on the Lists simultaneously.  To further make the system more thread-safe, we are using CopyOnWrite Lists in place of normal lists, which are a thread-safe version of lists. 

•	Deadlock: The deadlock state is when two threads are waiting for a mutual resource and as both threads are stuck in such a state the entire system comes to a halt. This situation is terminated in our system as customers keep coming in random order and the barber continues with his hair cut. Either the barber waits for customers or the customer wait in the waiting chairs and hence both are not waiting at the same time.

	Liveness Properties:

•	Starvation: Starvation is when either of the process is waiting for a resource and the other thread continues to hold onto the resource/continues to get inputs, which puts the first thread in an indefinite wait state. This state is not encountered in our system because once the barber continues to clear customers, new customers come into the barber shop in a random order and until the barber is working, the customers are automatically generated and hence the threads support each other to prevent starvation.

•	Fairness: Fairness refers to the concept of allocating resources to a thread. In our system, the FIFO(First in first out) is followed. The customer who comes in first is attended by the barber and the customer who comes in when all the chairs are full, is placed on the waiting chair and transferred into the barber chair after the chair is free, in the order of their arrival and because of this, our system is said to have a fair allocation of resources.
