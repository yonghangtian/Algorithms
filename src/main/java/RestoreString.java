class RestoreString {
    public static void main(String[] args) {
        String s = "codeleet";
        System.out.println(s.charAt(1));
        int[] ind = new int[]{4,5,6,7,0,2,1,3};
        System.out.println(restoreString(s, ind));
    }
    public static String restoreString(String s, int[] ind) {
        char[] result = new char[s.length()];

        for (int i = 0; i < ind.length; i++) {
            int id = ind[i];

            result[id] = s.charAt(i);
        }

        return String.copyValueOf(result);
    }
}
