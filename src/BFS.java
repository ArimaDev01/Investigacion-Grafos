import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * Recorrido en anchura (Breadth-First Search).
 * Visita los vértices por niveles usando una COLA (FIFO).
 *
 * Complejidad temporal:
 *   - con listas de adyacencia: O(V + E)
 *   - con matriz de adyacencia: O(V^2)
 * Complejidad espacial: O(V) (cola + arreglo de visitados).
 */
public class BFS {

    /** BFS sobre listas de adyacencia. Devuelve el orden de visita. */
    public static List<Integer> recorrer(GrafoLista g, int inicio, int[] nivel) {
        int n = g.getNumVertices();
        boolean[] visitado = new boolean[n];
        Arrays.fill(nivel, -1);
        List<Integer> orden = new ArrayList<>();
        Queue<Integer> cola = new ArrayDeque<>();

        visitado[inicio] = true;
        nivel[inicio] = 0;
        cola.add(inicio);

        while (!cola.isEmpty()) {
            int u = cola.poll();
            orden.add(u);
            for (Arista a : g.vecinos(u)) {
                int v = a.destino;
                if (!visitado[v]) {
                    visitado[v] = true;          // se marca al encolar
                    nivel[v] = nivel[u] + 1;     // distancia en número de aristas
                    cola.add(v);
                }
            }
        }
        return orden;
    }

    /** BFS sobre matriz de adyacencia: revisa toda la fila de cada vértice. */
    public static List<Integer> recorrer(GrafoMatriz g, int inicio) {
        int n = g.getNumVertices();
        boolean[] visitado = new boolean[n];
        List<Integer> orden = new ArrayList<>();
        Queue<Integer> cola = new ArrayDeque<>();

        visitado[inicio] = true;
        cola.add(inicio);

        while (!cola.isEmpty()) {
            int u = cola.poll();
            orden.add(u);
            for (int v = 0; v < n; v++) {
                if (g.existeArista(u, v) && !visitado[v]) {
                    visitado[v] = true;
                    cola.add(v);
                }
            }
        }
        return orden;
    }
}
