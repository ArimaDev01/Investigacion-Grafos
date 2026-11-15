import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Algoritmo de Kruskal para el Árbol de Expansión Mínima (MST).
 * Ordena todas las aristas por peso y las agrega de menor a mayor,
 * descartando las que formarían un ciclo. Para detectar ciclos usa la
 * estructura Union-Find (conjuntos disjuntos).
 *
 * Complejidad temporal: O(E log E) = O(E log V), dominada por el ordenamiento.
 * Las operaciones de Union-Find cuestan O(alfa(V)), prácticamente constante.
 */
public class Kruskal {

    /** Conjuntos disjuntos con compresión de caminos y unión por rango. */
    static class UnionFind {
        private final int[] padre;
        private final int[] rango;

        UnionFind(int n) {
            padre = new int[n];
            rango = new int[n];
            for (int i = 0; i < n; i++) {
                padre[i] = i;
            }
        }

        int buscar(int x) {
            if (padre[x] != x) {
                padre[x] = buscar(padre[x]);   // compresión de caminos
            }
            return padre[x];
        }

        /** Une los conjuntos de a y b. Devuelve false si ya estaban unidos. */
        boolean unir(int a, int b) {
            int ra = buscar(a);
            int rb = buscar(b);
            if (ra == rb) {
                return false;                  // misma componente: habría ciclo
            }
            if (rango[ra] < rango[rb]) {
                padre[ra] = rb;
            } else if (rango[ra] > rango[rb]) {
                padre[rb] = ra;
            } else {
                padre[rb] = ra;
                rango[ra]++;
            }
            return true;
        }
    }

    public static List<Arista> mst(GrafoLista g) {
        int n = g.getNumVertices();
        List<Arista> aristas = new ArrayList<>(g.getAristas());
        Collections.sort(aristas);                       // O(E log E), orden estable

        UnionFind uf = new UnionFind(n);
        List<Arista> resultado = new ArrayList<>();
        for (Arista a : aristas) {
            if (uf.unir(a.origen, a.destino)) {
                resultado.add(a);
                if (resultado.size() == n - 1) {
                    break;                               // ya se tiene el árbol
                }
            }
        }
        return resultado;
    }
}
