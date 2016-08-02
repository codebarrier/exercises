package com.learning;

import java.util.*;

/**
 * Created by maruthi on 01/08/16.
 */
public class PrimMSTSpecialSubTree {

    private static class Node {
        int name;

        @Override
        public String toString() {
            return "Node{" +
                    "name=" + name +
                    ", edges=" + edges +
                    '}';
        }

        List<Edge> edges = new ArrayList<>();
    }

    private static class Edge {
        int weight;
        Node node1;

        @Override
        public String toString() {
            return "Edge{" +
                    "weight=" + weight +
                    '}';
        }

        Node node2;
    }

    static Map<Integer, Node> nodeMap = new HashMap<>();

    //static List<Edge> edges = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            Node node = new Node();
            node.name = i;
            nodeMap.put(i, node);
        }

        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int r = scanner.nextInt();
            Node xNode = nodeMap.get(x);
            Node yNode = nodeMap.get(y);
            Edge edge = new Edge();
            edge.node1 = xNode;
            edge.node2 = yNode;
            edge.weight = r;
            xNode.edges.add(edge);
            Edge edge2 = new Edge();
            edge2.node2 = xNode;
            edge2.node1 = yNode;
            edge2.weight = r;
            yNode.edges.add(edge2);
        }
        int s = scanner.nextInt();
        System.out.println(getTotalWeight(new HashSet<Integer>(), n, 0));
    }

    static Map<Node, Set<Node>> nodeGroup = new HashMap<>();

    private static int getTotalWeight(Set<Integer> visitedNodes, int totalNodeCount, int weight) {

        List<Edge> minEdges = new ArrayList<>();
        for (int i = 1; i <= totalNodeCount; i++) {
            Node node = nodeMap.get(i);
            if (visitedNodes.contains(node.name)) {
                continue;
            }
            Edge minEdge = node.edges.get(0);
            for (int j = 0; j < node.edges.size(); j++) {
                if (minEdge.weight > node.edges.get(j).weight) {
                    minEdge = node.edges.get(j);
                }
            }
            visitedNodes.add(node.name);
            visitedNodes.add(minEdge.node2.name);
            //System.out.println(node.name + ":" + minEdge.node2.name);
            minEdges.add(minEdge);
            node.edges.remove(minEdge);
            minEdge.node2.edges.remove(minEdge);
            weight += minEdge.weight;
        }

        List<Set<Node>> listOfNodes = new ArrayList<>();
        for (Edge edge : minEdges) {
            if (nodeGroup.get(edge.node1) == null && nodeGroup.get(edge.node2) == null) {
                HashSet<Node> set = new HashSet<>();
                set.add(edge.node2);
                set.add(edge.node1);
                nodeGroup.put(edge.node2, set);
                nodeGroup.put(edge.node1, set);
                listOfNodes.add(set);
            } else if (nodeGroup.get(edge.node2) == null) {
                nodeGroup.get(edge.node1).add(edge.node2);
                nodeGroup.put(edge.node2, nodeGroup.get(edge.node1));
            } else if (nodeGroup.get(edge.node1) == null) {
                nodeGroup.get(edge.node2).add(edge.node1);
                nodeGroup.put(edge.node1, nodeGroup.get(edge.node2));
            }
        }

        for (int j = 0; j < listOfNodes.size(); j++) {
            for (int i = 0; i < listOfNodes.size(); i++) {
                Set<Node> nodes = listOfNodes.get(i);
                Edge minEdge = null;
                for (Node node : nodes) {
                    for (Edge edge : node.edges) {
                        if (!nodes.contains(edge.node2)) {
                            if (minEdge == null) {
                                minEdge = edge;
                            } else {
                                if (minEdge.weight > edge.weight) {
                                    minEdge = edge;
                                }
                            }
                        }
                    }
                }
                if (minEdge != null) {
                    Set<Node> grp = nodeGroup.get(minEdge.node2);
                    nodeGroup.get(minEdge.node1).addAll(grp);
                    for (Node node : grp) {
                        nodeGroup.put(node, nodeGroup.get(minEdge.node1));
                    }
                    grp.clear();
                    weight += minEdge.weight;
                }
            }
        }
        return weight;
    }
}
