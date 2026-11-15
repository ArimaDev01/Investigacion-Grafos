# Investigación: Grafos y Algoritmos Fundamentales de Recorrido y Optimización

Instituto Tecnológico de Costa Rica — Escuela de Ingeniería en Computadores
Algoritmos y Estructuras de Datos I — II Semestre 2026

Implementación en **Java** de los algoritmos estudiados en la investigación. Todos se ejecutan sobre el mismo grafo base no dirigido y ponderado de 6 vértices y 8 aristas.

```
A-B 4, A-C 2, B-D 5, C-D 8, C-E 10, D-E 2, D-F 6, E-F 3
```

## Contenido

| Archivo | Descripción | Complejidad temporal |
|---|---|---|
| `src/Arista.java` | Arista ponderada (comparable por peso) | — |
| `src/GrafoLista.java` | Representación con listas de adyacencia | Memoria O(V + E) |
| `src/GrafoMatriz.java` | Representación con matriz de adyacencia | Memoria O(V²) |
| `src/BFS.java` | Recorrido en anchura (cola) | O(V + E) lista / O(V²) matriz |
| `src/DFS.java` | Recorrido en profundidad (recursivo e iterativo) | O(V + E) lista / O(V²) matriz |
| `src/Prim.java` | Árbol de expansión mínima, Prim (montículo y matriz) | O(E log V) / O(V²) |
| `src/Kruskal.java` | Árbol de expansión mínima, Kruskal + Union-Find | O(E log E) |
| `src/Main.java` | Programa de demostración | — |
| `salida_ejemplo.txt` | Salida obtenida al ejecutar `Main` | — |

## Compilar y ejecutar

Requiere JDK 11 o superior.

```bash
cd src
javac -encoding UTF-8 *.java
java Main
```

## Resultados esperados

- BFS desde A: `A, B, C, D, E, F`
- DFS desde A: `A, B, D, C, E, F`
- Prim desde A: `A-C(2) A-B(4) B-D(5) D-E(2) E-F(3)` → peso total 16
- Kruskal: `A-C(2) D-E(2) E-F(3) A-B(4) B-D(5)` → peso total 16
