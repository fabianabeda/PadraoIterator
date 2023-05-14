package grafo.dirigido;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.LinkedList;

public class BFSIterator<T> implements Iterator<Vertice<T>> {
    private final Queue<Vertice<T>> queue = new LinkedList<>();

    public BFSIterator(Grafo<T> grafo) {
        for (Vertice<T> vertice : grafo.getVertices()) {
            vertice.setStatus(VertexState.Unvisited);
        }
        Vertice<T> start = grafo.getVertices().get(0);
        queue.offer(start);
        start.setStatus(VertexState.Visited);
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
