package neuron;

import java.util.Random;

/**
 *
 * @author Orhan Demirel
 */
public class Perceptron {

    double[] wagi;
    double prog;
    public void Train(double[][] inputs, int[] outputs, double prog, double lrate, int epoch)
    {

        this.prog = prog;
        int n = inputs[0].length;
        int p = outputs.length;
        wagi = new double[n];
        Random r = new Random();
        
        //initialize wagi
        for(int i=0;i<n;i++)
        {
            wagi[i] = r.nextDouble();
        }

        for(int i=0;i<epoch;i++)
        {
            int totalError = 0;
            for(int j =0;j<p;j++)
            {
                int output = Output(inputs[j]);
                int error = outputs[j] - output;
                
                totalError +=error;
               
                for(int k = 0;k<n;k++)
                {
                    double delta = lrate * inputs[j][k] * error;
                    wagi[k] += delta;
                }
                
                
            }
            if(totalError == 0)
                break;
        }

    }

    public int Output(double[] input)
    {
        double sum = 0.0;
        for(int i=0;i<input.length;i++)
        {
            sum += wagi[i]*input[i];
        }

        if(sum>prog)
            return 1;
        else
            return 0;
    }

}