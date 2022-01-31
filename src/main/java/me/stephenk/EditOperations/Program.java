package me.stephenk.EditOperations;

public class Program {
    public static void main(String[] args) {
        if (args.length < 2) {
            printUsage();
            return;
        }
        System.out.println(Levenshtein.distance(args[0], args[1]));
    }

    private static void printUsage() {
        var version = Program.class.getPackage().getSpecificationVersion() ;
        System.out.println("Diff tool - version " + version);
        System.out.println("java -jar \"EditOperations-vx.y\" string1 string2");
    }
}
