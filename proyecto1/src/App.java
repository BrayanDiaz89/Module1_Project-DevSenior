import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    private static String nombreEstudiante = "N/A";
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
                4. Eliminar datos de estudiante actual.
                0. Salir.
                Escoge una opción por favor:  """;
        int option = -1;
        while (option != 0) {
            try {
                System.out.print(menu);
                option = keyboard.nextInt();
                keyboard.nextLine();
                switch (option) {
                    case 1:
                        confirmarEstudianteRegistrado(keyboard);
                        break;
                    case 2:
                        System.out.println(mostrarDatosEstudianteActual()); 
                        break;
                    case 3:
                        String promedioDeEstudianteEs = seHaRegistradoUnEstudiante(nombreEstudiante) ? String.format("El promedio de notas del estudiante %s, es: %.2f",
                                                        nombreEstudiante,
                                                        calcularPromedio(notaOne, notaTwo, notaThree))
                                                        : "No se ha registrado a ningún estudiante todavía, registra a uno para continuar."; 
                        System.out.println(promedioDeEstudianteEs); 
                        break;
                    case 4:
                        if(seHaRegistradoUnEstudiante(nombreEstudiante)){
                            System.out.printf("¿Estás seguro de eliminar los datos del estudiante %s? (S=si | N=no): ", nombreEstudiante);
                            String convertDecision = validarCaracterContieneSorN(keyboard);
                            if(convertDecision.equals("s")){
                                eliminarDatos();
                                System.out.println("Datos del estudiante eliminados correctamente.");
                            }
                            System.out.println("Regresando al menú principal...");
                            break;
                        }
                        System.out.println("No ha registrado a un estudiante aún. No es posible eliminar.");
                        break;
                    case 0:
                        System.out.println("\nGracias por tú visita, vuelve pronto.");
                        System.out.println("Cerrando el programa...");
                        break;        
                    default:
                        System.out.println("Opción ingresada no es válida. Escoge una opción del menú, por favor.\n");
                        keyboard.nextLine();  
                        break;
                }
            } catch(InputMismatchException ex){
                System.out.println("No puedes ingresar caracteres diferentes a números.");
                if(seHaRegistradoUnEstudiante(nombreEstudiante)){
                    eliminarDatos();
                    keyboard.nextLine();
                }           
            }
        }
    }

        private static void registrarEstudiante(Scanner keyboard){
                System.out.print("Ingrese el nombre del estudiante: ");
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

                System.out.printf("El registro del estudiante | %s | fue exitoso.\nSus datos son: \n", nombreEstudiante);
                String estudiante = mostrarDatosEstudianteActual();
                System.out.println(estudiante);
        }

        private static boolean validarNombre(String nombre){
            return !(nombre.isBlank()) ? true : false;
        }
    
        private static double validarNota(Scanner keyboard, int consecutivo){
            double nota = -1;
            boolean entradaEsValida = false;
            while (!entradaEsValida) {
                System.out.printf("Digite la nota %d: ", consecutivo);
                try{
                    nota = keyboard.nextDouble();
                    if (!(nota > 0 && nota <= 100)) {
                        System.out.printf("La nota %d, no es válida, ingresela nuevamente.", consecutivo);
                    }
                    entradaEsValida = true;
                } catch(InputMismatchException ex){
                    System.out.println("Debes ingresar un valor numérico. Nota no válida.");
                    keyboard.nextLine();
                }
            }
            return nota;
        }

        private static String mostrarDatosEstudianteActual(){
            return seHaRegistradoUnEstudiante(nombreEstudiante) ? String.format("""
                Estudiante: %s
                Nota 1: %.2f
                Nota 2: %.2f
                Nota 3: %.2f
                Promedio: %.2f
                """, nombreEstudiante, notaOne, notaTwo, notaThree, calcularPromedio(notaOne, notaTwo, notaThree))
                : "No se ha registrado a ningún estudiante todavía, registra a uno para continuar.";
        }

        private static double calcularPromedio(double nota1, double nota2, double nota3){
            return (nota1 + nota2 + nota3) / 3;
        }

        private static void confirmarEstudianteRegistrado(Scanner keyboard){
            if(seHaRegistradoUnEstudiante(nombreEstudiante)){
                System.out.print("Ya existe un estudiante, desea sobreescribirlo (S=si N=no): ");
                String decisionUser = validarCaracterContieneSorN(keyboard);
                if (decisionUser.toLowerCase().equals("s")){
                    registrarEstudiante(keyboard);
                }
                System.out.println("Regresando al menú principal...");
                return;
            }
            registrarEstudiante(keyboard);
        }

        private static boolean seHaRegistradoUnEstudiante(String nombre){
            return !nombre.equals("N/A");
        }

        private static void eliminarDatos(){
            nombreEstudiante = "N/A";
        }

        private static String validarCaracterContieneSorN(Scanner keyboard){
            boolean characterEsValido = false;
            String convertirCharacter = "";
            while (!characterEsValido) {
                char character = keyboard.next().charAt(0); 
                if(!Character.isLetter(character)){
                    System.out.print("Caracter no válido. Debe ingresar una letra. Intente nuevamente: ");
                    continue;
                }
                convertirCharacter = String.valueOf(character).toLowerCase();
                if(convertirCharacter.equals("s") || convertirCharacter.equals("n")){
                    characterEsValido = true;
                } else{
                    System.out.print("Ha ingresado un caracter diferente a S o N, por favor ingreselo de nuevo: ");
                }
            }
            keyboard.nextLine();
            return convertirCharacter;            
        } 
    }

