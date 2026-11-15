/**
 * Representa una arista ponderada entre dos vértices.
 * Para grafos no dirigidos se usa la misma arista en ambos sentidos.
 */
public class Arista implements Comparable<Arista> {
    public final int origen;
    public final int destino;
    public final int peso;

    public Arista(int origen, int destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    /** Permite ordenar aristas por peso (lo usa Kruskal y la cola de Prim). */
    @Override
    public int compareTo(Arista otra) {
        return Integer.compare(this.peso, otra.peso);
    }
}
