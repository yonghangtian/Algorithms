public class ReverseLeftString {
    public static void main(String[] args) {
        String a = "ab1234";

        ReverseLeftString str = new ReverseLeftString();

        System.out.println(str.reverseLeftWords(a, 2));
    }
    public String reverseLeftWords(String s, int n) {
        StringBuilder sb = new StringBuilder();

        sb.append(s.substring(n, s.length()));
        sb.append(s.substring(0, n));

        return sb.toString();
    }
}
