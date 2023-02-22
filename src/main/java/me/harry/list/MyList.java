package me.harry.list;

public class MyList<T> {

    private final int DEFAULT_CAPACITY = 1;
    private T[] data;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public MyList() {
        this.data = (T[]) new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
    }


    @SuppressWarnings("unchecked")
    private void checkCapacity() {
        // 1. 사이즈 모자른지 체크 -> 모자르면 2배 확장
        int currentSize = this.size;
        if (this.size >= this.capacity) {
            int newCapacity = currentSize * 2;

            T[] newArr = (T[]) new Object[newCapacity];

            for (int i = 0; i < currentSize; i++) {
                newArr[i] = this.data[i];
            }

            increaseCapacity(newCapacity);
            this.data = newArr;
        }
    }

    private void increaseCapacity(int newCapacity) {
        this.capacity = newCapacity;
    }

    public void add(T data) {
        checkCapacity();

        this.data[this.size] = data;
        increaseSize();
    }

    public T get(int index) {
        return this.data[index];
    }

    private void increaseSize() {
        this.size++;
    }

    public int search(T data) {
        for (int i = 0; i < this.size; i++) {
            if (this.data[i] == data) {
                return i;
            }
        }
        return -1;
    }

    public void remove(T data) {
        int index = search(data);

        if (index < 0) {
            return;
        }

        for (int i = index; i < this.size - 1; i++) {
            this.data[i] = this.data[i + 1];
        }

        this.data[this.size - 1] = null;
        decreaseSize();

    }

    private void decreaseSize() {
        this.size--;
    }

    public static void main(String[] args) {
        MyList<Integer> list = new MyList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        list.remove(2);

        System.out.println(list);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.size; i++) {
            builder.append(this.data[i]);
            builder.append(" ");
        }
        return builder.toString();
    }
}
