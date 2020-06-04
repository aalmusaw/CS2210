/**
 *  This class represents a Road Map.
 * @author Ali Al-Musawi
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Iterator;

public class RoadMap {

    private Node src; // the starting point
    private Node dest; // the destination point
    private Graph map; // the Graph storing the RoadMap data.
    private Stack<Node> path = new Stack<Node>(); // a data structure to save a sequence of nodes.
    private final int WIDTH; // the width of the road map as a grid.
    private final int LENGTH; // the length of the road map as a grid.
    private final int TOLL; // the price to pay to use a private road
    private final int GAIN; // the cash earned when using a reward road
    private final int INITIAL_BUDGET; // the initial cash amount to use/save.
    
    /**
     *  Class Constructor
     * @param inputFile a String containing the name (address) of a file
     *                  that has data to build a Graph.       
     * @return an instance of RoadMap
     * @throws MapException if the input file does not exist.
     */
    public RoadMap(String inputFile) throws MapException {
        try {
            int start, end; // the names of the nodes labeled as 'start' and 'end' of a path.
            Scanner scanner = new Scanner(new File(inputFile));
            scanner.nextInt(); // skip the first number
            start = scanner.nextInt();
            end = scanner.nextInt();
            WIDTH = scanner.nextInt();
            LENGTH = scanner.nextInt();
            INITIAL_BUDGET = scanner.nextInt();
            TOLL = scanner.nextInt();
            GAIN = scanner.nextInt();
            map = new Graph(WIDTH*LENGTH);
            for (int i = 0; i < WIDTH*LENGTH; i++) {
            	map.getNode(i).setMark(false);
            }
            src = map.getNode(start);
            dest = map.getNode(end);
            LinkedList<String> grid = new LinkedList<String>(); // used to save the grid data.
            while(scanner.hasNext()) {
            	grid.add(scanner.nextLine());
            }
            grid.removeFirst();
            scanner.close();
            char[][] gridMap = new char[grid.size()][]; // matrix of char holds the map data
            int gridlength = grid.size();
            for (int i = 0; i < gridlength; i++) {
            	gridMap[i] = grid.removeFirst().toCharArray();
            }
            grid = null; // clears up memory
            int verticalCounter = 0, horizontalCounter = 0; // set up edge counters.
            for (int i = 0; i < gridMap.length; i++) {
            	for (int j = 0; j < gridMap[i].length; j++) {
            		if (i % 2 == 0) {
            			if (gridMap[i][j]=='C') {
            				map.insertEdge(map.getNode(horizontalCounter-1), map.getNode(horizontalCounter), -1);
            			}
            			else if (gridMap[i][j]=='F') {
            				map.insertEdge(map.getNode(horizontalCounter-1), map.getNode(horizontalCounter), 0);
            			}
            			else if (gridMap[i][j]=='T') {
            				map.insertEdge(map.getNode(horizontalCounter-1), map.getNode(horizontalCounter), 1);
            			}
            			if (j % 2 == 0) horizontalCounter++;
            		}
            		else {
            			if (gridMap[i][j]=='C') {
            				map.insertEdge(map.getNode(verticalCounter), map.getNode(verticalCounter+WIDTH), -1);
            			}
            			else if (gridMap[i][j]=='F') {
            				map.insertEdge(map.getNode(verticalCounter), map.getNode(verticalCounter+WIDTH), 0);
            			}
            			else if (gridMap[i][j]=='T') {
            				map.insertEdge(map.getNode(verticalCounter), map.getNode(verticalCounter+WIDTH), 1);
            			}
            			if (j % 2 == 0) verticalCounter++;
            		}
            	}
            }
            gridMap = null;
        }
        catch(FileNotFoundException e) {
        	throw new MapException();
        }
    }
    
    /**
     * Accessor Method
     * @return the Graph associated with this RoadMap
     */
    public Graph getGraph() {
    	return map;
    }
    
    /**
     * Accessor Method
     * @return the name of the start node of this RoadMap
     */
    public int getStartingNode() {
    	return src.getName();
    }
    
    /**
     * Accessor Method
     * @return the name of the destination node of this RoadMap
     */
    public int getDestinationNode() {
    	return dest.getName();
    }
    
    /**
     * Accessor Method
     * @return the initial amount of balance available to the path explorer.
     */
    public int getInitialMoney() {
    	return INITIAL_BUDGET;
    }
    
    /**
     * This method finds a path (among finitely many) from the start node to destination node.
     * @param start the name of the start node
     * @param destination the name of the destination node
     * @param initialMoney the balance at the time of the algorithm being called.
     * @return an iterator of nodes with the correct path if it exists, and null otherwise.
     */
    public Iterator<Node> findPath(int start, int destination, int initialMoney) {
    	if (pathExists(start, destination, initialMoney)) return path.iterator();
    	else return null;
    }
    
    /**
     * A helper method that builds a stack of nodes consisting of a path from start to destination.
     * @param start the start node's name
     * @param destination the end node's name
     * @param initialMoney the initial balance before moving from start node.
     * @return true if there is a path from start to destination, and false otherwise.
     */
    private boolean pathExists(int start, int destination, int initialMoney) {
    	Node startN = map.getNode(start);
    	Node destN = map.getNode(destination);
    	path.push(startN);
    	startN.setMark(true);
    	if (startN.equals(destN)) return true;
    	Iterator<Edge> EdgeIterator = map.incidentEdges(startN);
    	while(EdgeIterator.hasNext()) {
    		Edge nextEdge = EdgeIterator.next();
    		Node nextNode = nextEdge.secondEndpoint();
    		if (!nextNode.getMark() && hasSufficientBalance(initialMoney, nextEdge)) {
    			if (pathExists(nextNode.getName(), destination, newBalance(initialMoney, nextEdge))) {
    				return true;
    			}
    		}
    	}
    	path.pop();
    	startN.setMark(false);
    	return false;
    }
    
    /**
     * A helper method that ensures there is enough balance to use.
     * @param currentBalance the initial balance before crossing the edge.
     * @param edge the edge to examine its price.
     * @return true if there is enough balance to use to cross the edge, false otherwise.
     */
	private boolean hasSufficientBalance(int currentBalance, Edge edge) {
		if (edge.getType()==1) {
			return currentBalance >= TOLL;
		}
		else
			return true;
	}
	
	/**
	 * A helper method that updates the balance of the path explorer.
	 * @param currentBalance the initial balance before crossing the edge.
	 * @param edge the edge to cross
	 * @return the new balance after crossing the edge.
	 */
	private int newBalance(int currentBalance, Edge edge) {
		switch(edge.getType()) {
		case (-1): return currentBalance+GAIN;
		case (1): return currentBalance-TOLL;
		default: return currentBalance;
		}
	}
    
    

}