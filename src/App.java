import java.util.Base64;
import java.nio.ByteBuffer;

public class App {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Nenhuma entrada Base64 foi fornecida.");
        }

        String base64Input = args[0];

        try {
            byte[] data = Base64.getDecoder().decode(base64Input);

            if (data.length < 38) {
                System.err.println("Erro: A entrada Base64 não contém bytes suficientes.");
                return;
            }

            int qtRegistro = data[0] & 0xFF;
            int nivelBateria = data[1] & 0xFF;


            int relojoaria01_1 = ByteBuffer.wrap(data, 2, 4).getInt();
            int relojoaria02_1 = ByteBuffer.wrap(data, 6, 4).getInt();

            int[] consumos = new int[10];
            for (int i = 0; i < 10; i++) {
                consumos[i] = data[10 + i] & 0xFF;
            }

            int relojoaria01_2 = ByteBuffer.wrap(data, 20, 4).getInt();
            int relojoaria02_2 = ByteBuffer.wrap(data, 24, 4).getInt();

            int[] consumos2 = new int[10];
            for (int i = 0; i < 10; i++) {
                consumos2[i] = data[28 + i] & 0xFF;
            }

            System.out.println("<QT de registro>: " + qtRegistro);
            System.out.println("<nível da bateria>: " + nivelBateria);
            System.out.println("<relojoaria 01 H6>: " + relojoaria01_1);
            System.out.println("<relojoaria 02 H6>: " + relojoaria02_1);

            for (int i = 0; i < 10; i++) {
                System.out.println("<consumo (RL0" + ((i % 2) + 1) + ")>: " + consumos[i]);
            }

            System.out.println("<relojoaria 01 H6>: " + relojoaria01_2);
            System.out.println("<relojoaria 02 H6>: " + relojoaria02_2);

            for (int i = 0; i < 10; i++) {
                System.out.println("<consumo (RL0" + ((i % 2) + 1) + ")>: " + consumos2[i]);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: Entrada Base64 inválida.");
        }
    }
}
