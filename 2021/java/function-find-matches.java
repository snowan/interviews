/**
from leetcode post: https://leetcode.com/discuss/interview-question/759611/confluent-senior-software-engineer-phone-interview

Suppose you are building a library and have following definition of a function and two methods register and findMatches.
Register method registers a function and its argumentTypes in the library and findMatches accepts an input argument list and tries to find all the functions that match this input argument list.

class Function {
	String name;
	List<String> argumentTypes;
	boolean isVariadic;
	
	Function(String name, List<String> argumentTypes, boolean isVariadic) {
		this.name = name;
		this.argumentTypes = argumentTypes;
		this.isVariadic = isVariadic;
	}
}

class FunctionLibrary {
	public void register(Set<Function> functionSet)  {
		// implement this method.
	}
	
	public List<Function> findMatches(List<String> argumentTypes) {
		// implement this method.
		return null;
	}
}
Note:
If a function is marked as isVariadic=true, then the last argument can occur one or more number of times.

Example:
FuncA: [String, Integer, Integer]; isVariadic = false
FuncB: [String, Integer]; isVariadic = true
FuncC: [Integer]; isVariadic = true
FuncD: [Integer, Integer]; isVariadic = true
FuncE: [Integer, Integer, Integer]; isVariadic = false
FuncF: [String]; isVariadic = false
FuncG: [Integer]; isVariadic = false

findMatches({String}) -> [FuncF]
findMatches({Integer}) -> [FuncC, FuncG]
findMatches({Integer, Integer, Integer, Integer}) -> [FuncC, FuncD]
findMatches({Integer, Integer, Integer}) -> [FuncC, FuncD, FuncE]
findMatches({String, Integer, Integer, Integer}) -> [FuncB]
findMatches({String, Integer, Integer}) -> [FuncA, FuncB]
*/
public class Main {
    public static void main(String[] args) {
        Set<Function> functionSet = Set.of(
            new Function("FuncA", Arrays.asList("String", "Integer", "Integer"), false),
            new Function("FuncB", Arrays.asList("String", "Integer"), true),
            new Function("FuncC", Arrays.asList("Integer"), true),
            new Function("FuncD", Arrays.asList("Integer", "Integer"), true),
            new Function("FuncE", Arrays.asList("Integer", "Integer", "Integer"), false),
            new Function("FuncF", Arrays.asList("String"), false),
            new Function("FuncG", Arrays.asList("Integer"), false));
        
        FunctionLibrary fl = new FunctionLibrary();
        fl.register(functionSet);
        print(fl.findMatches(Arrays.asList("String")));
        print(fl.findMatches(Arrays.asList("Integer")));
        print(fl.findMatches(Arrays.asList("Integer", "Integer", "Integer", "Integer")));
        print(fl.findMatches(Arrays.asList("Integer", "Integer", "Integer")));
        print(fl.findMatches(Arrays.asList("String", "Integer", "Integer", "Integer")));
        print(fl.findMatches(Arrays.asList("String", "Integer", "Integer")));
        
    }
    
    private static void print(List<Function> list) {
        System.out.print("[");
        list.forEach(func -> {
            System.out.print(func.name + ", ");
        });
        System.out.print("]");
        System.out.println();
    }
    
}
class FunctionLibrary {
    public FunctionLibrary() {}
    Map<String, Map<Integer, Set<Function>>> map = new HashMap<>();
	public void register(Set<Function> functionSet)  {
        String key = "";
        for (Function func : functionSet) {
            key = "";
            // construct key
            for (String arg : func.argumentTypes) {
                key += arg + "-";
            }
            // System.out.println("original key= " + key);
            Map<Integer, Set<Function>> funcMap = map.get(key);
            if (funcMap == null) funcMap = new HashMap<>();
            Integer sign = func.isVariadic ? 1 : 0;
            funcMap.computeIfAbsent(sign, s -> new HashSet<>()).add(func);
            map.put(key, funcMap);
        }
	}
	
	public List<Function> findMatches(List<String> argumentTypes) {
        List<Function> res = new ArrayList<>();
        int idx = 0;
        // find index where prev and curr diff arg type (aka has variadic arguments)
        int len = argumentTypes.size();
        for (idx = len - 2; idx >= 0; idx--) {
            if (argumentTypes.get(idx) != argumentTypes.get(idx + 1)) break;
        }
        String key = "";
        // construct key until idx (non variadic arguments)
        for (int i = 0; i <= idx; i++) {
            key += argumentTypes.get(i) + "-";
        }
        // indicates that no variadic arguments
        if (idx == len - 2) {
            key += argumentTypes.get(idx + 1) + "-";
            // System.out.println("key= " + key);
            Set<Function> funcs = new HashSet<>();
            if (map.containsKey(key)) {
                if (map.get(key).get(0) != null)
                    res.addAll(new ArrayList<>(map.get(key).get(0)));
                if (map.get(key).get(1) != null)
                    res.addAll(new ArrayList<>(map.get(key).get(1)));
            }
            
        } else { // has variadic arguments
            for (int i = idx + 1; i < len; i++) {
                key += argumentTypes.get(i) + "-";
                // System.out.println("key= " + key);
                if (map.containsKey(key) && map.get(key).get(1) != null)
                    res.addAll(new ArrayList<>(map.get(key).get(1)));
            }
            // add final exact the same key with noon-variadic argument funcs
            if (map.containsKey(key) && map.get(key).get(0) != null)
                res.addAll(new ArrayList<>(map.get(key).get(0)));
        }

		return res;
	}
}

class Function {
	String name;
	List<String> argumentTypes;
	boolean isVariadic;
	
	Function(String name, List<String> argumentTypes, boolean isVariadic) {
		this.name = name;
		this.argumentTypes = argumentTypes;
		this.isVariadic = isVariadic;
	}
}
