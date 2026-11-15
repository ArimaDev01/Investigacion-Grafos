import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Algoritmo de Prim para el Árbol de Expansión Mínima (MST).
 * Hace crecer UN solo árbol desde un vértice inicial, agregando siempre
 * la arista de menor peso que conecta el árbol con un vértice nuevo.
 *
 * Complejidad temporal:
 *   - con montículo binario (PriorityQueue) y listas: O(E log V)
 *   - con matriz de adyacencia y arreglo (sin montículo): O(V^2)
 */
public class Prim {

    /** Versión con cola de prioridad sobre listas de adyacencia. */
    public static List<Arista> mst(GrafoLista g, int inicio) {
        int n = g.getNumVertices();
        boolean[] enArbol = new boolean[n];
        List<Arista> resultado = new ArrayList<>();
        PriorityQueue<Arista> cola = new PriorityQueue<>();

        enArbol[inicio] = true;
        cola.addAll(g.vecinos(inicio));

        while (!cola.isEmpty() && resultado.size() < n - 1) {
            Arista a = cola.poll();              // arista más liviana disponible
            if (enArbol[a.destino]) {
                continue;                        // formaría un ciclo: se descarta
            }
            enArbol[a.destino] = true;
            resultado.add(a);
            for (Arista siguiente : g.vecinos(a.destino)) {
                if (!enArbol[siguiente.destino]) {
                    cola.add(siguiente);
                }
            }
        }
        return resultado;
    }

    /** Versión clásica O(V^2) sobre matriz de adyacencia. */
    public static List<Arista> mst(GrafoMatriz g, int inicio) {
        int n = g.getNumVertices();
        int[] costo = new int[n];       // menor peso para conectar cada vértice
        int[] padre = new int[n];
        boolean[] enArbol = new boolean[n];
        for (int i = 0; i < n; i++) {
            costo[i] = Integer.MAX_VALUE;
            padre[i] = -1;
        }
        costo[inicio] = 0;
        List<Arista> resultado = new ArrayList<>();

        for (int k = 0; k < n; k++) {
            int u = -1;                                   // vértice más barato fuera del árbol
            for (int v = 0; v < n; v++) {
                if (!enArbol[v] && (u == -1 || costo[v] < costo[u])) {
                    u = v;
                }
            }
            if (costo[u] == Integer.MAX_VALUE) {
                break;                                    // grafo no conexo
            }
            enArbol[u] = true;
            if (padre[u] != -1) {
                resultado.add(new Arista(padre[u], u, costo[u]));
            }
            for (int v = 0; v < n; v++) {                 // actualizar costos
                if (g.existeArista(u, v) && !enArbol[v] && g.peso(u, v) < costo[v]) {
                    costo[v] = g.peso(u, v);
                    padre[v] = u;
                }
            }
        }
        return resultado;
    }
}
