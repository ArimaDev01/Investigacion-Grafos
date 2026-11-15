import java.util.List;

/**
 * Programa de demostración. Construye el MISMO grafo base con las dos
 * representaciones y ejecuta BFS, DFS, Prim y Kruskal sobre él.
 *
 * Grafo base (no dirigido y ponderado), 6 vértices y 8 aristas:
 *   A-B 4, A-C 2, B-D 5, C-D 8, C-E 10, D-E 2, D-F 6, E-F 3
 */
public class Main {

    private static final String[] NOMBRES = {"A", "B", "C", "D", "E", "F"};
    private static final int A = 0, B = 1, C = 2, D = 3, E = 4, F = 5;
    private static final int[][] ARISTAS = {
        {A, B, 4}, {A, C, 2}, {B, D, 5}, {C, D, 8},
        {C, E, 10}, {D, E, 2}, {D, F, 6}, {E, F, 3}
    };

    public static void main(String[] args) {
        GrafoLista lista = new GrafoLista(NOMBRES);
        GrafoMatriz matriz = new GrafoMatriz(NOMBRES);
        for (int[] a : ARISTAS) {
            lista.agregarArista(a[0], a[1], a[2]);
            matriz.agregarArista(a[0], a[1], a[2]);
        }

        titulo("1. Lista de adyacencia");
        lista.imprimir();

        titulo("2. Matriz de adyacencia");
        matriz.imprimir();

        titulo("3. BFS desde A");
        int[] nivel = new int[NOMBRES.length];
        List<Integer> bfs = BFS.recorrer(lista, A, nivel);
        System.out.println("Orden (lista):  " + nombres(bfs));
        System.out.println("Orden (matriz): " + nombres(BFS.recorrer(matriz, A)));
        StringBuilder niveles = new StringBuilder("Niveles: ");
        for (int v = 0; v < NOMBRES.length; v++) {
            niveles.append(NOMBRES[v]).append("=").append(nivel[v]).append(" ");
        }
        System.out.println(niveles.toString().trim());

        titulo("4. DFS desde A");
        System.out.println("Recursivo: " + nombres(DFS.recorrerRecursivo(lista, A)));
        System.out.println("Iterativo: " + nombres(DFS.recorrerIterativo(lista, A)));

        titulo("5. Prim desde A");
        imprimirArbol(Prim.mst(lista, A), "Prim (PriorityQueue)");
        imprimirArbol(Prim.mst(matriz, A), "Prim (matriz O(V^2))");

        titulo("6. Kruskal");
        imprimirArbol(Kruskal.mst(lista), "Kruskal");
    }

    private static void titulo(String t) {
        System.out.println();
        System.out.println("=== " + t + " ===");
    }

    private static String nombres(List<Integer> vertices) {
        StringBuilder sb = new StringBuilder();
        for (int v : vertices) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(NOMBRES[v]);
        }
        return sb.toString();
    }

    private static void imprimirArbol(List<Arista> arbol, String etiqueta) {
        int total = 0;
        StringBuilder sb = new StringBuilder();
        for (Arista a : arbol) {
            sb.append(NOMBRES[a.origen]).append("-").append(NOMBRES[a.destino])
              .append("(").append(a.peso).append(") ");
            total += a.peso;
        }
        System.out.println(etiqueta + ": " + sb.toString().trim() + "  | peso total = " + total);
    }
}
