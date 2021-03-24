package Concesionario;

import java.util.*;


/**
 *Clase para implementar la funcionalidad necesaria. Contiene la estructura de datos
 *dinámica necesaria para almacenar los vehículos. Se decide usar una LISTA, ya que
 * estas permiten acceder y buscar elementos de la lista, concretamente se elige
 * usar LINKEDIST, pues son más eficientes cuando se van a realizar eliminaciones en 
 * la aplicación (este es el caso pues se pide implementar la opción eliminar vehículo)
 */

/**
 *
 * @author Laura
 */

public class Concesionario {

    LinkedList <Vehiculo> t; //declaración del LinkedList de Vehiculos
     
    /**
     * Método constructor. Inicializa la estructura de datos.
     */
    public Concesionario(){
        
        t =new LinkedList<Vehiculo>(); //se inicializa la estructura de datos.    
    } 
   
    /**
     * Método para añadir nuevos vehículos.Recibe todos los datos de un vehículo 
     * y trata de insertarlo.
     * @param v: objeto de tipo vehículo creado en la clase Principal
     * @return 0 si se inserta el vehículo, -1 si no se puede insertar porque ya 
     * él concesionario está lleno y -2 si la matricula ya existe.
     */
    public int insertarVehiculo(Vehiculo v){
        
        for(Vehiculo i: t){//bucle para buscar si la matrícula introducida existe ya, si es así el indicador toma el valor -1.
            
            if(i.getMatricula().equals(v.getMatricula())){//comparación de las matrículas
                
                return -1;
            }
        }
                   
        t.add(v);//se añade el objeto a la lista
            
        Collections.sort(t);//se ordena la lista
              
        return 0;
    }
     
    /**
     * Método que lista por pantalla los datos de todos los vehículos del concesionario.
     */
    public void listaVehiculos(){
        
        int j=1;
        
        for(Vehiculo i:t){//bucle for-each para recorrer los objetos de toda la lista
            
            System.out.println("\nCOCHE " + j);
            System.out.println("Marca: "+ i.getMarca());
            System.out.println("Matricula: "+  i.getMatricula());
            System.out.println("Precio: " + i.getPrecio() + "€");
            System.out.println("Número de kilómetros: " + i.getNumKilom() + " kms");
            System.out.println("Descripción: " + i.getDescripcion()+"\n");
            
            j++;
        }
    }

    /**
     * Busca el vehículo en el concesionario a partir de una matrícula, devuelve sus datos si existe.
     * @param matricula: número de matrícula introducida para busca si existe un vehículo.
     * @return true si el coche es encontrado, false si no existe. 
     */   
    public boolean buscaVehiculo(String matricula){
        
        for(Vehiculo i: t){//bucle para recorrer todos los vehículos existentes
            
            if(i.getMatricula().equals(matricula)){//si se encuentra una matrícula igual a alguna existente el valor se torna true y se devuelve los datos del vehículo encontrado.

                System.out.println("\nCOCHE ENCONTRADO:");   
                System.out.println("Marca: "+ i.getMarca());
                System.out.println("Matrícula: "+  i.getMatricula());
                System.out.println("Precio: " + i.getPrecio() + "€\n");
                
                return true;
            }
        }
        
        return false;
               
    }
        
    /**
     * Busca el vehículo con la cuya matrícula coincida y actualiza sus kilómetros.
     * @param matricula: matrícula para buscar vehículo.
     * @param numKilom: número de kilómetros actualizados que se quiere introducir.
     * @return true si se hizo con éxito, false en caso contrario.
     */
    public boolean actualizaKms (String matricula, int numKilom){
        
        for(Vehiculo i: t){//bucle for-each para recorrer todos los objetos de la lista
            
            if(i.getMatricula().equals(matricula)){//si se encuentra coincidencia de matrícula, se le asigna como número de kilómetros el valor introducido por parámetro.
                
                i.setNumKilom(numKilom);//se establece el número de kilómetros recibido por parámetro
                
                return true;
            }
        }
        
        return false;

    }
    
    /**
     * Método para eliminar un vehículo según un número de matrícula introducido
     * @param matricula: número de matrícula del vehículo que se quiere eliminar
     * @return true si se ha eliminado correctamente, false en caso contrario
     */
    public boolean eliminaVehiculo (String matricula){
        
        for(Vehiculo i: t){//bucle para recorrer todos los vehículos
            
            if(i.getMatricula().equals(matricula)){//se comparan las matrículas
                
               t.remove(t.indexOf(i));//se elimina el vehículo encontrado
               
               return true;
            }
        }
        
        return false;
    }
}
   