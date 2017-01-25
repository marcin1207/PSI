
package hebb;

/**
 * Chapter 4: Machine Learning
 * 
 * Hebb: Learn, using Hebb's rule.
 */
public class HEBB {

	/**
	 * Main method just instanciates a delta object and calls run.
	 * 
	 * @param args
	 *            Not used
	 */
	public static void main(final String args[]) {
		final HEBB delta = new HEBB();
		delta.run();

	}

	double w1;//waga 1
	double w2;// waga 2

	/**
	 * Learning rate
	 */
	double rate = 1.0;

	/**
	 * Current epoch #
	 */
	int epoch = 1;

	public HEBB() {
		this.w1 = 1;
		this.w2 = -1;
	}

	/**
	 * Process one epoch. Here we learn from all three training samples and then
	 * update the weights based on error.
	 */

	protected void epoch() {
		System.out.println("***Beginning Epoch #" + this.epoch + "***");
		presentPattern(-1, -1);
		presentPattern(-1, 1);
		presentPattern(1, -1);
		presentPattern(1, 1);
		this.epoch++;
	}

	/**
	 * Present a pattern and learn from it.
	 * 
	 * @param i1
	 *            Input to neuron 1
	 * @param i2
	 *            Input to neuron 2
	 * @param i3
	 *            Input to neuron 3
	 * @param anticipated
	 *            The anticipated output
	 */
	protected void presentPattern(final double i1, final double i2) {
		double result;
		double delta;

		// run the net as is on training data
		// and get the error
		System.out.print("Presented [" + i1 + "," + i2 + "]");
		result = recognize(i1, i2);
		System.out.print(" result=" + result);

		// adjust weight 1
		delta = trainingFunction(this.rate, i1, result);
		this.w1 += delta;
		System.out.print(",delta w1=" + delta);

		// adjust weight 2
		delta = trainingFunction(this.rate, i2, result);
		this.w2 += delta;
		System.out.println(",delta w2=" + delta);

	}

	/**
	 * @param i1
	 *            Input to neuron 1
	 * @param i2
	 *            Input to neuron 2
	 * @param i3
	 *            Input to neuron 3
	 * @return the output from the neural network
	 */
	protected double recognize(final double i1, final double i2) {
		final double a = (this.w1 * i1) + (this.w2 * i2);
		return (a * .5);
	}

	/**
	 * This method loops through 10 epochs.
	 */
	public void run() {
		for (int i = 0; i < 10; i++) {
			epoch();
		}
	}

	/**
	 * The learningFunction implements the delta rule. This method will return
	 * the weight adjustment for the specified input neuron.
	 * 
	 * @param rate
	 *            The learning rate
	 * @param input
	 *            The input neuron we're processing
	 * @param error
	 *            The error between the actual output and anticipated output.
	 * @return The amount to adjust the weight by.
	 */
	protected double trainingFunction(final double rate, final double input,
			final double output) {
		return rate * input * output;
	}
}