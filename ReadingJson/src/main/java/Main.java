import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import java.io.File;
import java.io.IOException;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        File file=new File("src/data.json");
        ObjectMapper mapper=new ObjectMapper();
        Scanner scanner=new Scanner(System.in);
        try {

            LinkedHashMap<String,List<String>> map= new LinkedHashMap<String, List<String>>();
            JsonNode node =mapper.readTree(file);
            getKeys("",node, map);
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                System.out.println(entry.getKey() );
            }
            System.out.println();
            while (true) {
                List<String> n=map.get(scanner.next());
                for (String s:n) {
                    System.out.println(s);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void getKeys(String currentpath,JsonNode node,LinkedHashMap map){
        if(node.isObject()){
            ObjectNode objectNode=(ObjectNode) node;
            Iterator<Map.Entry<String, JsonNode>> it=objectNode.fields();
            String prefix=currentpath.isEmpty()?"":currentpath+".";
            while (it.hasNext()){
                SortedMap.Entry<String,JsonNode> iter=it.next();
                getKeys(prefix+iter.getKey(),iter.getValue(),map);
            }
        }else if (node.isArray()){
            ArrayNode arrayNode=(ArrayNode) node;
            for(int i=0; i<arrayNode.size(); i++){
                getKeys(currentpath,arrayNode.get(i),map);
            }
        }else if (node.isValueNode()) {
            ValueNode valueNode = (ValueNode) node;
            List<String> values = (List<String>) map.getOrDefault(currentpath, new ArrayList<>());
            values.add(valueNode.asText());
            map.put(currentpath, values);
        }
    }
}