import java.text.ParseException;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Group;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

//import backtestingapp.GraphPanel;


public class StockInputInfo {

	protected Shell shell;
	private Text stock1;
	private Text stock2;
	private Text stock3;
	private Text stock4;
	private Text stock5;
	private Text stock6;
	private Text weight6;
	private Text weight5;
	private Text weight4;
	private Text weight3;
	private Text weight2;
	private Text weight1;
	private Label lblNewLabel;
	private Label lblAllocation_1;
	private Group group;
	private Label lblStockTradingStrategy;
	private Text begDate;
	private Label lblBeginingHistoricalDate;
	private Text endDate;
	private Label lblEndingHistoricalDate;
	private Group group_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			StockInputInfo window = new StockInputInfo();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(514, 347);
		shell.setText("Stock Trading Strategy Analysis");
		
		group = new Group(shell, SWT.NONE);
		group.setBounds(10, 51, 478, 112);
		
		stock1 = new Text(group, SWT.BORDER);
		stock1.setBounds(92, 41, 56, 21);
		
		stock2 = new Text(group, SWT.BORDER);
		stock2.setBounds(154, 41, 56, 21);
		
		stock3 = new Text(group, SWT.BORDER);
		stock3.setBounds(216, 41, 56, 21);
		
		stock4 = new Text(group, SWT.BORDER);
		stock4.setBounds(278, 41, 56, 21);
		
		stock5 = new Text(group, SWT.BORDER);
		stock5.setBounds(340, 41, 56, 21);
		
		stock6 = new Text(group, SWT.BORDER);
		stock6.setBounds(402, 41, 56, 21);
		
		weight6 = new Text(group, SWT.BORDER);
		weight6.setBounds(402, 78, 56, 21);
		
		weight5 = new Text(group, SWT.BORDER);
		weight5.setBounds(340, 78, 56, 21);
		
		weight4 = new Text(group, SWT.BORDER);
		weight4.setBounds(278, 78, 56, 21);
		
		weight3 = new Text(group, SWT.BORDER);
		weight3.setBounds(216, 78, 56, 21);
		
		weight2 = new Text(group, SWT.BORDER);
		weight2.setBounds(154, 78, 56, 21);
		
		weight1 = new Text(group, SWT.BORDER);
		weight1.setBounds(92, 78, 56, 21);
		
		Label label_0 = new Label(group, SWT.NONE);
		label_0.setBounds(93, 20, 55, 15);
		label_0.setAlignment(SWT.CENTER);
		label_0.setText("Stock #1");
		
		Label lblStock = new Label(group, SWT.NONE);
		lblStock.setBounds(155, 20, 55, 15);
		lblStock.setAlignment(SWT.CENTER);
		lblStock.setText("Stock #2");
		
		Label lblStock_1 = new Label(group, SWT.NONE);
		lblStock_1.setBounds(217, 20, 55, 15);
		lblStock_1.setAlignment(SWT.CENTER);
		lblStock_1.setText("Stock #3");
		
		Label lblStock_2 = new Label(group, SWT.NONE);
		lblStock_2.setBounds(279, 20, 55, 15);
		lblStock_2.setAlignment(SWT.CENTER);
		lblStock_2.setText("Stock #4");
		
		Label lblStock_3 = new Label(group, SWT.NONE);
		lblStock_3.setBounds(341, 20, 55, 15);
		lblStock_3.setAlignment(SWT.CENTER);
		lblStock_3.setText("Stock #5");
		
		Label lblStock_4 = new Label(group, SWT.NONE);
		lblStock_4.setBounds(403, 20, 55, 15);
		lblStock_4.setAlignment(SWT.CENTER);
		lblStock_4.setText("Stock #6");
		
		lblAllocation_1 = new Label(group, SWT.NONE);
		lblAllocation_1.setAlignment(SWT.CENTER);
		lblAllocation_1.setBounds(12, 68, 74, 31);
		lblAllocation_1.setText("Allocation\r\n(as decimal)\r\n");
		
		lblNewLabel = new Label(group, SWT.NONE);
		lblNewLabel.setBounds(12, 47, 74, 15);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setText("Ticker");
		
		lblStockTradingStrategy = new Label(shell, SWT.NONE);
		lblStockTradingStrategy.setAlignment(SWT.CENTER);
		lblStockTradingStrategy.setFont(SWTResourceManager.getFont("Segoe UI", 19, SWT.BOLD));
		lblStockTradingStrategy.setBounds(10, 10, 478, 35);
		lblStockTradingStrategy.setText("Stock Trading Strategy Analysis");
		
		begDate = new Text(shell, SWT.BORDER);
		begDate.setBounds(100, 219, 131, 21);
		
		lblBeginingHistoricalDate = new Label(shell, SWT.NONE);
		lblBeginingHistoricalDate.setAlignment(SWT.CENTER);
		lblBeginingHistoricalDate.setBounds(100, 183, 131, 30);
		lblBeginingHistoricalDate.setText("Begining Historical Date \r\n(mm/dd/yyyy)");
		
		endDate = new Text(shell, SWT.BORDER);
		endDate.setBounds(273, 219, 131, 21);
		
		lblEndingHistoricalDate = new Label(shell, SWT.NONE);
		lblEndingHistoricalDate.setText("Ending Historical Date \r\n(mm/dd/yyyy)");
		lblEndingHistoricalDate.setAlignment(SWT.CENTER);
		lblEndingHistoricalDate.setBounds(273, 183, 131, 30);
		
		group_1 = new Group(shell, SWT.NONE);
		group_1.setBounds(86, 169, 329, 81);
		
		Button submitButton = new Button(shell, SWT.NONE);
		submitButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String ticker1 = stock1.getText();
				String ticker2 = stock2.getText();
				String ticker3 = stock3.getText();
				String ticker4 = stock4.getText();
				String ticker5 = stock5.getText();
				String ticker6 = stock6.getText();
				String symbolsInput = ticker1+" "+ticker2+" "+ticker3+" "+ticker4+" "+ticker5+" "+ticker6;
				
				String weightStr1 = weight1.getText();
				String weightStr2 = weight2.getText();
				String weightStr3 = weight3.getText();
				String weightStr4 = weight4.getText();
				String weightStr5 = weight5.getText();
				String weightStr6 = weight6.getText();
				String weightsInput = weightStr1+" "+weightStr2+" "+weightStr3+" "+weightStr4+" "+weightStr5+" "+weightStr6;
				
				String fromDate = begDate.getText();
				String toDate = endDate.getText();
				
				/*System.out.print(symbolsInput);
				System.out.print(weightsInput);
				System.out.print(fromDate);
				System.out.print(toDate);*/
				
				try {
					Portfolio.calculation(symbolsInput, weightsInput, toDate, fromDate, null);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		submitButton.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		submitButton.setBounds(209, 263, 92, 35);
		submitButton.setText("Submit");
		
		

	}
}
