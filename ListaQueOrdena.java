
public class ListaQueOrdena<T> extends Lista<T> implements iOrdenar {

    @Override
    public Lista<T> insercionDirecta() {
        Lista<T> listaOrdenada = new Lista<>();
        Nodo<T> actual = getPrimero();
        if (esVacia()) {
            return null;
        }
        while (actual != null) {
            listaOrdenada.insertar(this.quitarPrimero());
            actual = actual.getSiguiente();
        }
        return listaOrdenada;


    }

    @Override
    public Lista<T> seleccionDirecta() {
        Lista<T> listaOrdenada = new Lista<>();
        if (esVacia()) {
            return null;
        }
        Nodo<T> actual = getPrimero();
        while (actual.getSiguiente() != null) {
            Nodo<T> aux = quitarMenor().clonar();
            listaOrdenada.insertar(aux);
        }
        Nodo<T> aux2 = quitarMenor().clonar();
        listaOrdenada.insertar(aux2);
        return listaOrdenada;

     

    }

}
