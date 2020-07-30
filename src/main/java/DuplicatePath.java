import java.util.HashMap;
import java.util.Map;

public class DuplicatePath {
    public static void main(String[] args) {
        DuplicatePath path = new DuplicatePath();

        System.out.println(path.isPathCrossing("NES"));
    }

    public boolean isPathCrossing(String path) {
        if (path == "" | path.length() == 1) {
            return false;
        }

        // split path into char array
        char[] steps = path.toCharArray();

        int[] point = new int[2];
        point[0] = 0;
        point[1] = 0;
        Map<String, Integer> map = new HashMap<>();

        map.put(point[0]+ "," +point[1], 1);

        for (int i = 0; i < steps.length; i++) {
            switch(steps[i]){
                case 'N':
                    point[1] ++;
                    break;
                case 'S':
                    point[1] --;
                    break;
                case 'E':
                    point[0] ++;
                    break;
                case 'W':
                    point[0] --;
                    break;
            }

            if (map.get(point[0]+ "," +point[1]) != null){
                return true;
            }else{
                map.put(point[0]+ "," +point[1], 1);
            }
        }

        return false;
    }
}
