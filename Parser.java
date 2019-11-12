package parserprogram;

public class Parser {
	private String equation = "", firstnumber = "", secondnumber = "";
	private char operator = ' ';

	public void formatInput(String a) {
		if (a.equals("")) {
			throw new IllegalArgumentException();
		}
		equation = a;
		equation = equation.trim();

	}

	public void checkEqualSign() {
		if (equation.charAt(0) != '=') {
			throw new ArithmeticException();
		}
	}

	public void findFirstandOperator() {
		boolean period = false;
		for (int i = 1; i < equation.length(); i++) {

			if (equation.charAt(i) == ' ') {
				continue;
			} else if (Character.isDigit(equation.charAt(i)) || (firstnumber == "" && equation.charAt(i) == '-')) {
				firstnumber = firstnumber + equation.charAt(i);
			} else if (equation.charAt(i) == '.' && period == false) {
				firstnumber = firstnumber + equation.charAt(i);
				period = true;
			} else if (equation.charAt(i) == '/' || equation.charAt(i) == '*'
					|| (equation.charAt(i) == '-' && firstnumber != "") || equation.charAt(i) == '+') {
				operator = equation.charAt(i);
				findSecond(i);
				break;
			} else {
				throw new IllegalArgumentException();
			}
		}
	}

	public void findSecond(int operatorindex) {
		boolean period = false;
		for (int j = operatorindex + 1; j < equation.length(); j++) {

			if (equation.charAt(j) == ' ') {
				continue;
			} else if ((equation.charAt(j) == '-' && secondnumber.equals(""))
					|| Character.isDigit(equation.charAt(j))) {
				secondnumber = secondnumber + equation.charAt(j);
			} else if (equation.charAt(j) == '.' && period == false) {
				secondnumber = secondnumber + equation.charAt(j);
				period = true;
			} else {
				throw new IllegalArgumentException();
			}
		}

	}

	public double getResult() {
		double answer = 0;
		if (firstnumber == "" || secondnumber == "") {
			throw new IllegalArgumentException();
		}
		if (operator == '+') {
			answer = Double.valueOf(firstnumber) + Double.valueOf(secondnumber);
		} else if (operator == '-') {
			answer = Double.valueOf(firstnumber) - Double.valueOf(secondnumber);
		} else if (operator == '*') {
			answer = Double.valueOf(firstnumber) * Double.valueOf(secondnumber);
		} else if (operator == '/' && Double.valueOf(secondnumber) == 0) {
			throw new IllegalArgumentException();
		} else if (operator == '/' && Double.valueOf(secondnumber) != 0) {
			answer = Double.valueOf(firstnumber) / Double.valueOf(secondnumber);
		} else {
			throw new IllegalArgumentException();
		}
		return answer;
	}

	public void reset() {
		equation = "";
		firstnumber = "";
		secondnumber = "";
		operator = ' ';
	}

}
