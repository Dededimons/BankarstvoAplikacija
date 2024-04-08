public class Main {
    public static void main(String[] args) {
        if (Login.login()) {
            Menu.displayMenu();
        } else {
            System.out.println("Izlazak");
        }
    }
}