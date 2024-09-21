package com.raimundo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String options = """

                ========= MENU ===========

                1- Pesos Chilenos a Dolares
                2- Dolares a Pesos Chilenos
                3- Pesos Colombianos a Dolares
                4- Dolares a Pesos Colombianos
                5- Peso Argentino a Dolares
                6- Dolares a Peso Argentino
                0- Salir
                """;
        String chooseOption = "Elija una opcion valida: ";
        String chooseAmount = "Ingrese el valor que desee convertir?";
        String invalidOption = "Opcion invalida, intente de nuevo";
        String badInputMessage = "Input mal formado, porfavor utilice ',' para los decimales y verifique que es un numero";

        int selectedOption = 0;
        double amount = 0;

        do {

            System.out.println(options);
            System.out.println(chooseOption);

            try {

                selectedOption = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.nextLine();
                selectedOption = 9;
            }

            if (selectedOption > 0 && selectedOption <= 6) {
                System.out.println(chooseAmount);
                try {
                    amount = sc.nextDouble();
                } catch (Exception e) {
                    sc.nextLine();
                    System.out.println(badInputMessage);
                    selectedOption = 9;
                }
            }

            switch (selectedOption) {
                case 1:
                    CurrencyConversionInfoDTO cci1 = APIDriver.convert("CLP", "USD");
                    System.out.println(formartAnswer(cci1.base_code, cci1.target_code, amount, cci1.conversion_rate));
                    break;
                case 2:
                    CurrencyConversionInfoDTO cci2 = APIDriver.convert("USD", "CLP");
                    System.out.println(formartAnswer(cci2.base_code, cci2.target_code, amount, cci2.conversion_rate));
                    break;
                case 3:
                    CurrencyConversionInfoDTO cci3 = APIDriver.convert("COP", "USD");
                    System.out.println(formartAnswer(cci3.base_code, cci3.target_code, amount, cci3.conversion_rate));
                    break;
                case 4:
                    CurrencyConversionInfoDTO cci4 = APIDriver.convert("USD", "COP");
                    System.out.println(formartAnswer(cci4.base_code, cci4.target_code, amount, cci4.conversion_rate));
                    break;
                case 5:
                    CurrencyConversionInfoDTO cc5 = APIDriver.convert("ARS", "USD");
                    System.out.println(formartAnswer(cc5.base_code, cc5.target_code, amount, cc5.conversion_rate));
                    break;
                case 6:
                    CurrencyConversionInfoDTO cci6 = APIDriver.convert("USD", "ARS");
                    System.out.println(formartAnswer(cci6.base_code, cci6.target_code, amount, cci6.conversion_rate));
                    break;
                case 0:
                    selectedOption = 0;
                    System.out.println("Gracias por usar nuestro servicio de conversion de moneda");
                    break;
                default:
                    System.out.println(invalidOption);
                    break;
            }
        } while (selectedOption != 0);

        sc.close();

    }

    private static String formartAnswer(String currency1, String currency2, double amount, double conversion_rate) {
        return "El Valor " + amount + " [" + currency1 + "] " + "corresponde al valor final de =>> "
                + (float) (conversion_rate * amount)
                + " [" + currency2 + "]";
    }

}