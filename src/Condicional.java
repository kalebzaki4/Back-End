public class Condicional {
    public static void main(String[] args) {
        int anoDeLancamento = 2020;
        boolean incluidoNoPlano = false;
        double notaDoFilme = 8.1;
        String tipoPlano = "nornal";


        if (anoDeLancamento >= 2022) {
            System.out.println("Lançamentos que os clientes estão curtindo!");
        } else {
            System.out.println("Seu filme é retro amiguinho, ta véio em");
        }

        if ( incluidoNoPlano == true || tipoPlano.equals("plus") ) {
            System.out.println("Filme liberado");
        } else {
            System.out.println("Deve pagar a locação");
        }
    }
}
