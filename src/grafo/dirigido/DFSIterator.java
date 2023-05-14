package grafo.dirigido;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class DFSIterator<T> implements Iterator<Vertice<T>> {
    private final Stack<Vertice<T>> stack = new Stack<>();

    public DFSIterator(Grafo<T> grafo) {
        Vertice<T> vertice = grafo.getVertices().get(0);
        stack.push(vertice);
        vertice.setStatus(VertexState.Visited);
    }

    @Override
    public Vertice<T> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Vertice<T> vertice = stack.pop();
        for (int i = vertice.getAdj().size() - 1; i >= 0; i--) {
            Aresta<T> arco = vertice.getAdj().get(i);
            Vertice<T> v = arco.getDestino();
            if (v.getStatus() == VertexState.Unvisited) {
                v.setStatus(VertexState.Visited);
                stack.push(v);
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
        return !stack.isEmpty();
    }
}
