/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */
public class Supermercado {
    
    private String nombre;
    private String direccion;
    private String telefono;
    private Lista<Sucursal> listaSucursales;
    private TArbolBB<Producto> poolProductos;
    
    public Supermercado(String nombre, String direccion, String telefono){
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        listaSucursales = new Lista<>();
        poolProductos = new TArbolBB();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Lista<Sucursal> getListaSucursales() {
        return this.listaSucursales;
    }
    
    public void insertarSucursal(Sucursal unaSucursal){
        Nodo<Sucursal> nodoSucursal = new Nodo<>(unaSucursal.getId(), unaSucursal);
        if (this.listaSucursales.buscar(unaSucursal.getId()) == null){
            this.listaSucursales.insertar(nodoSucursal);
        }
    }
    
    //pool general de productos insertados en un arbol
    public void poolArbolProductos(Producto unProducto){
        TElementoAB<Producto> elementoProducto = new TElementoAB<>(unProducto.getEtiqueta(), unProducto);
        this.poolProductos.insertar(elementoProducto);
    }
    
    //completo la informacion de los arboles de productos de cada sucursal
    public void insertarProductoConStockEnSucursal(String idSucursal,Comparable idProducto , Integer cantidadStock){
        Nodo<Sucursal> unNodoSucursal = this.listaSucursales.buscar(idSucursal);
        try {
            if (unNodoSucursal != null) {
                Producto unElementoProducto = this.poolProductos.buscar(idProducto).getDatos();
                if (unElementoProducto != null) {
                    unElementoProducto.agregarStock(cantidadStock);
                    unNodoSucursal.getDato().insertarProducto(unElementoProducto);
                }
            }
        } catch (java.lang.NullPointerException exception){
            
        }
    }
//    
//    public buscarStockSuficiente
    
    public Integer simularVentaProducto(String idSucursal, String idProducto, Integer cantidad){
        Nodo<Sucursal> nodoSucursal = this.listaSucursales.buscar(idSucursal);
        if (nodoSucursal != null){ //se encontró la sucursal en la que se produce la venta del producto
            Producto productoAVender = nodoSucursal.getDato().getArbolProductos().buscar(idProducto).getDatos();
            System.out.println("Stock: " + productoAVender.getStock());
            System.out.println("Stock - CANTIDAD: " + (productoAVender.getStock()-cantidad));
            if ((productoAVender.getStock()-cantidad) >= 0){
                return productoAVender.getStock()-cantidad;
            }else{//no hay stock suficiente, generamos una lista con sucursal que si tengan stock de ese producto
                nodoSucursal = this.getListaSucursales().getPrimero();
                Lista<Sucursal> listaConStock = new Lista<>();
                while (nodoSucursal != null) {
                    if (nodoSucursal.getDato().getArbolProductos() != null) {
                        Producto productoAux = nodoSucursal.getDato().getArbolProductos().buscar(idProducto).getDatos();
                        if ((productoAux.getStock() - cantidad) >= 0) {
                            listaConStock.insertar(nodoSucursal.clonar());
                            System.out.println("listaCosStock: " + listaConStock.imprimir(" || "));
                        }
                    }
                    nodoSucursal = nodoSucursal.getSiguiente();
                }
                return listaConStock.cantElementos();
            }
        }
        return -1;
    }
    
    
    
}
