/**
 * Grafo no dirigido y ponderado representado con MATRIZ DE ADYACENCIA.
 * La celda [i][j] guarda el peso de la arista i-j, o 0 si no existe.
 * Memoria: O(V^2).
 */
public class GrafoMatriz {
    private final int numVertices;
    private final String[] nombres;
    private final int[][] matriz;

    public GrafoMatriz(String[] nombres) {
        this.numVertices = nombres.length;
        this.nombres = nombres;
        this.matriz = new int[numVertices][numVertices];
    }

    /** Agrega una arista no dirigida u-v. O(1). */
    public void agregarArista(int u, int v, int peso) {
        matriz[u][v] = peso;
        matriz[v][u] = peso;
    }

    /** Verifica si existe la arista u-v. O(1). */
    public boolean existeArista(int u, int v) {
        return matriz[u][v] != 0;
    }

    public int peso(int u, int v) {
        return matriz[u][v];
    }

    public int getNumVertices() {
        return numVertices;
    }

    public String nombre(int v) {
        return nombres[v];
    }

    public void imprimir() {
        StringBuilder encabezado = new StringBuilder("    ");
        for (String n : nombres) {
            encabezado.append(String.format("%4s", n));
        }
        System.out.println(encabezado);
        for (int i = 0; i < numVertices; i++) {
            StringBuilder fila = new StringBuilder(String.format("%4s", nombres[i]));
            for (int j = 0; j < numVertices; j++) {
                fila.append(String.format("%4d", matriz[i][j]));
            }
            System.out.println(fila);
        }
    }
}
