import java.util.Scanner;

public class DoubleEndedQueue {
    private int[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public DoubleEndedQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new int[capacity];
        this.front = -1;
        this.rear = -1;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    public void enqueueFront(int item) {
        if (isFull()) {
            System.out.println("Deque is full. Cannot enqueue at the front.");
            return;
        }

        if (isEmpty()) {
            front = rear = 0;
        } else if (front == 0) {
            front = capacity - 1;
        } else {
            front--;
        }

        queue[front] = item;
        size++;
    }

    public void enqueueRear(int item) {
        if (isFull()) {
            System.out.println("Deque is full. Cannot enqueue at the rear.");
            return;
        }

        if (isEmpty()) {
            front = rear = 0;
        } else {
            rear = (rear + 1) % capacity;
        }

        queue[rear] = item;
        size++;
    }

    public int dequeueFront() {
        if (isEmpty()) {
            System.out.println("Deque is empty. Cannot dequeue from the front.");
            return -1;
        }

        int removedItem = queue[front];
        if (front == rear) {
            front = rear = -1;
        } else {
            front = (front + 1) % capacity;
        }

        size--;
        return removedItem;
    }

    public int dequeueRear() {
        if (isEmpty()) {
            System.out.println("Deque is empty. Cannot dequeue from the rear.");
            return -1;
        }

        int removedItem = queue[rear];
        if (front == rear) {
            front = rear = -1;
        } else if (rear == 0) {
            rear = capacity - 1;
        } else {
            rear--;
        }

        size--;
        return removedItem;
    }

    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Deque is empty.");
            return;
        }

        System.out.print("Deque: ");
        int current = front;
        for (int i = 0; i < size; i++) {
            System.out.print(queue[current] + " ");
            current = (current + 1) % capacity;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int capacity = 5;
        DoubleEndedQueue doubleEndedQueue = new DoubleEndedQueue(capacity);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Enqueue Front");
            System.out.println("2. Enqueue Rear");
            System.out.println("3. Dequeue Front");
            System.out.println("4. Dequeue Rear");
            System.out.println("5. Print Deque");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter the item to enqueue at the front:");
                    int frontItem = scanner.nextInt();
                    doubleEndedQueue.enqueueFront(frontItem);
                    break;
                case 2:
                    System.out.println("Enter the item to enqueue at the rear:");
                    int rearItem = scanner.nextInt();
                    doubleEndedQueue.enqueueRear(rearItem);
                    break;
                case 3:
                    int dequeuedFrontItem = doubleEndedQueue.dequeueFront();
                    if (dequeuedFrontItem != -1) {
                        System.out.println("Dequeued item from the front: " + dequeuedFrontItem);
                    }
                    break;
                case 4:
                    int dequeuedRearItem = doubleEndedQueue.dequeueRear();
                    if (dequeuedRearItem != -1) {
                        System.out.println("Dequeued item from the rear: " + dequeuedRearItem);
                    }
                    break;
                case 5:
                    doubleEndedQueue.printQueue();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }
}
