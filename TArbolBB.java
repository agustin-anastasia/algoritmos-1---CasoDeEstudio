

public class TArbolBB<T> implements IArbolBB<T> {

    private TElementoAB<T> raiz;

    /**
     * Separador utilizado entre elemento y elemento al imprimir la lista
     */
    public static final String SEPARADOR_ELEMENTOS_IMPRESOS = "-";

    public TArbolBB() {
        raiz = null;
    }

    /**
     * @param unElemento
     * @return
     */
    public boolean insertar(TElementoAB<T> unElemento) {
        if (esVacio()) {
            raiz = unElemento;
            return true;
        } else {
            return raiz.insertar(unElemento);
        }
    }

    /**
     * @param unaEtiqueta
     * @return
     */
    @SuppressWarnings("unchecked")
    public TElementoAB<T> buscar(Comparable unaEtiqueta) {
        if (esVacio()) {
            return null;
        } else {
            return raiz.buscar(unaEtiqueta);
        }
    }
    
    public boolean eliminarBoolean(Comparable unaEtiqueta){
        if (!esVacio()){
            this.raiz = this.raiz.eliminar(unaEtiqueta);
            TElementoAB aux = this.raiz;
            if(aux != null){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    
    public String postOrden() {
        if (raiz == null){
            return null;
        }
        String result = raiz.postOrden();
        return result.substring(0, result.length()-1);
    }
    
    public String preOrden() {
        if (raiz == null){
            return null;
        }
        String result = raiz.preOrden();
        return result.substring(0, result.length()-1);
    }

    /**
     * @return recorrida en inorden del arbol, null en caso de ser vacío
     */
    public String inOrden() {
        if (esVacio()) {
            return null;
        } else {
            String result = raiz.inOrden();
            return result.substring(0, result.length()-1);
        }
    }

    /**
     * @return
     */
    public boolean esVacio() {
        return (raiz == null);
    }

    /**
     * @return True si habían elementos en el árbol, false en caso contrario
     */
    public boolean vaciar() {
        if (!esVacio()) {
            raiz = null;
            return true;
        }
        return false;
    }

    @Override
    public Lista<T> inorden() {
        Lista <T> listaInorden = null;
        if (!esVacio()) {
            listaInorden = new Lista<T>();
            raiz.inOrden(listaInorden);
        }
        return listaInorden;
    }

    @Override
    public int obtenerAltura() {
        if (raiz != null){
            return raiz.obtenerAltura();
        }
        return -1;
    }

    @Override
    public int obtenerTamanio() {
        if (raiz != null){
            return raiz.obtenerTamanio();
        }
        return 0;
    }

    @Override
    public int obtenerNivel(Comparable unaEtiqueta) {
        if (raiz == null) {
            return -1;
        } else {
            return raiz.obtenerNivel(unaEtiqueta);
        }
    }

    @Override
    public int obtenerCantidadHojas() {
        if (raiz == null){
            return 0;
        }else{
            return raiz.obtenerCantidadHojas();
        }
    }


}
