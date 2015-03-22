


import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class Portfolio {
	private final float PRINCIPAL_VALUE = 100000f;
	private Map<Date, Float> EODPortfVal = new TreeMap<Date, Float>();
	private Map<String, Float> symbols = new TreeMap<String, Float>();
	private Map<String, Stock> stocks;
	private Calendar from;
	private Calendar to;
	private Map<String,Map<Date,float[]>> allDailyReturn;
	private float compoundReturn = 1f;

	public Portfolio(String[] symbols, float[] weight, Calendar from, Calendar to) {
		super();
		this.from = from;
		this.to = to;
		for(int i = 0;i<symbols.length;i++){
			this.symbols.put(symbols[i], weight[i]);
		}
		stocks = YahooFinance.get(symbols, true);
	}

	public String[] getSymbols() {
		return (String[]) symbols.keySet().toArray();
	}

	private Map<Date,float[]> getDailyReturn(String ticker){
		Map<Date,float[]> retVal = new TreeMap<Date,float[]>();
		ticker = ticker.toUpperCase();
		Stock stock = stocks.get(ticker);
		List<HistoricalQuote> history = stock.getHistory(this.from, this.to, Interval.DAILY);
		for(int i = 0; i<history.size()-1;i++) {
			float todayData = history.get(i).getAdjClose().floatValue();
			float yesterdayData = history.get(i+1).getAdjClose().floatValue();
			float dailyReturn = todayData/yesterdayData - 1;
			float weightedDailyReturn = dailyReturn * symbols.get(ticker);
			retVal.put(history.get(i).getDate().getTime(), new float[]{dailyReturn,weightedDailyReturn});
		}
		return retVal;
	}

	private Map<String,Map<Date,float[]>> getAllDailyReturn(){
		Map<String,Map<Date,float[]>> retVal = new TreeMap<String,Map<Date,float[]>>();
		for(String ticker : symbols.keySet()){
			retVal.put(ticker,getDailyReturn(ticker));
		}
		this.allDailyReturn = retVal;
		return retVal;
	}

	public Map<Date,float[]> getTotalReturn(){
		getAllDailyReturn();
		Map<Date,float[]> retVal = new TreeMap<Date,float[]>();
		int index = 0;
		for(String ticker : this.symbols.keySet()){
			Map<Date,float[]> dataSet = this.allDailyReturn.get(ticker);
			for(Date date : dataSet.keySet()) {
				float[] value;
				if(retVal.get(date) == null){
					value = new float[symbols.keySet().size()+1];
				} else {
					value = retVal.get(date);
				}
				value[index]=dataSet.get(date)[1];
				value[value.length-1] += value[index];
				retVal.put(date, value);
			}
			index++;
		}
		float EODPortfVal = PRINCIPAL_VALUE;
		for(Date date : retVal.keySet()){
			this.compoundReturn *= 1 + retVal.get(date)[symbols.keySet().size()];
			EODPortfVal *= 1 + retVal.get(date)[symbols.keySet().size()];
			this.EODPortfVal.put(date, EODPortfVal);
		}
		this.compoundReturn -= 1;

		return retVal;
	}

	public float getCompoundReturn() {
		return compoundReturn;
	}

	public static void calculation(String symbolsInput, String weightsInput, String fromDate, String toDate, String[] args) throws ParseException {
		
	/*public static void main(String[] args) throws ParseException {
		Scanner input = new Scanner(System.in);*/
		/*String symbolsInput = null;
		String weightsInput = null;
		String fromDate = null;
		String toDate = null;
		System.out.println("Input Tickers:\t\n");
		symbolsInput = input.nextLine().toUpperCase();
		System.out.println("Input Weights (%):\t\n");
		weightsInput = input.nextLine();
		System.out.println("Input Date From (MM/dd/yyyy):\t\n");
		fromDate = input.nextLine();
		System.out.println("Input Date To (MM/dd/yyyy):\t\n");
		toDate = input.nextLine();
		input.close();*/
		
		System.out.print(symbolsInput);
		System.out.print(weightsInput);
		System.out.print(fromDate);
		System.out.print(toDate);
		
		String[] symbols = symbolsInput.split(" ");
		String[] weightsString = weightsInput.split(" ");
		float[] weightsFloat = new float[weightsString.length];
		for(int i = 0; i < weightsString.length; i++) {
			weightsFloat[i] = (float) (Float.parseFloat(weightsString[i])/100.0);
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.setTime(dateFormat.parse(fromDate));
		to.setTime(dateFormat.parse(toDate));
		Portfolio portf = new Portfolio(symbols, weightsFloat,from, to);

		/*Total Return*/
		Map<Date,float[]> totalReturn = portf.getTotalReturn();
		System.out.print("Date\t\t");
		for(String ticker : portf.symbols.keySet()){
			System.out.print(ticker+"\t");
		}
		System.out.println("Total");
		for(Date date : totalReturn.keySet()) {
			System.out.printf(sdf.format(date));
			for(float f : totalReturn.get(date)) {
				System.out.printf("\t%.2f%%",f*100);
			}
			System.out.println("");
		}
		System.out.printf("Total Compound Return: %.4f%%\t\n",portf.getCompoundReturn()*100);
		/*End Of Day Portfolio Value*/
		System.out.println("\t\nEnd Of Day Portfolio Value");
		for(Date date : portf.EODPortfVal.keySet()) {
			System.out.printf(sdf.format(date));
			System.out.println("\t"+currency.format(portf.EODPortfVal.get(date)));
		}
		/*Graph*/
		GraphPanel plot = new GraphPanel(portf.EODPortfVal);
		plot.graph();
	}


		
	}
