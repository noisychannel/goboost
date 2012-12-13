import jsat.classifiers.CategoricalData;
import jsat.classifiers.ClassificationDataSet;
import jsat.distributions.multivariate.NormalM;
import jsat.graphing.CategoryPlot;
import jsat.linear.DenseMatrix;
import jsat.linear.DenseVector;
import jsat.linear.Matrix;
import jsat.linear.Vec;

import javax.swing.*;
import java.util.Random;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * It can often be useful to generate synthetic data sets to test out classifiers on, and get a feel for how they work.
 *
 * @author Edward Raff
 */
public class GenerateGausianMixtureData
{
    public static void main(String[] args)
    {
        //We create a new data set. This data set will have 2 dimensions so we can visualize it, and 4 target class values
        ClassificationDataSet dataSet = new ClassificationDataSet(3, new CategoricalData[0], new CategoricalData(2));

        //We can generate data from a multivarete normal distribution. The 'M' at the end stands for Multivariate 
        NormalM normal;

        //The normal is specifed by a mean and covariance matrix. The covariance matrix must be symmetric. 
        //We use a simple covariance matrix for each data point for simplicity
        Matrix covariance = new DenseMatrix(new double[][]
                {
                        {1.0, 0.4, 0.5}, //Try altering these values to see the change!
                        {0.4, 1.5, 1.2},
                        {0.5, 1.2, 3.3}  //Just make sure its still symetric!
                });

        //And we create 4 different means
        Vec mean0 = DenseVector.toDenseVec(0.0, 0.5, 3.3);
        Vec mean1 = DenseVector.toDenseVec(0.8, 4.0, 1.5);
        //      Vec mean2 = DenseVector.toDenseVec(4.0, 0.3, 2.2);
        //      Vec mean3 = DenseVector.toDenseVec(4.0, 4.8, 6.6);

        Vec[] means = new Vec[]{mean0, mean1};//, mean2, mean3};

        //We now generate out data
        for (int i = 0; i < means.length; i++)
        {
            normal = new NormalM(means[i], covariance);
            for (Vec sample : normal.sample(300, new Random()))
            {
                dataSet.addDataPoint(sample, new int[0], i);
            }
        }

       /* for (DataPoint d : dataSet.getDataPoints())
        {

            d.getCategoricalValue()
        }*/


        CategoryPlot plot = new CategoryPlot(dataSet);

        JFrame jFrame = new JFrame("2D Visualization");
        jFrame.add(plot);
        jFrame.setSize(400, 400);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}