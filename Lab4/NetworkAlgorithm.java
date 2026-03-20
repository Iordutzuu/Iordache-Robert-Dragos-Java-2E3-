package org.example;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.*;

public class NetworkAlgorithm {

    public static void displayKBestRoutes(City city, int k) {
        Graph<Intersection, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        Map<DefaultWeightedEdge, Street> edgeMap = new HashMap<>();

        for (Intersection i : city.getIntersections()) {
            graph.addVertex(i);
        }

        for (Street s : city.getStreets()) {
            DefaultWeightedEdge edge = graph.addEdge(s.getStart(), s.getEnd());
            if (edge != null) {
                graph.setEdgeWeight(edge, s.getLength());
                edgeMap.put(edge, s);
            }
        }

        List<SpanningTreeAlgorithm.SpanningTree<DefaultWeightedEdge>> allSolutions = new ArrayList<>();

        KruskalMinimumSpanningTree<Intersection, DefaultWeightedEdge> kruskal = new KruskalMinimumSpanningTree<>(graph);
        SpanningTreeAlgorithm.SpanningTree<DefaultWeightedEdge> bestTree = kruskal.getSpanningTree();
        allSolutions.add(bestTree);

        for (DefaultWeightedEdge edgeToPenalize : bestTree.getEdges()) {
            double originalWeight = graph.getEdgeWeight(edgeToPenalize);

            graph.setEdgeWeight(edgeToPenalize, Double.MAX_VALUE);

            KruskalMinimumSpanningTree<Intersection, DefaultWeightedEdge> altKruskal = new KruskalMinimumSpanningTree<>(graph);
            SpanningTreeAlgorithm.SpanningTree<DefaultWeightedEdge> altTree = altKruskal.getSpanningTree();

            if (altTree.getWeight() < Double.MAX_VALUE / 2) {
                allSolutions.add(altTree);
            }

            graph.setEdgeWeight(edgeToPenalize, originalWeight);
        }

        allSolutions.sort(Comparator.comparingDouble(SpanningTreeAlgorithm.SpanningTree::getWeight));

        List<SpanningTreeAlgorithm.SpanningTree<DefaultWeightedEdge>> uniqueSolutions = new ArrayList<>();
        Set<Set<DefaultWeightedEdge>> uniqueTrees = new HashSet<>();

        for (SpanningTreeAlgorithm.SpanningTree<DefaultWeightedEdge> tree : allSolutions) {
            if (uniqueTrees.add(tree.getEdges())) {
                uniqueSolutions.add(tree);
            }
        }

        int limit = Math.min(k, uniqueSolutions.size());
        for (int i = 0; i < limit; i++) {
            SpanningTreeAlgorithm.SpanningTree<DefaultWeightedEdge> tree = uniqueSolutions.get(i);
            System.out.println("\nSoluția " + (i + 1) + " (Cost total: " + tree.getWeight() + "):");
            for (DefaultWeightedEdge edge : tree.getEdges()) {
                Street s = edgeMap.get(edge);
                System.out.println("  " + s.getStart().getName() + " <--> " + s.getEnd().getName() + " [" + s.getLength() + "]");
            }
        }
    }
}