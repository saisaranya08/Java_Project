package project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class QuizApplication {
	int totalReward = 0;
	String name, age, state, city;
	boolean usedAudiencePoll = false;
	boolean usedFiftyFifty = false;

	void start() {
		showWelcomeScreen();
	}

	void showWelcomeScreen() {
		JFrame welcomeFrame = new JFrame("Welcome");
		welcomeFrame.setSize(500, 400);
		welcomeFrame.setLayout(new BoxLayout(welcomeFrame.getContentPane(), BoxLayout.Y_AXIS));
		welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel welcomeLabel = new JLabel("Welcome to Java Quiz");
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));
		welcomeLabel.setForeground(Color.BLUE);
		welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel infoLabel = new JLabel("Here's the reward ladder showing the prize for each correct answer");
		infoLabel.setFont(new Font("Arial", Font.BOLD, 14));
		infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel rewardPanel = new JPanel();
		rewardPanel.setLayout(new BoxLayout(rewardPanel, BoxLayout.Y_AXIS));
		String[] rewards = { "Question 1  -  ₹ 1000", "Question 2  -  ₹ 3000", "Question 3  -  ₹ 6000",
				"Question 4  -  ₹ 7000", "Question 5  -  ₹ 9000", "Question 6  - ₹10000", "Question 7  - ₹12000",
				"Question 8  - ₹15000", "Question 9  - ₹20000", "Question 10 - ₹25000" };
		for (String reward : rewards) {
			JLabel label = new JLabel(reward);
			label.setFont(new Font("Arial", Font.BOLD, 14));
			label.setForeground(new Color(0, 139, 139));
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			rewardPanel.add(label);
		}

		JButton proceedButton = new JButton("Press Here To Start Java Quiz");
		proceedButton.setFont(new Font("Arial", Font.BOLD, 16));
		proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		proceedButton.addActionListener(e -> {
			welcomeFrame.dispose();
			showCandidateForm();
		});

		welcomeFrame.add(welcomeLabel);
		welcomeFrame.add(Box.createRigidArea(new Dimension(0, 14)));
		welcomeFrame.add(infoLabel);
		welcomeFrame.add(Box.createRigidArea(new Dimension(0, 10)));
		welcomeFrame.add(rewardPanel);
		welcomeFrame.add(Box.createRigidArea(new Dimension(0, 10)));
		welcomeFrame.add(proceedButton);

		welcomeFrame.setVisible(true);
	}

	void showCandidateForm() {
		JFrame frame = new JFrame("Java Quiz - Candidate Details");
		frame.setSize(400, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		JLabel nameLabel = new JLabel("Full Name:");
		nameLabel.setBounds(30, 30, 100, 25);
		JTextField nameField = new JTextField();
		nameField.setBounds(150, 30, 200, 25);

		JLabel ageLabel = new JLabel("Age:");
		ageLabel.setBounds(30, 70, 100, 25);
		JTextField ageField = new JTextField();
		ageField.setBounds(150, 70, 200, 25);

		JLabel stateLabel = new JLabel("State:");
		stateLabel.setBounds(30, 110, 100, 25);
		JTextField stateField = new JTextField();
		stateField.setBounds(150, 110, 200, 25);

		JLabel cityLabel = new JLabel("City:");
		cityLabel.setBounds(30, 150, 100, 25);
		JTextField cityField = new JTextField();
		cityField.setBounds(150, 150, 200, 25);

		JButton startBtn = new JButton("Start Quiz");
		startBtn.setBounds(130, 200, 120, 30);

		frame.add(nameLabel);
		frame.add(nameField);
		frame.add(ageLabel);
		frame.add(ageField);
		frame.add(stateLabel);
		frame.add(stateField);
		frame.add(cityLabel);
		frame.add(cityField);
		frame.add(startBtn);

		frame.setVisible(true);

		startBtn.addActionListener(e -> {
			name = nameField.getText();
			age = ageField.getText();
			state = stateField.getText();
			city = cityField.getText();

			if (name.trim().isEmpty() || age.trim().isEmpty() || state.trim().isEmpty() || city.trim().isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Please fill all details.", "Input Required",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			frame.dispose();
			showQuestion(1);
		});
	}

	void forCorrectAnswerResponseSwing(int rewardForThisQuestion) {
		totalReward += rewardForThisQuestion;
		JLabel label = new JLabel("Correct! " + name + ", you've won ₹" + rewardForThisQuestion);
		label.setFont(new Font("Arial", Font.BOLD, 16));
		label.setForeground(new Color(0, 128, 0));
		JOptionPane.showMessageDialog(null, label, "Correct Answer", JOptionPane.INFORMATION_MESSAGE);
	}

	void forWrongAnswerResponseSwing() {
		JLabel label = new JLabel("" + name + ", your answer is wrong. Quiz Terminated.");
		label.setFont(new Font("Arial", Font.BOLD, 16));
		label.setForeground(Color.RED);
		JOptionPane.showMessageDialog(null, label, "Wrong Answer", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}

	void showFinalSuccessScreen() {
		JFrame finalFrame = new JFrame("Winner");
		finalFrame.setSize(500, 300);
		finalFrame.setLayout(new BoxLayout(finalFrame.getContentPane(), BoxLayout.Y_AXIS));
		finalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel congratsLabel = new JLabel("🎉 Congratulations, " + name + "! 🎉");
		congratsLabel.setFont(new Font("Arial", Font.BOLD, 20));
		congratsLabel.setForeground(Color.MAGENTA);
		congratsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel rewardLabel = new JLabel("Total Reward Earned: ₹" + totalReward);
		rewardLabel.setFont(new Font("Arial", Font.BOLD, 16));
		rewardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel detailLabel = new JLabel("Candidate Details:");
		detailLabel.setFont(new Font("Arial", Font.BOLD, 16));
		detailLabel.setForeground(new Color(128, 0, 128));
		detailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Create info panel with padding
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40)); // top, left, bottom, right
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

		JLabel infoLabel = new JLabel(
				"<html>Name: " + name + "<br>Age: " + age + "<br>State: " + state + "<br>City: " + city + "</html>");
		infoLabel.setFont(new Font("Arial", Font.BOLD, 14));
		infoLabel.setForeground(new Color(128, 0, 128));
		infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		infoPanel.add(infoLabel);
		finalFrame.add(congratsLabel);
		finalFrame.add(rewardLabel);
		finalFrame.add(Box.createRigidArea(new Dimension(0, 10)));
		finalFrame.add(detailLabel);
		finalFrame.add(infoPanel);

		finalFrame.setVisible(true);
	}

	void lifeLineOptionsSwing(Component parentComponent, String optA, String optB, String optC, String optD,
			String correctAnswer) {
		if (usedAudiencePoll && usedFiftyFifty) {
			JOptionPane.showMessageDialog(parentComponent, "No lifelines available.", "Lifeline Info",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		java.util.List<String> availableList = new java.util.ArrayList<>();
		if (!usedAudiencePoll)
			availableList.add("Audience Poll");
		if (!usedFiftyFifty)
			availableList.add("50-50");

		String[] availableOptions = availableList.toArray(new String[0]);

		int choice = JOptionPane.showOptionDialog(parentComponent, "Choose a lifeline:", "Lifeline Options",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, availableOptions, availableOptions[0]);

		if (choice == -1) {
			return;
		}

		String selectedLifeline = availableOptions[choice];

		if (selectedLifeline.equals("Audience Poll")) {
			usedAudiencePoll = true;
			String pollResult = switch (correctAnswer.toUpperCase()) {
			case "A" -> optA + " - 72%\n" + optB + " - 10%\n" + optC + " - 9%\n" + optD + " - 9%";
			case "B" -> optA + " - 10%\n" + optB + " - 72%\n" + optC + " - 9%\n" + optD + " - 9%";
			case "C" -> optA + " - 9%\n" + optB + " - 10%\n" + optC + " - 72%\n" + optD + " - 9%";
			case "D" -> optA + " - 9%\n" + optB + " - 10%\n" + optC + " - 9%\n" + optD + " - 72%";
			default -> "Invalid";
			};
			JOptionPane.showMessageDialog(parentComponent, "Audience poll result:\n" + pollResult, "Audience Poll",
					JOptionPane.INFORMATION_MESSAGE);

		} else if (selectedLifeline.equals("50-50")) {
			usedFiftyFifty = true;
			String remainingOptions = switch (correctAnswer.toUpperCase()) {
			case "A" -> optA + "\n" + optB;
			case "B" -> optB + "\n" + optC;
			case "C" -> optC + "\n" + optD;
			case "D" -> optA + "\n" + optD;
			default -> "Invalid";
			};
			JOptionPane.showMessageDialog(parentComponent, "Remaining options after 50-50:\n" + remainingOptions,
					"50-50 Lifeline", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	void showQuestion(int qNo) {
		String[] questions = { "What is byte code verifier?", "Which of these is not OOP concept in Java?",
				"Which keyword is used to inherit a class in Java?",
				"Which method is the starting point of a Java program?", "Which of these is not a Java feature?",
				"Which data type is used to create a variable that should store strings?",
				"Which of the following is not a Java loop?", "Which one is a valid declaration of a boolean?",
				"Which of these cannot be used for a variable name in Java?", "Java is a ___ language." };

		String[][] options = {
				{ "A. Region of memory created on RAM", "B. Prevents one JRE accessing another application JRE",
						"C. Both 1 and 2", "D. Converts intermediate to machine code" },
				{ "A. Encapsulation", "B. Inheritance", "C. Compilation", "D. Polymorphism" },
				{ "A. implement", "B. extends", "C. import", "D. package" },
				{ "A. main()", "B. start()", "C. program()", "D. run()" },
				{ "A. Object-oriented", "B. Use of pointers", "C. Portable", "D. Dynamic and Extensible" },
				{ "A. String", "B. Txt", "C. myString", "D. str" }, { "A. for", "B. while", "C. loop", "D. do-while" },
				{ "A. boolean b = 0", "B. boolean b = true", "C. boolean b = 'false'", "D. boolean b = no" },
				{ "A. _var", "B. var1", "C. 1var", "D. my_var" },
				{ "A. low-level", "B. assembly", "C. machine", "D. high-level" } };

		String[] answers = { "B", "C", "B", "A", "B", "A", "C", "B", "C", "D" };
		int[] rewards = { 1000, 3000, 6000, 7000, 9000, 10000, 12000, 15000, 20000, 25000 };

		if (qNo > 10) {
			showFinalSuccessScreen();
			return;
		}

		JFrame frame = new JFrame("Question " + qNo);
		frame.setSize(600, 400);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel questionLabel = new JLabel(qNo + ". " + questions[qNo - 1]);
		questionLabel.setForeground(new Color(0, 0, 128)); // dark blue
		questionLabel.setBounds(30, 30, 500, 25);

		JRadioButton optA = new JRadioButton(options[qNo - 1][0]);
		JRadioButton optB = new JRadioButton(options[qNo - 1][1]);
		JRadioButton optC = new JRadioButton(options[qNo - 1][2]);
		JRadioButton optD = new JRadioButton(options[qNo - 1][3]);

		optA.setForeground(new Color(102, 0, 102)); // purple
		optB.setForeground(new Color(102, 0, 102)); // purple
		optC.setForeground(new Color(102, 0, 102)); // purple
		optD.setForeground(new Color(102, 0, 102)); // purple

		optA.setBounds(30, 70, 500, 25);
		optB.setBounds(30, 100, 500, 25);
		optC.setBounds(30, 130, 500, 25);
		optD.setBounds(30, 160, 500, 25);

		ButtonGroup group = new ButtonGroup();
		group.add(optA);
		group.add(optB);
		group.add(optC);
		group.add(optD);

		JButton lifelineBtn = new JButton("Use Lifeline");
		lifelineBtn.setBounds(150, 200, 120, 30);
		lifelineBtn.addActionListener(e -> lifeLineOptionsSwing(frame, optA.getText(), optB.getText(), optC.getText(),
				optD.getText(), answers[qNo - 1]));

		JButton submitBtn = new JButton("Submit");
		submitBtn.setBounds(300, 200, 100, 30);
		submitBtn.addActionListener(e -> {
			String selected = "";
			if (optA.isSelected())
				selected = "A";
			else if (optB.isSelected())
				selected = "B";
			else if (optC.isSelected())
				selected = "C";
			else if (optD.isSelected())
				selected = "D";

			if (selected.equals(answers[qNo - 1])) {
				forCorrectAnswerResponseSwing(rewards[qNo - 1]);
				frame.dispose();
				showQuestion(qNo + 1);
			} else if (selected.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Please select an option before submitting.", "Input Required",
						JOptionPane.WARNING_MESSAGE);
				return;
			} else {
				frame.dispose();
				forWrongAnswerResponseSwing();
			}
		});

		frame.add(questionLabel);
		frame.add(optA);
		frame.add(optB);
		frame.add(optC);
		frame.add(optD);
		frame.add(lifelineBtn);
		frame.add(submitBtn);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new QuizApplication().start();
	}
}
