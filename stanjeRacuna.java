public class stanjeRacuna {
    public static void checkBalance() {
        System.out.println("-- Stanje racuna --");
        System.out.println("Racun: " + Login.getRacun());
        System.out.println("Stanje: " + Login.getStanje() + " " + Login.getValuta());
    }
}