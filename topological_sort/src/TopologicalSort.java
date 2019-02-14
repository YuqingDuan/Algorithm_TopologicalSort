import java.util.Stack;

/**
 * Topological sort.
 *
 * @author Yuqing Duan
 * @date 02/14/2019
 */
public class TopologicalSort {
    private int numVertexes;

    private static final class ENode {
        int adjvex;
        ENode next;

        public ENode(int adjvex) {
            this.adjvex = adjvex;
        }

        public int getAdjvex() {
            return adjvex;
        }

        public void setAdjvex(int adjvex) {
            this.adjvex = adjvex;
        }

        public ENode getNext() {
            return next;
        }

        public void setNext(ENode next) {
            this.next = next;
        }
    }

    private static final class VNode {
        int indegree;
        String data;
        ENode firstEdge;

        public VNode(int indegree, String data) {
            this.indegree = indegree;
            this.data = data;
        }

        public int getIndegree() {
            return indegree;
        }

        public void setIndegree(int indegree) {
            this.indegree = indegree;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public ENode getFirstEdge() {
            return firstEdge;
        }

        public void setFirstEdge(ENode firstEdge) {
            this.firstEdge = firstEdge;
        }
    }

    private VNode[] adjList;

    public TopologicalSort(int numVertexes) {
        this.numVertexes = numVertexes;
    }

    private void createGraph() {
        VNode node00 = new VNode(0, "V0");
        VNode node01 = new VNode(0, "V1");
        VNode node02 = new VNode(2, "V2");
        VNode node03 = new VNode(0, "V3");
        VNode node04 = new VNode(2, "V4");
        VNode node05 = new VNode(3, "V5");
        VNode node06 = new VNode(1, "V6");
        VNode node07 = new VNode(2, "V7");
        VNode node08 = new VNode(2, "V8");
        VNode node09 = new VNode(1, "V9");
        VNode node10 = new VNode(1, "V10");
        VNode node11 = new VNode(2, "V11");
        VNode node12 = new VNode(1, "V12");
        VNode node13 = new VNode(2, "V13");
        adjList = new VNode[numVertexes];
        adjList[0] = node00;
        adjList[1] = node01;
        adjList[2] = node02;
        adjList[3] = node03;
        adjList[4] = node04;
        adjList[5] = node05;
        adjList[6] = node06;
        adjList[7] = node07;
        adjList[8] = node08;
        adjList[9] = node09;
        adjList[10] = node10;
        adjList[11] = node11;
        adjList[12] = node12;
        adjList[13] = node13;
        node00.firstEdge = new ENode(11);
        node00.firstEdge.next = new ENode(5);
        node00.firstEdge.next.next = new ENode(4);
        node01.firstEdge = new ENode(8);
        node01.firstEdge.next = new ENode(4);
        node01.firstEdge.next.next = new ENode(2);
        node02.firstEdge = new ENode(9);
        node02.firstEdge.next = new ENode(6);
        node02.firstEdge.next.next = new ENode(5);
        node03.firstEdge = new ENode(13);
        node03.firstEdge.next = new ENode(2);
        node04.firstEdge = new ENode(7);
        node05.firstEdge = new ENode(12);
        node05.firstEdge.next = new ENode(8);
        node06.firstEdge = new ENode(5);
        node08.firstEdge = new ENode(7);
        node09.firstEdge = new ENode(11);
        node09.firstEdge.next = new ENode(10);
        node10.firstEdge = new ENode(13);
        node12.firstEdge = new ENode(9);
    }

    private void topologicalSort() throws Exception {
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        int k = 0;
        for (int i = 0; i < numVertexes; i++) {
            if (adjList[i].indegree == 0) {
                stack.push(i);
            }
        }

        while (!stack.isEmpty()) {
            int pop = stack.pop();
            System.out.println("Vertex: " + adjList[pop].data);
            count++;
            for (ENode node = adjList[pop].firstEdge; node != null; node = node.next) {
                k = node.adjvex;
                if (--adjList[k].indegree == 0) {
                    stack.push(k);
                }
            }
        }

        if (count < numVertexes) {
            throw new Exception("Failure of Topological Sorting");
        }
    }

    public static void main(String[] args) {
        TopologicalSort ts = new TopologicalSort(14);
        ts.createGraph();
        try {
            ts.topologicalSort();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
