package c18Lang;

// Demonstrate Runtime.Version.
class VerDemo2 {
    public static void main(String args[]) {
        Runtime.Version ver = Runtime.version();

        // Display individual version numbers.
        System.out.println("Major version: " + ver.major());
        System.out.println("Minor version: " + ver.minor());
        System.out.println("Security version: " + ver.security());
    }
}
