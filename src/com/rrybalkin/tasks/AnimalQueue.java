package com.rrybalkin.tasks;

/**
 * Created by Roman Rybalkin
 * 06.10.16
 */
public class AnimalQueue {

    public static void main(String[] args) {
        AnimalQueue animalQueue = new AnimalQueue();
        animalQueue.enqueueCat(new Cat(1));
        animalQueue.enqueueDog(new Dog(1));
        animalQueue.enqueueCat(new Cat(2));

        System.out.println(animalQueue.dequeueDog());
        System.out.println(animalQueue.dequeueCat());
        System.out.println(animalQueue.dequeueAny());
    }

    private Node head;
    private CatNode catHead;
    private DogNode dogHead;

    public void enqueue(Animal animal) {
        Node node;
        if (animal instanceof Cat) {
            node = enqueueCat((Cat) animal);
        } else {
            node = enqueueDog((Dog) animal);
        }
        if (head == null) {
            head = node;
        } else {
            head.next = node;
        }
    }

    private Node enqueueCat(Cat cat) {
        CatNode catNode = new CatNode(cat);
        if (catHead == null) {
            catHead = catNode;
        } else {
            catHead.next = catNode;
        }
        return catNode;
    }

    private Node enqueueDog(Dog dog) {
        DogNode dogNode = new DogNode(dog);
        if (dogHead == null) {
            dogHead = dogNode;
        } else {
            dogHead.next = dogNode;
        }
        return dogNode;
    }

    public Animal dequeueAny() {
        if (head == null) {
            return null;
        }
        if (head instanceof CatNode) {
            catHead = (CatNode) catHead.next;
        } else {
            dogHead = (DogNode) dogHead.next;
        }
        Node res = head;
        head = head.next;
        return res.getData();
    }

    public Cat dequeueCat() {
        if (catHead == null) return null;
        CatNode res = catHead;
        catHead = (CatNode) catHead.next;
        return (Cat) res.getData();
    }

    public Dog dequeueDog() {
        if (dogHead == null) return null;
        DogNode res = dogHead;
        dogHead = (DogNode) dogHead.next;
        return (Dog) res.getData();
    }

    abstract class Node {
        protected Node next;
        private Animal data;
        public Node(Animal data) {
            this.data = data;
        }

        public Animal getData() {
            return data;
        }
    }

    class CatNode extends Node {
        public CatNode(Cat data) {
            super(data);
        }
    }
    class DogNode extends Node {
        public DogNode(Dog data) {
            super(data);
        }
    }
}

class Animal {
    protected int id;
    public Animal(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + id + "]";
    }
}

class Cat extends Animal {

    public Cat(int id) {
        super(id);
    }
}

class Dog extends Animal {

    public Dog(int id) {
        super(id);
    }
}
