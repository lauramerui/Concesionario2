/**
 * Esta clase se encarga de:
 * Instanciar un objeto Concesionario.
 * Realizar las validaciones de datos de entrada.
 * Mostrar datos por pantalla.
 */
package Concesionario;

import Util.Validaciones;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Laura
 */

public class Principal {
    
    /**
     * Método principal.
     * @param args 
     */
    public static void main(String[] args){
         
        
        int menu;//declaración de la variable menu para usarla en el posterior swith
        
        Concesionario mi_coche=new Concesionario();//instancia de clase concesionario.
        
        /*Declaración de variables que se van a usar para almacenar los datos que se van a mandar a la clase concesionario 
        para insertar los vehículos:*/
       
        String marca, matricula, descripcion, nomPropietario, numDni;
        
        int numKilom;
        
        int dia=1;
    
        int mes=1;
    
        int anio=1;
    
        double precio;
    
        LocalDate fecha;


        /*la estructura se inserta en un bucle do-while para volver al menú cada 
        vez que sea necesario*/
        do{
            
            /*Se muestra el menú para seleccionar lo que se desee, el valor se 
            introduce por teclado con la clase Scanner*/
            System.out.println("Menú \nSeleccione una opción: \n1.Nuevo Vehiculo."
                    + "\n2.Listar Vehículos. \n3.Buscar Vehículo. \n4.Modificar kms Vehículo." 
                    + "\n5.Eliminar vehículo. \n6.Salir.");
    
            Scanner entrada=new Scanner(System.in);
        
            menu=entrada.nextInt();
        
            entrada.nextLine();

            switch(menu){//Comienzo de la estructura switch, con 5 posibilidades de ejecución
           
                case 1:
                /*Opción "Nuevo Vehiculo". Se solicitarán al usuario por teclado los
                datos de un nuevo vehículo y si los datos son correctos, se almacenarán
                en Concesionario. En caso de error en algún dato se volverá a solicitar
                hasta introducir un valor correcto.*/
                      
                    System.out.println("Introduzca la marca:"); //se pide la marca
                
                    marca=entrada.nextLine();
                    
                                      
                    do{ //se pide la matrícula hasta que sea válida.
                        
                        System.out.println("Introduzca la matricula:");
                    
                        matricula=entrada.nextLine();
                    
                    }while(Validaciones.validarMatricula(matricula)==false);
                     
                    
                    do{//se pide el número de kilómetros hasta que el valor sea válido.
                        
                        System.out.println("Introduzca número de kilómetros:");
                    
                        numKilom=entrada.nextInt();
                        entrada.nextLine();
                    
                    }while(Validaciones.validarKilom(numKilom)==false);

                    
                    do{//se pide la fecha dividida en dia, mes y año, hasta que sea válida.
                        
                        System.out.println("Introduzca fecha de matriculación:");
                
                        System.out.println("Dia:");
                
                        dia=entrada.nextInt();
                        entrada.nextLine();
                
                        System.out.println("Mes:");
                
                        mes=entrada.nextInt();
                        entrada.nextLine();
                
                        System.out.println("Año:");
                
                        anio=entrada.nextInt();
                        entrada.nextLine();
                        
                        fecha=LocalDate.of(anio, mes, dia);//se guarda la fecha en LocalDate para poder usarla correctamente
                                    
                    }while(Validaciones.validarFecha(fecha)==false);
                    

                    System.out.println("Introduzca la descripción del vehículo:");//se pide la descripción del vehículo.
                
                    descripcion=entrada.nextLine();
                
                    
                    System.out.println("Introduzca el precio:");
                    /*se pide el precio y se captura la excepción generada si no se introducen números 
                    para que lance un mensaje en lugar de un error.*/
                   
                    try{
                        
                        precio=entrada.nextDouble();
                    
                        entrada.nextLine();
                        
                    }catch(InputMismatchException e){
                    
                        System.out.println("Error: formato inválido.");
                    
                        break;
                    }
                     
                    
                    do{//se pide el nombre hasta que sea válido.
                    
                        System.out.println("Introduzca el nombre completo del propietario del vehículo:");
                
                        nomPropietario=entrada.nextLine();
                      
                    }while (Validaciones.validarNombre(nomPropietario)==false);
                    
                    
                    do{//se pide el número de dni hasta que sea válido
                        
                        System.out.println("Introduzca el DNI del propietario:");
                    
                        numDni=entrada.nextLine();
                    
                    }while(Validaciones.validarDni(numDni)==false);
                    
                    
                    /*Finalmente con los datos introducidos, se invoca al método insertarVehiculo de la clase Concesionario,
                    según el valor devuelto, se introducirá el nuevo vehículo o devolverá un mensaje de error
                    y volverá al menú principal.
                    */
                    
                    Vehiculo v=new Vehiculo(marca, matricula, numKilom, dia, mes, anio, precio, descripcion, nomPropietario, numDni);
                    
                    int insert=mi_coche.insertarVehiculo(v);
                    
                    if(insert==0){
                        
                        System.out.println("\nVehiculo introducido correctamente.\n");
                        
                    }else if(insert==-1){
                        
                        System.out.println("Error: Número de matrícula ya registrada.\n");
                        
                        break;
                    }

                break;
                
    
                case 2:
                /*Opción "Listar Vehículos". Mostrará por pantalla un listado de los vehículos del concesionario, 
                mostrando marca, matrícula, precio, kilómetros y descripción por cada vehículo.*/
                    
                    mi_coche.listaVehiculos();//se llama al método listaVehiculos de la clase Concesionario.

                    break;
                    
                    
                case 3:
                /*Opción "Buscar Vehículo". Se solicitará una matrícula y se buscará en el concesionario 
                un vehículo cuya matrícula coincida con la introducida. Si existe se mostrarán su marca, 
                matrícula y precio por pantalla y en caso contrario el mensaje "No existe vehículo con la matrícula introducida".*/   
                    
                    System.out.println("Introduzca matrícula:");//se solicita la matrícula.
                    
                    matricula=entrada.nextLine();
                    
                    if(mi_coche.buscaVehiculo(matricula)==false){//si el método buscaVehiculo devuelve false, se mostrará un mensaje por pantalla.
                        
                        System.out.println("No existe vehículo con la matrícula introducida.");
                        
                        System.out.println();
                    }
                    
                    break;
                    
                    
                case 4:
                /*Opción "Modificar kms Vehículo": Se solicitará una matrícula y un número de kilómetros. 
                Si el vehículo con esa matrícula existe, se actualizará su número de kms al valor introducido. 
                Si no existe, se mostrará un mensaje por pantalla.*/   
                    
                    System.out.println("Introduzca matrícula:");
                    
                    matricula=entrada.nextLine();
                    
                    System.out.println("Introduzca kilómetros:");
                    
                    numKilom=entrada.nextInt();
                    
                    if(mi_coche.actualizaKms(matricula, numKilom)==true){
                        
                        System.out.println("Kilómetros actualizados con éxito.\n");
                        
                    }else{
                        
                        System.out.println("Matrícula no encontrada.\n");
                    }
                    
                    break;
                    
                case 5:
                /*Opción "Eliminar Vehículo": Se solicitará una matrícula.
                Si el vehículo con esa matrícula existe, se eliminará. 
                Si no existe, se mostrará un mensaje por pantalla.*/
                    
                    System.out.println("Intrduzca matrícula del vehículo que desea eliminar:");
                    
                    matricula=entrada.nextLine();
                    
                    boolean elim=mi_coche.eliminaVehiculo(matricula);
                    
                    if(elim==true){
                        
                        System.out.println("Vehículo eliminado.\n");
                    }else{
                        
                        System.out.println("Matrícula no encontrada.\n");
                    }
                    
                    break;
            }  
            
        }while(menu!=6); /*mientras se seleccione una opción diferente a 6(finalizar programa) el programa se seguirá ejecutando*/
    }
}       
    

