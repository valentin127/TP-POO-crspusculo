import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String archivoCSV = "vampiros.csv"; 
        ArrayList<Clan> clanes = new ArrayList<>(); 
        ArrayList<Vampiro> vampiros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            Clan sinClan = new Clan("Sin clan", 0);
            clanes.add(sinClan);
            Clan volturi = new Volturi("Volturi", 60);
            clanes.add(volturi);
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
                String clanNombre = datos[5];
                String alas = datos[6].equalsIgnoreCase("si") ? "si" : "no"; 
                int años = Integer.parseInt(datos[7]);
                Crear_vampiro creador = new Crear_vampiro(nombre, fuerza, velocidad, hambre, clanNombre, alas);
                Vampiro vampiro = creador.obtenerVampiroCreado();
                if (vampiro != null) {
                    vampiros.add(vampiro); 
                    Clan clanExistente = null;
                    for (Clan existente : clanes) {
                        if (existente.getNombreClan().equalsIgnoreCase(clanNombre)) {
                            clanExistente = existente;
                            break;
                        }
                    }
                    if (clanExistente != null) {
                        clanExistente.IniciarClanes(vampiro);
                    } else {
                        Clan nuevoClan = new Clan(clanNombre, años);
                        clanes.add(nuevoClan);
                        nuevoClan.IniciarClanes(vampiro); 
                    }
                }
            }

            Scanner sc = new Scanner(System.in);
            boolean ciclo = true;

            while (ciclo) {
                try {
                    System.out.println("\nSistema gestor de crepusculo: \n1. Mostrar clanes\n2. Crear Vampiro\n3. Listar vampiros\n4. Admitir vampiro a un clan\n5. Expulsar vampiro de un clan\n6. Comer\n7. Obtener vampiro más apto\n8. Usar habilidad especial de un vampiro\n9. Crear nuevo clan\n10. Borrar clan\n11. Salir");
                    int opcion = sc.nextInt();
                    sc.nextLine();  
                    switch (opcion) {
                        case 1:
                            System.out.println("\nClanes:");
                            for (Clan clan : clanes) {
                                System.out.println("- " + clan);
                            }
                            break;

                        case 2:
                            String nombre;
                            int fuerza = 0;
                            int velocidad = 0;
                            int hambre = 0;
                            String alas;
                            boolean continuarCreacion = true;

                            while (true) {
                                System.out.println("Ingrese el nombre del vampiro (o escribe 'cancelar' para cancelar):");
                                nombre = sc.nextLine();
                            
                                if (nombre.equalsIgnoreCase("cancelar")) {
                                    System.out.println("Creación de vampiro cancelada.");
                                    continuarCreacion = false;
                                    break;
                                }
                            
                                boolean vampiroExiste = false;
                                for (Vampiro vampiro : vampiros) {
                                    if (vampiro.getNombre().equalsIgnoreCase(nombre)) {
                                        vampiroExiste = true;
                                        break;
                                    }
                                }
                                
                                if(nombre.trim().isEmpty()){
                                    System.out.println("Entrada inválida.");
                                } else if (vampiroExiste) {
                                    System.out.println("Ya existe un vampiro con el nombre " + nombre + ". Por favor, ingrese un nombre diferente.");
                                } else {
                                    break;
                                }
                            }

                            if (!continuarCreacion) break;
                            while (true) {
                                System.out.println("Ingrese la fuerza del vampiro (entre 1 y 100) (o escribe 'cancelar' para cancelar):");
                                String input = sc.nextLine();
                                
                                if (input.equalsIgnoreCase("cancelar")) {
                                    System.out.println("Creación de vampiro cancelada.");
                                    continuarCreacion = false;
                                    break;
                                }
                                
                                try {
                                    fuerza = Integer.parseInt(input);
                                    if (fuerza < 1 || fuerza > 100) {
                                        System.out.println("La fuerza debe estar entre 1 y 100. Inténtalo de nuevo.");
                                        continue;
                                    }
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("Entrada inválida. Debe ser un número entero.");
                                }
                            }

                            if (!continuarCreacion) break;
                            while (true) {
                                System.out.println("Ingrese la velocidad del vampiro (entre 1 y 100) (o escribe 'cancelar' para cancelar):");
                                String input = sc.nextLine();
                                
                                if (input.equalsIgnoreCase("cancelar")) {
                                    System.out.println("Creación de vampiro cancelada.");
                                    continuarCreacion = false;
                                    break;
                                }
                                
                                try {
                                    velocidad = Integer.parseInt(input);
                                    if (velocidad < 1 || velocidad > 100) {
                                        System.out.println("La velocidad debe estar entre 1 y 100. Inténtalo de nuevo.");
                                        continue;
                                    }
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("Entrada inválida. Debe ser un número entero.");
                                }
                            }

                            if (!continuarCreacion) break;
                            while (true) {
                                System.out.println("Ingrese el hambre del vampiro (entre 1 y 100) (o escribe 'cancelar' para cancelar):");
                                String input = sc.nextLine();
                                
                                if (input.equalsIgnoreCase("cancelar")) {
                                    System.out.println("Creación de vampiro cancelada.");
                                    continuarCreacion = false;
                                    break;
                                }
                                
                                try {
                                    hambre = Integer.parseInt(input);
                                    if (hambre < 1 || hambre > 100) {
                                        System.out.println("El hambre debe estar entre 1 y 100. Inténtalo de nuevo.");
                                        continue;
                                    }
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("Entrada inválida. Debe ser un número entero.");
                                }
                            }
                            if (!continuarCreacion) break;
                            while (true) {
                                System.out.println("¿Tiene alas? (si/no) (o escribe 'cancelar' para cancelar):");
                                alas = sc.nextLine();
                                if (alas.equalsIgnoreCase("cancelar")) {
                                    System.out.println("Creación de vampiro cancelada.");
                                    continuarCreacion = false;
                                    break;
                                }
                                if (alas.equalsIgnoreCase("si") || alas.equalsIgnoreCase("no")) {
                                    break;
                                } else {
                                    System.out.println("Entrada inválida. Por favor ingrese 'si' o 'no'.");
                                }
                            }

                            if (!continuarCreacion) break;

                            Crear_vampiro creador = new Crear_vampiro(nombre, fuerza, velocidad, hambre, alas);
                            Vampiro nuevoVampiro = creador.obtenerVampiroCreado();

                            if (nuevoVampiro != null) {
                                vampiros.add(nuevoVampiro);
                                sinClan.admitirVampiro(nuevoVampiro);
                                System.out.println("Vampiro creado y añadido a la lista.");
                            } else {
                                System.out.println("Error al crear el vampiro. Verifique los valores ingresados.");
                            }
                            break;

                        case 3:
                            for(Clan clan:clanes){
                                clan.listarVampiros();
                            }
                            break;

                        case 4:
                            System.out.println("Opcion 4 - Admitir vampiro a un clan");
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
                            if (clanExistente.getNombreClan().equals("Sin clan")) {
                                System.out.println("No se puede admitir a \"Sin clan\", utilice la funcion de expulsar");
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
                            } else if(!vampiroExistente.getClan().equals("Sin clan")){
                                System.out.println("El vampiro indicado ya se encuentra en un clan");
                            } else {
                                clanExistente.admitirVampiro(vampiroExistente);
                                sinClan.expulsarVampiro(vampiroExistente, sinClan);
                            }
                            break;

                        case 5:
                            System.out.println("Opción 5 - Expulsar vampiro de un clan\n");
                            System.out.println("Ingrese el nombre del clan: ");
                            String nombreClan2 = sc.nextLine();

                            Clan clanExistente2 = null;
                            for (Clan c : clanes) {
                                if (c.getNombreClan().equals(nombreClan2)) {
                                    clanExistente2 = c;
                                    break;
                                }
                            }
                            if (clanExistente2 == null) {
                                System.out.println("El clan indicado no existe");
                                break;
                            }
                            if (clanExistente2.getNombreClan().equals("Sin clan")) {
                                System.out.println("No se puede expulsar si no tiene clan");
                                break;
                            }
                            if (clanExistente2 instanceof Volturi) {
                                while(true){
                                    System.out.println("Ingrese la influencia política del clan Volturi: (entre 1 y 100)");
                                    try {
                                        int influenciaPolitica = sc.nextInt();
                                        sc.nextLine();
                                        if (influenciaPolitica < 1 || influenciaPolitica > 100) {
                                            System.out.println("La influencia política debe estar entre 1 y 100.");
                                            continue;
                                        }
                                        Volturi volturiClan = (Volturi) clanExistente2;
                                        volturiClan.setInfluenciaPolitica(influenciaPolitica);
                                        break;
                                    } catch (Exception e) {
                                        System.out.println("Entrada inválida. Debe ser un número entero entre 1 y 100.");
                                        sc.next();
                                    }
                                }
                            }
                        
                            System.out.println("Ingrese el nombre del vampiro: ");
                            String nombreVampiro2 = sc.nextLine();

                            Vampiro vampiroExistente2 = null;
                            for (Vampiro v : vampiros) {
                                if (v.getNombre().equals(nombreVampiro2)) {
                                    vampiroExistente2 = v;
                                    break;
                                }
                            }
                            if (vampiroExistente2 == null) {
                                System.out.println("El vampiro indicado no existe");
                                break;
                            }
                            clanExistente2.expulsarVampiro(vampiroExistente2, sinClan);
                            break;

                        case 6:
                            System.out.println("Opción 6 - Comer");
                            boolean continuarComer = true;
                            
                            int vampiroSeleccionado = -1;
                            while (true) {
                                for (int i = 0; i < vampiros.size(); i++) {
                                    Vampiro vamp = vampiros.get(i);
                                    System.out.println((i + 1) + ". Vampiro: " + vamp.getNombre() + " (Hambre actual: " + vamp.getHambre() + ")");
                                }
                                System.out.println("Seleccione el número del vampiro que va a comer (o escribe 'cancelar' para cancelar):");
                                String input = sc.nextLine();
                            
                                if (input.equalsIgnoreCase("cancelar")) {
                                    System.out.println("Operación cancelada.");
                                    continuarComer = false;
                                    break;
                                }
                            
                                try {
                                    vampiroSeleccionado = Integer.parseInt(input) - 1;
                            
                                    if (vampiroSeleccionado >= 0 && vampiroSeleccionado < vampiros.size()) {
                                        break;
                                    } else {
                                        System.out.println("Selección inválida. Por favor, elija un número de la lista.");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Entrada inválida. Debe ser un número entero.");
                                }
                            }

                            if(continuarComer == false) break;
                            
                            Vampiro vampiroQueCome = vampiros.get(vampiroSeleccionado);
                            if (vampiroQueCome instanceof RecienConvertido) {
                                System.out.println(vampiroQueCome.getNombre() + " es un vampiro tipo RecienConvertido y solo puede comer personas.");
                                vampiroQueCome.comer(); 
                            } 
                            else if (vampiroQueCome instanceof Maduro) {
                                System.out.println(vampiroQueCome.getNombre() + " es un vampiro tipo Maduro, puede comer tanto animales como personas.");
                            
                                if (vampiroQueCome instanceof ComedorDeAnimales) {
                                    ComedorDeAnimales vampiroMaduro = (ComedorDeAnimales) vampiroQueCome;
                                    System.out.println("¿El vampiro desea comer un animal? (si/no):");
                                    String respuesta = sc.nextLine();
                                    if (respuesta.equalsIgnoreCase("si")) {
                                        vampiroMaduro.comerAnimal();
                                    } 
                                    else {
                                        vampiroQueCome.comer();
                                    }
                                }
                            } 
                            else if (vampiroQueCome instanceof Adulto) {
                                System.out.println(vampiroQueCome.getNombre() + " es un vampiro tipo Adulto, puede comer lo que desee.");
                            
                                if (vampiroQueCome instanceof ComedorDeAnimales) {
                                    ComedorDeAnimales vampiroAdulto = (ComedorDeAnimales) vampiroQueCome;
                                    System.out.println("¿El vampiro desea comer un animal? (si/no):");
                                    String eleccion = sc.nextLine();
                                    if (eleccion.equalsIgnoreCase("si")) {
                                        vampiroAdulto.comerAnimal();  
                                    } 
                                    else {
                                        vampiroQueCome.comer();  
                                    }
                                }
                            }
                            
                            break;
                    
                        case 7:
                            System.out.println("Opción 7 - Vampiro más apto (mayor fuerza y velocidad pero menor hambre): ");
                
                            Vampiro vampiroMasApto = null;
                            int mayorAptitud = 0; 
                            for (Vampiro vamp : vampiros) {
                                int aptitudActual = vamp.fuerza + vamp.velocidad - vamp.hambre;
                                if (aptitudActual > mayorAptitud) {
                                    mayorAptitud = aptitudActual;
                                    vampiroMasApto = vamp;
                                }
                            }
                            if (vampiroMasApto != null) {
                                System.out.println("El vampiro más apto es " + vampiroMasApto.nombre + ", con fuerza " + vampiroMasApto.fuerza + " y velocidad " + vampiroMasApto.velocidad +  ". Posee un hambre de "+ vampiroMasApto.hambre);
                            } else {
                                System.out.println("No hay vampiros en la lista.");
                            }
                            break;

                            case 8:
                            System.out.println("Opción 8 - Usar habilidad especial");
                            boolean continuarHabilidad = true;
                        
                            int vampiroSeleccionado1 = -1;
                            while (true) {
                                for (int i = 0; i < vampiros.size(); i++) {
                                    Vampiro vamp = vampiros.get(i);
                                    System.out.println((i + 1) + ". Vampiro: " + vamp.getNombre() + " (Hambre actual: " + vamp.getHambre() + ")");
                                }
                                System.out.println("Seleccione el número del vampiro que va a usar su habilidad (o escribe 'cancelar' para cancelar):");
                                String input = sc.nextLine();
                            
                                if (input.equalsIgnoreCase("cancelar")) {
                                    System.out.println("Operación cancelada.");
                                    continuarHabilidad = false;
                                    break;
                                }
                            
                                try {
                                    vampiroSeleccionado1 = Integer.parseInt(input) - 1;
                            
                                    if (vampiroSeleccionado1 >= 0 && vampiroSeleccionado1 < vampiros.size()) {
                                        break;
                                    } else {
                                        System.out.println("Selección inválida. Por favor, elija un número de la lista.");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Entrada inválida. Debe ser un número entero.");
                                }
                            }
                        
                            if (continuarHabilidad == false) break;
                        
                            Vampiro vampiroQueaactiva = vampiros.get(vampiroSeleccionado1);
                            if (vampiroQueaactiva instanceof RecienConvertido) {
                                System.out.println(vampiroQueaactiva.getNombre() + " es un vampiro tipo RecienConvertido y va a usar su habilidad.");
                                vampiroQueaactiva.habilidadEspecial();
                            } 
                            else if (vampiroQueaactiva instanceof Maduro) {
                                System.out.println(vampiroQueaactiva.getNombre() + " es un vampiro tipo Maduro y va a usar su habilidad.");
                                vampiroQueaactiva.habilidadEspecial();
                            } 
                            else if (vampiroQueaactiva instanceof Adulto) {
                                System.out.println(vampiroQueaactiva.getNombre() + " es un vampiro tipo Adulto y va a usar su habilidad.");
                                vampiroQueaactiva.habilidadEspecial();
                            }
                            break;
                        

                        case 9:
                            String nombreClanNuevo;
                            while(true){
                                System.out.println("Ingrese el nombre del nuevo clan comun (Ten en cuenta que debes agregarle vampiros si quieres guardarlo):");
                                nombreClanNuevo = sc.nextLine();
                                
                                boolean existeClan = false;
                                for (Clan existente : clanes) {
                                    if (existente.getNombreClan().equalsIgnoreCase(nombreClanNuevo)) {
                                        existeClan = true;
                                        break;
                                    }
                                }
                                
                                if (existeClan) {
                                    System.out.println("Ya existe un clan con ese nombre");
                                } else if(nombreClanNuevo.trim().isEmpty()){
                                    System.out.println("Entrada inválida.");
                                } else {
                                    break;
                                }
                            
                            }

                            int años = -1;
                            while (true) {
                                System.out.println("Ingrese los años de antiguedad de ese clan:");
                                String input = sc.nextLine();
                            
                                try {
                                    años = Integer.parseInt(input);
                                    if (años >= 0) {
                                        break;
                                    } else {
                                        System.out.println("Los años de antiguedad no pueden ser negativos.");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Entrada inválida. Debe ser un número entero positivo.");
                                }
                            }
                            
                            Clan nuevoClan = new Clan(nombreClanNuevo, años);
                            clanes.add(nuevoClan);
                            System.out.println("Clan '" + nombreClanNuevo + "' creado exitosamente.");
                            break;
                        
                        case 10:
                            System.out.println("Ingrese el nombre del clan a eliminar:");
                            String nombreClanAEliminar = sc.nextLine();
                            if (nombreClanAEliminar.equalsIgnoreCase("Sin clan")) {
                                System.out.println("No se puede eliminar \"Sin clan\"");
                                break;
                            }
                            
                            Clan clanAEliminar = null;
                            for (Clan clan : clanes) {
                                if (clan.getNombreClan().equalsIgnoreCase(nombreClanAEliminar)) {
                                    clanAEliminar = clan;
                                    break;
                                }
                            }

                            if (clanAEliminar != null) {
                                if (clanAEliminar instanceof Volturi) {
                                    System.out.println("No se puede eliminar \"Volturi\"");
                                    break;
                                }
                                List<Vampiro> vampirosAExpulsar = clanAEliminar.getVampiros();
                                for (Vampiro vampiro : vampirosAExpulsar) {
                                    sinClan.admitirVampiro(vampiro);
                                }
                                clanes.remove(clanAEliminar);
                                System.out.println("El clan '" + clanAEliminar + "' ha sido eliminado");
                            } else {
                                System.out.println("No se encontró un clan con el nombre '" + nombreClanAEliminar + "'.");
                            }
                            break;

                        case 11:
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
                                                    if (clan instanceof Volturi) {
                                                        bw.write("3000\n");
                                                    } else {
                                                        bw.write(clan.getAntiguedadDelClan() + "\n");
                                                    }
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
                            System.out.println("Opción inválida. Por favor, elija un número de la lista.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Opción inválida. Por favor, elija un número de la lista.");
                    sc.nextLine();
                } catch (Exception e) {
                    System.out.println("Se produjo un error inesperado: " + e.getClass().getName() + " - " + e.getMessage());
                    sc.nextLine();
                }
                
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        } catch (Exception e) {
        System.out.println("Se produjo un error inesperado: " + e.getMessage());
        }
        finally{
            System.out.println("Guardando los vampiros y saliendo del programa...");
        }
    }
}
