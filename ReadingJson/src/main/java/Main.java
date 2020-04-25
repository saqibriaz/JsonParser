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
        try {

            LinkedHashMap<String,String> map= new LinkedHashMap<String, String>();
            JsonNode node =mapper.readTree(file);
            getKeys("",node, map);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println(entry.getKey() );
            }

            Scanner scanner=new Scanner(System.in);
            while (true) {
                System.out.println(map.get(scanner.next()));
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
                getKeys(currentpath,arrayNode.get(0),map);
            }
        }
        else if(node.isValueNode()) {
            ValueNode valueNode=(ValueNode) node;
            map.put(currentpath,valueNode.asText());
        }
    }
}
