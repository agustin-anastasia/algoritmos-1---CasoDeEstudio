public class Sucursal implements ISucursal {

    private String id;
    private String telefono;
    private String direccion;
    private String codigoPostal;
    private String ciudad;
    private String departamento;
    private TArbolBB<Producto> productos; //arbol de productos de la sucursal

    public Sucursal(String id, String telefono, String direccion, String codPostal, String ciudad, String departamento) {
        this.id = id;
        this.telefono = telefono;
        this.direccion = direccion;
        this.codigoPostal = codPostal;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.productos = new TArbolBB<Producto>();
    }
    
    public TArbolBB<Producto> getArbolProductos(){
        return this.productos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
    @Override
    public void insertarProducto(Producto unProducto) {
        TElementoAB<Producto> elementoProducto = new TElementoAB(unProducto.getEtiqueta(), unProducto);
        this.productos.insertar(elementoProducto);
    }

    @Override
    public String imprimirProductos() {
        return productos.inOrden();
    }
    
    public String imprimirSucursal(){
        String almacen = this.getId() + " || " + this.getTelefono() + " || " + this.getDireccion() + " || " + this.getCodigoPostal().toString() + " || " +
                this.getCiudad() + " || " + this.getDepartamento();
        return almacen;
    }

    @Override
    public Boolean agregarStock(Comparable clave, Integer cantidad) {
        TElementoAB<Producto> aux = this.productos.buscar(clave);
        if (aux != null){
            Producto unProducto = aux.getDatos();
            Integer stockActual = unProducto.getStock();
            unProducto.setStock(stockActual + cantidad);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Integer restarStock(Comparable clave, Integer cantidad) {
        TElementoAB<Producto> unProducto = this.productos.buscar(clave);
        if (unProducto != null){
            Integer stockActual = unProducto.getDatos().getStock();
            if((stockActual - cantidad) > 0){
                unProducto.getDatos().setStock(stockActual - cantidad);
                unProducto.getDatos().getStock();
            }else{
                System.out.println("No es posible restar una cantidad mayor a la existente.");
                return -1;
            }
        }
        return -1;
    }

    @Override
    public Producto buscarPorCodigo(Comparable clave) {
        TElementoAB<Producto> unProducto = this.productos.buscar(clave);
        if (unProducto != null){
            return unProducto.getDatos();
        }else{
            return null;
        }
    }

    @Override
    public boolean eliminarProducto(Comparable clave) {
        return this.productos.eliminarBoolean(clave);
    }


   
 
   


  

   

   

   

}
