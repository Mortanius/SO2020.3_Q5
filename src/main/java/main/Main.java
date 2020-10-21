package main;

import org.apache.commons.cli.*;

public class Main {
    private static Options argsOptions() {
        Options options = new Options();
        Option consumidores = new Option("c", "consumidores", true, "Numero total de consumidores");
        consumidores.setRequired(true);
        options.addOption(consumidores);
        Option banheiros = new Option("b", "banheiros", true, "Numero total de banheiros");
        banheiros.setRequired(true);
        options.addOption(banheiros);
        Option iteracoes = new Option("i", "iteracoes", true, "Numero total de iteracoes");
        banheiros.setRequired(true);
        options.addOption(iteracoes);
        return options;
    }

    public static void main(String[] args) {
        //String[] mockArgs = {"-n", "10"};
        Options options = argsOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            //cmd = parser.parse(options, mockArgs);
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            HelpFormatter helpF = new HelpFormatter();
            System.err.println(e.getMessage());
            helpF.printHelp("CafeBanheiro [opcoes]", options);
            System.exit(1);
        }
        int consumidores = 0, banheiros = 0;
        long iteracoes = 0;
        String opt = "", val = "";
        try {
            opt = "c";
            val = cmd.getOptionValue(opt);
            consumidores = Integer.parseUnsignedInt(val);

            opt = "b";
            val = cmd.getOptionValue(opt);
            banheiros = Integer.parseUnsignedInt(val);

            opt = "i";
            val = cmd.getOptionValue(opt);
            iteracoes = Long.parseUnsignedLong(val);
        } catch (NumberFormatException e) {
            System.err.println("Valor " + val + " para " + opt + " invalido");
        }
        System.out.println("Consumidores: "+consumidores+" | Banheiros: "+banheiros+" | Iteracoes: "+iteracoes);
        Cafeteria cafeteria = new Cafeteria(consumidores, banheiros, iteracoes);
        cafeteria.run();
    }
}
