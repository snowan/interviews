/**
 use 2 hashmap to hold station and t info. 
 id2Node hashmap: key: id, value: station and time info
 
 station2Time hashamp: key: startStation name + endStation name, value: list of checkin time and checkout time
 
*/
class UndergroundSystem {
    Map<Integer, Node> id2Node;
    Map<String, List<int[]>> station2Time;
    public UndergroundSystem() {
        id2Node = new HashMap<>();
        station2Time = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        id2Node.put(id, new Node(id, stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        if (!id2Node.containsKey(id)) return;
        Node node = id2Node.get(id);
        String key = node.name + "," + stationName;
        station2Time.computeIfAbsent(key, l -> new ArrayList<>()).add(new int[]{node.t, t});
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + "," + endStation;
        if (!station2Time.containsKey(key)) return 0;
        double sum = 0;
        int size = station2Time.get(key).size();
        for (int[] t : station2Time.get(key)) {
            sum += (t[1] - t[0]);
        }
        return sum / size;
    }
}
class Node {
    int id;
    String name;
    int t;
    public Node(int id, String name, int t) {
        this.id = id;
        this.name = name;
        this.t = t;
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
