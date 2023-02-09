import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

/**
 * 
 */

/**
 * @author Luis Moncada (lumo03)
 *
 */
public class PrimeNumberTaskExample {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final JFrame frame = new JFrame();
		frame.setSize(200, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		final JTextArea textArea = new JTextArea();
		final JProgressBar progressBar = new JProgressBar(0, 100);

		final JPanel mainPanel = new JPanel();

		// mainPanel.add(textAreaWrapper.getTextArea());
		mainPanel.add(textArea);
		mainPanel.add(progressBar);

		frame.add(mainPanel);

		frame.setVisible(true);

		final PrimeNumberTask task = new PrimeNumberTask(textArea, 200);
		task.addPropertyChangeListener(
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (evt.getPropertyName().equals("progress")) {
							progressBar.setValue((Integer) evt.getNewValue());
						}
					}
				}
				);
		task.execute();
		try {
			System.out.println(task.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}



class PrimeNumberTask extends SwingWorker<List<Integer>, Integer> {


	final JTextArea textArea;
	final int numbersToFind;


	PrimeNumberTask(JTextArea textArea, int numbersToFind) {
		this.textArea = textArea;
		this.numbersToFind = numbersToFind;
	}


	@Override
	protected List<Integer> doInBackground() {
		final List<Integer> numbers = new ArrayList<>();
		int number;

		for (int i = 0; i < numbersToFind && !isCancelled(); i++) {
			number = nextPrimeNumber();
			numbers.add(number);
			publish(number);
			setProgress(100 * numbers.size() / numbersToFind);
		}

		return numbers;
	}


	@Override
	protected void process(List<Integer> chunks) {
		for (int number : chunks) {
			textArea.append(number + ", ");
		}
	}


	private int nextPrimeNumber() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return 0;
	}

}