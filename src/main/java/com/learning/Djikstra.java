package com.learning;

import java.util.*;

/**
 * Created by maruthi on 08/08/16.
 * https://www.hackerrank.com/challenges/dijkstrashortreach
 * I found a youtube video of djkistra to be extremely useful. https://www.youtube.com/watch?v=WN3Rb9wVYDY
 */
public class Djikstra {

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
        int testCount = scanner.nextInt();
        for (int j = 0; j < testCount; j++) {
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
            Node startNode = nodeMap.get(s);
            startNode.dist = 0;
            getShortestRoutes(startNode);
            StringBuilder builder = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                if (i == s) {
                    continue;
                } else if (nodeMap.get(i).dist == Integer.MAX_VALUE) {
                    builder.append("-1");
                } else {
                    builder.append(nodeMap.get(i).dist);
                }
                builder.append(" ");
            }
            System.out.println(builder.toString().trim());
        }
    }

    static PriorityQueue<Node> nodes = new PriorityQueue<>();

    private static void getShortestRoutes(Node startNode) {
        if (startNode == null) {
            return;
        }

        List<Edge> edges = startNode.edges;
        for (Edge edge : edges) {
            if (edge.node2.dist > startNode.dist + edge.weight) {
                edge.node2.dist = startNode.dist + edge.weight;
                nodes.add(edge.node2);
            }
        }
        getShortestRoutes(nodes.poll());
    }
}
