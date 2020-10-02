
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Supermercado supermercado = new Supermercado("GEANT", "Av a la Playa, Ciudad de la Costa", "08005020");
        //carga de las sucursales
        String[] datosSucursales = ManejadorArchivosGenerico.leerArchivo("src/sucursalesPrueba.txt");
        //carga de los productos
        String[] datosProductos = ManejadorArchivosGenerico.leerArchivo("src/productosPrueba.txt");
        //carga del stock de los productos por sucursal
        String[] datosStock = ManejadorArchivosGenerico.leerArchivo("src/stockPrueba.txt");
        
        
        //lista de sucursales
        //Lista<Sucursal> listaSucursales = new Lista<>();

        
        //metodo suffle para mezclar de manera randomica un array utilizando List
        //devuelvo un array de tipo String con las lineas del archivo mezcladas
        List<String> listaDatosArchivo = Arrays.asList(datosSucursales);
        Collections.shuffle(listaDatosArchivo);
        String[] datosArchivoSucursales = listaDatosArchivo.toArray(datosSucursales); // datosArchivo es el array mezclado con las lineas del archivo
        
        
        //creacion de las sucursales e insercion de las misma en una lista de sucursales, cada sucursal es un nodo de la lista
        for (String linea : datosArchivoSucursales) {
            String[] datos = linea.split(",");
            String id = datos[0];
            String tel = datos[1];
            String dir = datos[2];
            String codPostal = datos[3];
            String ciudad = datos[4];
            String depto = datos[5];
            Sucursal sucursal = new Sucursal(id, tel, dir, codPostal, ciudad, depto);
//            Nodo<Sucursal> nodoSucursal = new Nodo<>(datos[0], sucursal);
            supermercado.insertarSucursal(sucursal);
        }
        
        // mezclo las lineas del archivo "productosPrueba.txt"
        List<String> listaDatosArchivoProductos = Arrays.asList(datosProductos);
        Collections.shuffle(listaDatosArchivoProductos);
        String[] datosArchivoProductos = listaDatosArchivoProductos.toArray(datosProductos);
        
        // arbol axuiliar de productos para luego buscar en este arbol e insertarlos en el arbol
        // de productos de cada sucursal        
        for (String linea : datosArchivoProductos){
            try{
                String[] datos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                String id = datos[0];
                String nombre = datos[1];
                Float precio = Float.parseFloat(datos[2]);
                Integer stock = 0;
                Producto unProducto = new Producto(id, nombre, precio, stock);
                TElementoAB<Producto> elementoProducto = new TElementoAB(id, unProducto);
                supermercado.poolArbolProductos(unProducto);
            } catch (java.lang.NumberFormatException exception) {
            }
            catch(Exception exception){
                System.out.println("se ha generado un error " + exception);
            }
        }
        
        // mezclo las lineas del archivo "stockPrueba.txt"
        List<String> listaDatosArchivoStock = Arrays.asList(datosStock);
        Collections.shuffle(listaDatosArchivoStock);
        String[] datosArchivoStock = listaDatosArchivoStock.toArray(datosStock);
        
        //asignacion de productos a cada sucursal con datosStock
        //se crean los arboles de productos para cada sucursal 
        try {
            for (String linea : datosArchivoStock) {
                String[] datos = linea.split(",");
                String idSucursal = datos[0];
                String idProducto = datos[1];
                Integer cantidad = Integer.parseInt(datos[2]);
                supermercado.insertarProductoConStockEnSucursal(idSucursal, idProducto, cantidad);
            }
        }catch (java.lang.NumberFormatException exception){
            
        }
        
        //ejemplo de busqueda de sucursal y su arbol de productos
        System.out.println("Lista de sucursales: " + supermercado.getListaSucursales().imprimir(" - "));
        System.out.println("cantidad de productos en arbol de la sucursal Local 122: " + supermercado.getListaSucursales().buscar("Local 122").getDato().getArbolProductos().obtenerTamanio());
        
        System.out.println(supermercado.simularVentaProducto("Local 122","1403796890",205));
//        System.out.println(supermercado.getListaSucursales().buscar("Local 102").getDato().getArbolProductos().inOrden());
      

    }
    
}
