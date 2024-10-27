import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            String archivoCSV = "vampiros.csv"; // Ruta del archivo CSV
            ArrayList<Clan> clanes = new ArrayList<>(); // Utilizamos un Set para almacenar clanes únicos
            ArrayList<Vampiro> vampiros = new ArrayList<>();  // Lista para almacenar vampiros

            // Leer el archivo CSV
            try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {

                // Se crea el clan "Sin clan"
                Clan sinClan = new ClanComun("Sin clan", 0);
                clanes.add(sinClan);

                String linea;
                boolean primeraLinea = true;
                while ((linea = br.readLine()) != null) {
                    if (primeraLinea) {
                        primeraLinea = false;  
                        continue;
                    }
                    String[] datos = linea.split(",");
                    String nombre = datos[0];
                    int fuerza = Integer.parseInt(datos[1]);
                    int velocidad = Integer.parseInt(datos[2]);
                    int hambre = Integer.parseInt(datos[3]);
                    // String ColorOjos = datos[4];
                    String clanNombre = datos[5];
                    String alas = datos[6].equalsIgnoreCase("si") ? "si" : "no";  // Convertir el texto a una respuesta booleana
                    int años = Integer.parseInt(datos[7]);

                    // Crear vampiro con los datos leídos usando la clase Crear_vampiro
                    Crear_vampiro creador = new Crear_vampiro(nombre, fuerza, velocidad, hambre, clanNombre, alas);

                    // Obtener el vampiro creado y añadirlo a la lista si no es null
                    Vampiro vampiro = creador.obtenerVampiroCreado();
                    if (vampiro != null) {
                        vampiros.add(vampiro);  // Añadir vampiro a la lista
                       
                        // Verifica si el clan ya existe
                        Clan clanExistente = null;
                        for (Clan existente : clanes) {
                            if (existente.getNombreClan().equalsIgnoreCase(clanNombre)) {  // Comparar nombres de clanes (ignorar mayúsculas)
                                clanExistente = existente;
                                break;
                            }
                        }
                        // Si el clan existe, admite al vampiro en el clan existente
                        if (clanExistente != null) {
                            clanExistente.IniciarClanes(vampiro);
                        } else {
                            // Si el clan no existe, añade un nuevo clan y admite al vampiro
                            ClanComun nuevoClan = new ClanComun(clanNombre, años);
                            clanes.add(nuevoClan);
                            nuevoClan.IniciarClanes(vampiro);
                        }
                    }
                }

                // Menú interactivo
                Scanner sc = new Scanner(System.in);
                boolean ciclo = true;

                while (ciclo) {
                    System.out.println("Sistema gestor de crepusculo: \n1. Mostrar clanes\n2. Crear Vampiro\n3. Listar vampiros\n4. Admitir vampiro a un clan\n5. Expulsar vampiro de un clan\n6. Comer\n7. Obtener vampiro más apto\n8. Usar habilidad especial de un vampiro\n9. Crear nuevo clan\n10. Borrar clan\n11. Salir");
                    int opcion = sc.nextInt();
                    sc.nextLine();  
                    switch (opcion) {
                        case 1:
                            // Mostrar clanes
                            System.out.println("Clanes:");
                            for (Clan clan : clanes) {
                                System.out.println("- " + clan);
                            }
                            break;
                        case 2:
                            // Crear nuevo vampiro manualmente
                            System.out.println("Ingresa el nombre del vampiro:");
                            String nombre = sc.nextLine();
                            System.out.println("Ingresa la fuerza del vampiro (entre 1 y 100):");
                            int fuerza = sc.nextInt();
                            System.out.println("Ingresa la velocidad del vampiro:");
                            int velocidad = sc.nextInt();
                            System.out.println("Ingresa el hambre del vampiro:");
                            int hambre = sc.nextInt();
                            sc.nextLine();  
                            System.out.println("¿Tiene alas? (si/no):");
                            String alas = sc.nextLine();
                            Crear_vampiro creador = new Crear_vampiro(nombre, fuerza, velocidad, hambre, alas);
                            Vampiro nuevoVampiro = creador.obtenerVampiroCreado();
                            if (nuevoVampiro != null) {
                                vampiros.add(nuevoVampiro);
                                sinClan.admitirVampiro(nuevoVampiro);
                                System.out.println("Vampiro creado y añadido a la lista.");
                            } else {
                                System.out.println("Error al crear el vampiro. Verifica los valores ingresados.");
                            }
                            break;
                        case 3:
                            // Listar vampiros
                            for(Clan clan:clanes){
                                clan.listarVampiros();
                            }
                            break;
                        case 4:
                            System.out.println("Opcion 4");
                            // Código para admitir vampiro a clan

                            System.out.println("Ingrese el nombre del clan: ");
                            String nombreClan = sc.nextLine();

                            Clan clanExistente = null;
                            for (Clan c : clanes) {
                                if (c.getNombreClan().equals(nombreClan)) {
                                    clanExistente = c;
                                    break;
                                }
                            }
                            if (clanExistente == null) {
                                System.out.println("El clan indicado no existe");
                                break;
                            }

                            System.out.println("Ingrese el nombre del vampiro: ");
                            String nombreVampiro = sc.nextLine();

                            Vampiro vampiroExistente = null;
                            for (Vampiro v : vampiros) {
                                if (v.getNombre().equals(nombreVampiro)) {
                                    vampiroExistente = v;
                                    break;
                                }
                            }
                            if (vampiroExistente == null) {
                                System.out.println("El vampiro indicado no existe");
                                break;
                            } else {
                                clanExistente.admitirVampiro(vampiroExistente);
                            }

                            break;

                        // Continuación del código para otras opciones del menú

                        case 11:
                            System.out.println("Guardando los vampiros y saliendo del programa...");
                        
                            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCSV))) {
                                bw.write("Nombre,Fuerza,Velocidad,Hambre,ColorOjos,Clan,TieneAlas,Antiguedad del clan \n");
                        
                                for (Vampiro vamp : vampiros) {
                                    bw.write(vamp.nombre + "," 
                                            + vamp.fuerza + "," 
                                            + vamp.velocidad + "," 
                                            + vamp.hambre + "," 
                                            + vamp.ColorOjos + "," 
                                            + vamp.Clan + "," 
                                            + (vamp.alas.equalsIgnoreCase("si") ? "si" : "no") + ","
                                             );
                                             for (Clan clan : clanes) {
                                                if (clan.getNombreClan().equalsIgnoreCase(vamp.Clan)) {
                                                    bw.write( clan.getEdad() + "\n");
                                                }
                                             }
                                            }
                                
                            } catch (IOException e) {
                                System.out.println("Error al guardar los vampiros en el archivo CSV: " + e.getMessage());
                            }
                        
                            sc.close();
                            ciclo = false;
                            break;
                        
                        default:
                            System.out.println("Opción no válida");
                    }
                }
            } catch (IOException e) {
                System.out.println("Error al leer el archivo CSV: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
        }
    }
}
