package lessonTwo;


public class Main {
    public static void main(String[] args) {
        MyList<String> one = new DynamicList();
        one.add("one");
        one.add("two");
        one.add("three");
        System.out.println(one+" size: "+ one.length());
        one.delete(1);
        System.out.println(one+" size: "+ one.length());
        one.insert(1,"four");
        System.out.println(one+" size: "+ one.length());
        one.set(0,"five");
        System.out.println(one+" size: "+ one.length());
        System.out.println("DynamicList foreach: ");
        for (String s: one) {
            System.out.print(s+" ");
        }
        System.out.println("\n--------------------");

        MyList<String> two = new TwoLinkedList<>();
        two.add("ONE");
        two.add("TWO");
        two.add("THREE");
        System.out.println(two+" size: "+ two.length());
        two.delete(1);
        System.out.println(two+" size: "+ two.length());
        two.add("FOUR");
        two.set(0,"FIVE");
        System.out.println(two+" size: "+ two.length());
        two.insert(1,"SIX");
        System.out.println(two+" size: "+ two.length());
        for (String s:two) {
            System.out.println(s);
        }
    }

}
