package PamakBook;


import javax.swing.*;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.shortestpath.UnweightedShortestPath;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;

import java.awt.*;
import java.util.ArrayList;




public class FriendsGraphFrame extends JFrame {
	
	public FriendsGraphFrame(ArrayList<User> users) {

        setTitle("Friends Graph");
        setSize(700, 700);
        setLocationRelativeTo(null);
        
        Graph<User, String>graph = new UndirectedSparseGraph();
        
        for (User u: users) {
        	graph.addVertex(u);        
        	}
        
        int edgeCount = 0;
        
        for (User u: users) {
        	for (User f : u.getFriends()) {
        		if(!graph.isNeighbor(u, f)) {
        			graph.addEdge("E"+ edgeCount++, u, f);
        		}
        	}
        }
        
        VisualizationViewer<User, String> vv =
                new VisualizationViewer<>(new edu.uci.ics.jung.algorithms.layout.CircleLayout<>(graph));
        CircleLayout<User, String> layout =
                new CircleLayout<>(graph);

        layout.setRadius(180);   
        layout.setSize(new Dimension(400, 400));
        vv.setPreferredSize(new Dimension(500, 500));

        vv.getRenderContext().setVertexLabelTransformer(User::getName);
        

        add(vv, BorderLayout.CENTER);
        
        double diameter = calculateDiameter(graph);
        JLabel diameterLabel =
                new JLabel(String.format(java.util.Locale.US, "Graph Diameter: %.1f" , diameter));

        JPanel bottomLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomLeftPanel.add(diameterLabel);
        bottomLeftPanel.setBorder(
                BorderFactory.createEmptyBorder(5, 10, 5, 5)
        );
        add(bottomLeftPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
        
        private double calculateDiameter(Graph<User, String> graph) {

            int diameter = 0;
            UnweightedShortestPath<User, String> usp = new UnweightedShortestPath<>(graph);

            for (User u1 : graph.getVertices()) {
                for (User u2 : graph.getVertices()) {
                    if (!u1.equals(u2)) {
                        Number dist = usp.getDistance(u1, u2);
                        if (dist != null) {
                            diameter = Math.max(diameter, dist.intValue());
                        }
                    }
                }
            }
            return (double) diameter;
        }

        
    

	 
        

}
