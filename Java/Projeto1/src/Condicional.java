public class Condicional {
    public static void main(String[] args) {
        int anoDeLancamento = 2022;
        boolean inclidoNoPlano = true;
        double notaDoFilme = 8.1;
        String tipoPlano = "gratis 3 meses";

        if (anoDeLancamento >= 2022){
            System.out.println("Lançamento que os clientes estão curtindo!");
        } else {
            System.out.println("Filme retrô que vale a pena assistir!");
        }

        if (tipoPlano.equals("plus")) {
            System.out.println("Você pode assistir tudo!");
        } else {
            System.out.println("Você não tem o nosso plano basico!");
        }
    }
}
