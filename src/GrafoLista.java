import java.util.ArrayList;
import java.util.List;

/**
 * Grafo no dirigido y ponderado representado con LISTAS DE ADYACENCIA.
 * Cada vértice guarda una lista con las aristas que salen de él.
 * Memoria: O(V + E).
 */
public class GrafoLista {
    private final int numVertices;
    private final String[] nombres;
    private final List<List<Arista>> adyacencia;
    private final List<Arista> aristas;   // lista global (útil para Kruskal)

    public GrafoLista(String[] nombres) {
        this.numVertices = nombres.length;
        this.nombres = nombres;
        this.adyacencia = new ArrayList<>();
        this.aristas = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            adyacencia.add(new ArrayList<>());
        }
    }

    /** Agrega una arista no dirigida u-v con el peso indicado. O(1). */
    public void agregarArista(int u, int v, int peso) {
        adyacencia.get(u).add(new Arista(u, v, peso));
        adyacencia.get(v).add(new Arista(v, u, peso));
        aristas.add(new Arista(u, v, peso));
    }

    /** Devuelve los vecinos de u. O(1) para obtener la lista. */
    public List<Arista> vecinos(int u) {
        return adyacencia.get(u);
    }

    /** Verifica si existe la arista u-v. O(grado(u)). */
    public boolean existeArista(int u, int v) {
        for (Arista a : adyacencia.get(u)) {
            if (a.destino == v) {
                return true;
            }
        }
        return false;
    }

    public List<Arista> getAristas() {
        return aristas;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public String nombre(int v) {
        return nombres[v];
    }

    public void imprimir() {
        for (int u = 0; u < numVertices; u++) {
            StringBuilder sb = new StringBuilder(nombres[u] + " -> ");
            for (Arista a : adyacencia.get(u)) {
                sb.append(nombres[a.destino]).append("(").append(a.peso).append(") ");
            }
            System.out.println(sb.toString().trim());
        }
    }
}
