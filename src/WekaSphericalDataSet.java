import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class WekaSphericalDataSet
{
    private static Random rnd = new Random();

    public static void main(String[] args)
    {

        BufferedWriter bw;
        File file = new File("E:\\code\\MachineLearning\\ML-CS475\\res3\\weka\\spherical\\spherical.train.arff");
        double radius = 0.6;

        try
        {
            if (!file.exists()) file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);

            writeDefinitionToArffFile(bw);

            for (int example = 0; example < 500; example++)
            {
                String label = "A";
                double[] X = new double[10];

                for (int i = 1; i < X.length; i++)
                {
                    X[i] = rnd.nextDouble();
                    if (Math.abs(X[i]) > radius && label.equalsIgnoreCase("A"))
                        label = "B";
                }

                // Add 10% noise
                if (example % 5 == 0)
                {
                    label = toggle(label);
                }

                String featureVector = "";
                for (double aX : X)
                {
                    if (featureVector.equals(""))
                    {
                        featureVector = featureVector + aX;
                    }
                    else
                    {
                        featureVector += "," + aX;
                    }
                }

                featureVector += "," + label;
                bw.write(featureVector);
                bw.newLine();
            }

            bw.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }


    }

    private static void writeDefinitionToArffFile(BufferedWriter bw) throws IOException
    {
        String definition = "";
        definition += "@RELATION Gaussian \n";
        definition += "@ATTRIBUTE f1 NUMERIC\n";
        definition += "@ATTRIBUTE f2 NUMERIC\n";
        definition += "@ATTRIBUTE f3 NUMERIC\n";
        definition += "@ATTRIBUTE f4 NUMERIC\n";
        definition += "@ATTRIBUTE f5 NUMERIC\n";
        definition += "@ATTRIBUTE f6 NUMERIC\n";
        definition += "@ATTRIBUTE f7 NUMERIC\n";
        definition += "@ATTRIBUTE f8 NUMERIC\n";
        definition += "@ATTRIBUTE f9 NUMERIC\n";
        definition += "@ATTRIBUTE f10 NUMERIC\n";
        definition += "@ATTRIBUTE class {A,B}\n\n";
        definition += "@Data";
        bw.write(definition);
        bw.newLine();
    }

    static String toggle(String label)
    {
        return label.equalsIgnoreCase("A") ? "B" : "A";
    }

}





