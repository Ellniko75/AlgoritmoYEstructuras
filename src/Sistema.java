import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    private static String nombreEmpresa;

    public static Scanner Keyboard = new Scanner(System.in);

    public static void ShowMenu(){
        boolean salir = false;

        if(nombreEmpresa==null){
            String nombre = Sistema.RequestString("Ingrese un nombre para la empresa ");
            nombreEmpresa = nombre;
            ShowMenu();
        }else{
            while (!salir) {
                System.out.println("BIENVENIDO A LA EMPRESA " + nombreEmpresa);
                System.out.println("MENU PRINCIPAL:");
                System.out.println("1. Menu sucursales");
                System.out.println("0. Salir");

                int num = RequestNumber("Eliga una opción: ");
                switch (num) {
                    case 1 -> Sucursal.ShowMenu();
                    case 0 -> salir = true;
                    default -> System.out.println("No es un numero válido");
                }
            }
        }
    }

    public static String RequestString(String msg){
        System.out.print(msg);
        return Sistema.Keyboard.nextLine();
    }
    public static int RequestNumber(String msg){
        int number = 0;
        System.out.print(msg);
        while (true){
            if(Sistema.Keyboard.hasNextInt()){
                number = Sistema.Keyboard.nextInt();
                break;
            }else{
                System.out.println("Error: Debe ingresar un número válido. Intente nuevamente.");
                Sistema.Keyboard.next();
            }
        }
        return number;
    }


}
