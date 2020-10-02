public class Lista<T> implements ILista<T> {

    private Nodo<T> primero;

    public Lista() {
        primero = null;
    }


    @Override
    public void insertar(Nodo<T> unNodo) {
        if (esVacia()) {
            primero = unNodo;
        } else {
            Nodo<T> aux = primero;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(unNodo);
        }
    }

    public void insertarOrdenado(Nodo<T> nodo){
        Nodo<T> actual;
        Nodo<T> siguiente;

        // si la lista es vacia, lo defino como primero
        if (esVacia()) {
            this.primero = nodo;
        } else {
            // verifico que no exista ya el nodo
            if (this.buscar(nodo.getEtiqueta()) == null) {
                actual = this.getPrimero();
                siguiente = actual.getSiguiente();

                // primer elemento ya es mayor
                if (actual.compareTo(nodo.getEtiqueta()) > 0) {
                    // lo agrego primero a lista
                    nodo.setSiguiente(actual);
                    this.primero = nodo;
                }
                // caso de un solo elemento y es menor
                else if (siguiente == null) {
                    // lo agrego como siguiente y ultimo (null)
                    actual.setSiguiente(nodo);
                    nodo.setSiguiente(null);
                }
                // caso de dos elementos y estoy entre medio
                else if (actual.compareTo(nodo.getEtiqueta()) < 0 && siguiente.compareTo(nodo.getEtiqueta()) > 0) {
                    actual.setSiguiente(nodo);
                    nodo.setSiguiente(siguiente);
                } else {
                    // recorro la lista y voy comparando
                    while (actual.getSiguiente() != null) {
                        actual = actual.getSiguiente();
                        siguiente = actual.getSiguiente();
                        // llego al final de la lista y lo inserto al final
                        if (siguiente == null) {
                            actual.setSiguiente(nodo);
                            nodo.setSiguiente(null);
                            break;
                        } else if (actual.compareTo(nodo.getEtiqueta()) < 0 && siguiente.compareTo(nodo.getEtiqueta()) > 0) {
                            // inserto en este lugar y dejo de recorrer la lista
                            actual.setSiguiente(nodo);
                            nodo.setSiguiente(siguiente);
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public Nodo<T> buscar(Comparable clave) {
        if (esVacia()) {
            return null;
        } else {
            Nodo<T> aux = primero;
            while (aux != null) {
                if (aux.getEtiqueta().equals(clave)) {
                    return aux;
                }
                aux = aux.getSiguiente();
            }
        }
        return null;
    }

    @Override
    public boolean eliminar(Comparable clave) {
        if (esVacia()) {
            return false;
        }
        if (primero.getSiguiente() == null) {
            if (primero.getEtiqueta().equals(clave)) {
                primero = null;
                return true;
            }
        }
        Nodo<T> aux = primero;
        if (aux.getEtiqueta().compareTo(clave) == 0) {
            //Eliminamos el primer elemento
            Nodo<T> temp1 = aux;
            Nodo<T> temp = aux.getSiguiente();
            primero = temp;
            return true;
        }
        while (aux.getSiguiente() != null) {
            if (aux.getSiguiente().getEtiqueta().equals(clave)) {
                Nodo<T> temp = aux.getSiguiente();
                aux.setSiguiente(temp.getSiguiente());
                return true;

            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    @Override
    // public String imprimir() {
    //     String aux = "";
    //     if (!esVacia()) {
    //         Nodo<T> temp = primero;
    //         while (temp != null) {
    //             temp.imprimirEtiqueta();
    //             temp = temp.getSiguiente();
    //         }
    //     }
    //     return aux;
    public String imprimir() {
        String lista = "";
        Nodo<T> actual = this.getPrimero(); 
        while (actual != null) { 
            lista += actual.getEtiqueta() + " ";
            actual = actual.getSiguiente(); 
        } 
        return lista;
    }

    public String imprimir(String separador) {
        String aux = "";
        if (esVacia()) {
            return "";
        } else {
            Nodo<T> temp = primero;
            aux = "" + temp.getEtiqueta();
            while (temp.getSiguiente() != null) {
                aux = aux + separador + temp.getSiguiente().getEtiqueta();
                temp = temp.getSiguiente();
            }

        }
        return aux;

    }

    @Override
    public int cantElementos() {
        int contador = 0;
        if (esVacia()) {
            System.out.println("Cantidad de elementos 0.");
            return 0;
        } else {
            INodo aux = primero;
            while (aux != null) {
                contador++;
                aux = aux.getSiguiente();
            }
        }
        return contador;
    }

    @Override
    public boolean esVacia() {
        return primero == null;
    }

    public Nodo<T> getPrimero() {
        return primero;
    }

    @Override
    public void setPrimero(Nodo<T> unNodo) {
        this.primero = unNodo;
    }

    @Override
    public boolean estaOrdenada(){
        return false;
    }

// metodos auxiliares

    public Nodo<T> quitarPrimero(){
        Nodo<T> actual = getPrimero();
        if(esVacia()){
            return null;
        }else{
            primero = actual.getSiguiente();
            return actual;
        }
    }

    public Nodo<T> quitarPrimero2(){
        if(esVacia()){
            return null;
        } else {
            Nodo<T> aux = this.getPrimero().clonar();
            eliminar(getPrimero().getEtiqueta());
            return aux;   
        }
    }

    public Nodo<T> quitarPrimero3(){
        if (esVacia()){
            return null;
        }
        Nodo<T> aQuitar = primero;
        primero = aQuitar.getSiguiente();
        return aQuitar;
    }

    public Nodo<T> quitar(Comparable clave){
        if (esVacia()) {
            return null;
        }
      
        Nodo aux = primero;
        if (aux.getEtiqueta().compareTo(clave) == 0) {
            //Eliminamos el primer elemento
            Nodo temp = aux.getSiguiente();
            primero = temp;
            aux.setSiguiente(null);
            return aux;
        }
        while (aux.getSiguiente() != null) {
            if (aux.getSiguiente().getEtiqueta().equals(clave)) {
                Nodo temp = aux.getSiguiente();
                aux.setSiguiente(temp.getSiguiente());
                temp.setSiguiente(null);
                return temp;

            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    public Nodo<T> quitarMenor() {
        Nodo<T> actual = this.primero;
        Nodo<T> menor = this.primero;
        Nodo<T> anteriorMenor = this.primero;
        if (esVacia()) {
            return null;
        }
        while (actual != null) {
            if (actual.getEtiqueta().compareTo(menor.getEtiqueta()) < 0) {
                menor = actual;
                anteriorMenor = actual;
            }
            actual = actual.getSiguiente();
        }
        eliminar(anteriorMenor.getEtiqueta());
        return menor;
    }

    public ListaQueOrdena<T> clonarLista(){
        ListaQueOrdena<T> nuevaLista = new ListaQueOrdena<>();
        if(esVacia()){
            return nuevaLista;
        }
        Nodo<T> aux = this.getPrimero();
        Nodo<T> actual;
        Nodo<T> siguiente;
        while (aux != null){
            actual = aux.clonar();
            // siguiente = aux.getSiguiente().clonar();
            // actual.setSiguiente(siguiente);
            nuevaLista.insertar(actual);
            aux = aux.getSiguiente();
        }
        return nuevaLista;
    }

}
