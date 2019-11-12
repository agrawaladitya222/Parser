/*Aditya Agrawal
 * 13 November 2019
 * Parser Program
 * Parser program will calculate a one operation equation based on a user input */
package parserprogram;

import javax.swing.*;
import BreezySwing.*;

public class GUI extends GBFrame {
	private JTextField input;
	private JLabel inputLabel;
	private JLabel outputLabel;
	private JButton calculate;
	private JButton reset;
	private JButton end;
	Parser parser = new Parser();

	public GUI() {
		input = addTextField("", 1, 2, 1, 1);
		inputLabel = addLabel("Input", 1, 1, 1, 1);
		outputLabel = addLabel("", 2, 1, 1, 1);
		calculate = addButton("Calculate", 3, 1, 1, 1);
		reset = addButton("Reset", 3, 2, 1, 1);
		end = addButton("End", 4, 1, 1, 1);
		reset.setEnabled(false);

	}

	public static void main(String[] args) {
		JFrame frm = new GUI();
		frm.setTitle("Parser");
		frm.setSize(600, 600);
		frm.setVisible(true);

	}

	public void buttonClicked(JButton button) {
		if (button == calculate) {
			try {
				parser.formatInput(input.getText());
				parser.checkEqualSign();
				parser.findFirstandOperator();
				outputLabel.setText("=" + parser.getResult());
				reset.setEnabled(true);
				calculate.setEnabled(false);
			} catch (IllegalArgumentException e) {
				messageBox("Invalid input. Please try again.");
				parser.reset();
				reset.setEnabled(false);
				calculate.setEnabled(true);
				input.setText("=");
				input.requestFocus();
			} catch (ArithmeticException e) {
				messageBox("Error.Equation needs to start with equal sign(=)");
				outputLabel.setText(input.getText());
				calculate.setEnabled(false);
				reset.setEnabled(true);
			}

		} else if (button == reset) {
			calculate.setEnabled(true);
			reset.setEnabled(false);
			parser.reset();
			outputLabel.setText("");
			input.setText("=");
			input.requestFocus();
		} else if (button == end) {
			System.exit(0);
		}
	}

}
