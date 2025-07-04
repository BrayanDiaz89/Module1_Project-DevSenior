import java.util.Scanner;

public class App {

    private static String nombreEstudiante;
    private static double notaOne;
    private static double notaTwo;
    private static double notaThree;

    public static void main(String[] args) {
        var keyboard = new Scanner(System.in);
        mostrarMenu(keyboard);
    }
    
    private static void mostrarMenu(Scanner keyboard){
        String menu = """
                \n.::-> BIENVENIDO AL REGISTRO DE ESTUDIANTES REG_EXCELENT <-::.
                ===============================================================
                1. Registrar datos de un estudiante.
                2. Mostrar datos del estudiante actual.
                3. Calcular el promedio de notas del estudiante actual.
                0. Salir.
                Escoge una opción por favor: """;
        int option = -1;
        while (option != 0) {
            System.out.print(menu);
            option = keyboard.nextInt();
            keyboard.nextLine();
            switch (option) {
                case 1:
                    registrarEstudiante(keyboard);
                    break;
                case 2:
                    System.out.println(mostrarDatosEstudianteActual()); 
                    break;
                case 3:
                    
                    break;
                case 0:
                    System.out.println("Gracias por tú visita, vuelve pronto.");
                    System.out.println("Cerrando el programa...");
                    break;        
                default:
                    System.out.println("Opción ingresada no es válida. Escoge una opción del menú, por favor.\n");
                    break;
            }
        }
    }

        private static void registrarEstudiante(Scanner keyboard){
            System.out.println("Ingrese el nombre del estudiante: ");
            nombreEstudiante = keyboard.nextLine().strip();
            boolean nombreEsValido = validarNombre(nombreEstudiante);
            while(!nombreEsValido){
                System.out.println("El nombre ingresado no es válido, por favor digitelo nuevamente: ");
                nombreEstudiante = keyboard.nextLine().strip();
                nombreEsValido = validarNombre(nombreEstudiante);
            }

            notaOne = validarNota(keyboard, 1);
            notaTwo = validarNota(keyboard, 2);
            notaThree = validarNota(keyboard, 3);
            
            System.out.printf("El registro del estudiante %s fue exitoso.\nSus datos son: \n", nombreEstudiante);
            String estudiante = mostrarDatosEstudianteActual();
            System.out.println(estudiante);
        }

        private static boolean validarNombre(String nombre){
            return !(nombre.isBlank()) ? true : false;
        }
    
        private static double validarNota(Scanner keyboard, int consecutivo){
            System.out.printf("Digite la nota %d: ", consecutivo);
            double nota = keyboard.nextDouble();
            while (!(nota > 0 && nota <= 100)) {
                System.out.printf("La nota %d, no es válida, ingresela nuevamente: ", consecutivo);
                nota = keyboard.nextDouble();
            }
            return nota;
        }

        private static String mostrarDatosEstudianteActual(){
            return String.format("""
                Estudiante:  %s
                Nota 1: %.2f
                Nota 2: %.2f
                Nota 3: %.2f
                """, nombreEstudiante, notaOne, notaTwo, notaThree);
        } 
    }

