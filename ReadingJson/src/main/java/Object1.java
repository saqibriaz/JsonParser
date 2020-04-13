import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class Object1 {
    public static void main(String[] args) throws IOException, ParseException{
        JSONParser parser=new JSONParser();
        FileReader reader=new FileReader("./src/data.json");
        Object object = parser.parse(reader);
         //For array
        JSONArray array=(JSONArray) object;

        for(int i=0; i<array.size(); i++) {
            //for object1
            JSONObject object1= (JSONObject) array.get(i);
            long id= (Long) object1.get("id");
            String s= (String) object1.get("name");
            String s1=(String) object1.get("username");
            String s2= (String) object1.get("email");
           //for object2
            JSONObject object2=(JSONObject) object1.get("address");
            String s4=(String)  object2.get("street");
            String s5=(String)  object2.get("suite");
            String s6=(String)  object2.get("city");
            String s7=(String)  object2.get("zipcode");
            //for object3
            JSONObject object3=(JSONObject) object2.get("geo");
            String s8=(String)  object3.get("lat");
            String s9=(String)  object3.get("lng");
            //for object
            String s10=(String)  object1.get("phone");
            String s11=(String)  object1.get("website");
            //for object4
            JSONObject object4=(JSONObject) object1.get("company");
            String s12=(String)  object4.get("name");
            String s13=(String)  object4.get("catchPhrase");
            String s14=(String)  object4.get("bs");

            //display the result
            System.out.println("id: "+id);
            System.out.println("name: "+s);
            System.out.println("username: "+s1);
            System.out.println("email: "+s2);

            System.out.println("Address: ");
            System.out.println(" street: "+s4);
            System.out.println(" suite: "+s5);
            System.out.println(" city: "+s6);
            System.out.println(" zipcode: "+s7);

            System.out.println(" Geo: ");
            System.out.println("  lat: "+s8);
            System.out.println("  lng: "+s9);

            System.out.println(",");
            System.out.println(" phone: "+s10);
            System.out.println(" website: "+s11);
            System.out.println(" name: "+s12);
            System.out.println(" catchphrase: "+s13);
            System.out.println(" bs: "+s14);
            System.out.println("");
            System.out.println("");

        }
    }
}
