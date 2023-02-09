import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

/**
 * 
 */

/**
 * @author Luis Moncada (lumo03)
 *
 */
public class MeaningOfLifeFinderExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final JFrame frame = new JFrame();
		frame.setSize(200, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		final JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		frame.add(label);
		
		final MeaningOfLifeFinder finder = new MeaningOfLifeFinder(label);
		finder.execute();
		
		frame.setVisible(true);
	}

}

class MeaningOfLifeFinder extends SwingWorker<String, Object> {
	
	final JLabel label;
	
	MeaningOfLifeFinder(JLabel label) {
		this.label = label;
	}
	
	@Override
	protected String doInBackground() {
		return findTheMeaningOfLife();
	}

	@Override
	protected void done() {
		try {
			label.setText(get());
		} catch (Exception ingore) {}
	}
	
	private String findTheMeaningOfLife() {
		return "The meaning of life is 42.";
	}
}
