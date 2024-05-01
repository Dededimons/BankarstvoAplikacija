public class stanjeRacuna {
    public static void checkStanje() {

        String ime = Login.getIme();
        String prezime = Login.getPrezime();
        String iban = Login.getIBAN();
        double iznos = Login.getIznos();


        System.out.println("-- Stanje racuna --");
        System.out.println("Ime: " + ime);
        System.out.println("Prezime: " + prezime);
        System.out.println("IBAN: " + iban);
        System.out.println("Iznos: " + iznos );
    }
}