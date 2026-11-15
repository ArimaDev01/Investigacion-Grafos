import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Recorrido en profundidad (Depth-First Search).
 * Avanza lo más lejos posible por un camino antes de retroceder.
 * Se implementa de forma recursiva (pila implícita) e iterativa (pila explícita).
 *
 * Complejidad temporal:
 *   - con listas de adyacencia: O(V + E)
 *   - con matriz de adyacencia: O(V^2)
 * Complejidad espacial: O(V) (pila + arreglo de visitados).
 */
public class DFS {

    /** DFS recursivo. Devuelve el orden de visita. */
    public static List<Integer> recorrerRecursivo(GrafoLista g, int inicio) {
        boolean[] visitado = new boolean[g.getNumVertices()];
        List<Integer> orden = new ArrayList<>();
        visitar(g, inicio, visitado, orden);
        return orden;
    }

    private static void visitar(GrafoLista g, int u, boolean[] visitado, List<Integer> orden) {
        visitado[u] = true;
        orden.add(u);
        for (Arista a : g.vecinos(u)) {
            if (!visitado[a.destino]) {
                visitar(g, a.destino, visitado, orden);
            }
        }
    }

    /**
     * DFS iterativo con pila explícita. Los vecinos se apilan en orden
     * inverso para que el resultado coincida con la versión recursiva.
     */
    public static List<Integer> recorrerIterativo(GrafoLista g, int inicio) {
        boolean[] visitado = new boolean[g.getNumVertices()];
        List<Integer> orden = new ArrayList<>();
        Deque<Integer> pila = new ArrayDeque<>();
        pila.push(inicio);

        while (!pila.isEmpty()) {
            int u = pila.pop();
            if (visitado[u]) {
                continue;
            }
            visitado[u] = true;
            orden.add(u);
            List<Arista> vecinos = g.vecinos(u);
            for (int i = vecinos.size() - 1; i >= 0; i--) {
                int v = vecinos.get(i).destino;
                if (!visitado[v]) {
                    pila.push(v);
                }
            }
        }
        return orden;
    }
}
