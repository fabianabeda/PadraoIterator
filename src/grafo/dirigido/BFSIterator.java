package grafo.dirigido;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BFSIterator<T> implements Iterator<Vertice<T>> {
    private final Queue<Vertice<T>> queue = new ArrayDeque<>();

    public BFSIterator(Grafo<T> grafo) {
        Vertice<T> vertice = grafo.getVertices().get(0);
        queue.offer(vertice);
        vertice.setStatus(VertexState.Visited);
    }

    @Override
    public Vertice<T> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Vertice<T> vertice = queue.poll();
        for (Aresta<T> arco : vertice.getAdj()) {
            Vertice<T> v = arco.getDestino();
            if (v.getStatus() == VertexState.Unvisited) {
                v.setStatus(VertexState.Visited);
                queue.offer(v);
            }
        }
        return vertice;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
