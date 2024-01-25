
import java.util.*;

record Location(String description, Map<String,String> nextPlaces){}

public class LocationData {

        private static String lastPlace;

        private static final String locations= """
                road, at the end of the road, W:hill, E:well house, S:valley, N:forest
                hill, on top of hill with a view in all directions,	N:forest, E:road		
                well house,	inside a well house for a small spring,	W:road,	N:lake,	S:stream	
                valley, in a forest valley beside a tumbling stream, N:road, W:hill, E:stream	
                forest,	at the edge of a thick dark forest, S:road,	E:lake		
                lake, by an alpine lake surrounded by wildflowers, W:forest, S:well house		
                stream, near a stream with a rocky bed,	W:valley, N:well house
                lake, ar the edge of Lake Alex, E:ocean,W:forest,S:well house, N:cave              
                """;

        public static Map<String,Location> adventureMap = new HashMap<>();

        public static void getData(){


            for(String s:locations.split("\\R")){
                String[] parts = s.split(",",3);
                Arrays.asList(parts).replaceAll(String::trim);
                Map<String,String> nextPlaces = loadDirections(parts[2]);
                Location location = new Location(parts[1],nextPlaces);
                adventureMap.put(parts[0],location);
            }
            //adventureMap.forEach((k,v)->System.out.printf("%s:%s%n",k,v));
        }

        private static Map<String,String> loadDirections(String nextPlaces){

            Map<String,String> directions = new HashMap<>();
            List<String> nextSteps = Arrays.asList(nextPlaces.split(","));
            nextSteps.replaceAll(String::trim);
            for(String nextPlace:nextSteps){
                String[] splits = nextPlace.split(":");
                String indication = splits[0].trim();
                String location = splits[1].trim();
                directions.put(indication,location);
            }
            return directions;

        }

        private static void visit(Location currentLocation){

            System.out.println("You are here: "+currentLocation.description());
            System.out.println("You can go here: ");
            System.out.println(currentLocation.nextPlaces());


        }

    public static void setLastPlace(String lastPlace) {
        LocationData.lastPlace = lastPlace;
    }

    public static void move(String direction){

            var nextPlaces = adventureMap.get(lastPlace).nextPlaces();
            String nextPlace = null;
            nextPlace=nextPlaces.get(direction);
            play(nextPlace);
        }

        public static void play(String nextPlace){
            if(adventureMap.containsKey(nextPlace)){
                Location next = adventureMap.get(nextPlace);
                String lastPlace = nextPlace;
                visit(next);
            }
        }
}
