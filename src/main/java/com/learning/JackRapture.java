package com.learning;

import java.util.*;

/**
 * Created by maruthi on 04/08/16.
 * https://www.hackerrank.com/challenges/jack-goes-to-rapture
 * Using a variation of the djikstra's algorithm.
 */
public class JackRapture {

    private static class Node implements Comparable<Node> {
        int name;
        int dist = Integer.MAX_VALUE;

        @Override
        public String toString() {
            return "Node{" +
                    "name=" + name +
                    ", edges=" + edges +
                    '}';
        }

        List<Edge> edges = new ArrayList<>();

        @Override
        public int compareTo(Node o) {
            if (dist == o.dist) {
                return 0;
            } else if (dist > o.dist) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    private static class Edge {
        int weight;
        Node node1;
        Node node2;
    }

    static Map<Integer, Node> nodeMap = new HashMap<>();

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

        Node startNode = nodeMap.get(1);
        startNode.dist = 0;
        getShortestRoutes(startNode);
        if (nodeMap.get(n).dist == Integer.MAX_VALUE) {
            System.out.println("NO PATH EXISTS");
        } else {
            System.out.println(nodeMap.get(n).dist);
        }

    }

    static PriorityQueue<Node> nodes = new PriorityQueue<>();

    private static void getShortestRoutes(Node startNode) {
        if (startNode == null) {
            return;
        }

        List<Edge> edges = startNode.edges;
        for (Edge edge : edges) {
            int diff = (edge.weight - startNode.dist) < 0 ? 0 : (edge.weight - startNode.dist);
            if (edge.node2.dist > startNode.dist + diff) {
                edge.node2.dist = startNode.dist + diff;
                nodes.add(edge.node2);
            }
        }
        getShortestRoutes(nodes.poll());
    }
}
