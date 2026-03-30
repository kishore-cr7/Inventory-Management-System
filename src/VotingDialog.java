import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VotingDialog extends JFrame implements ActionListener {

    private JRadioButton candidate1, candidate2, candidate3, candidate4;
    private ButtonGroup candidateGroup;

    public VotingDialog() {
        setTitle("Vote Dialog");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
  JLabel heading = new JLabel("Click the 'Vote' button once you have selected a candidate.");

        candidate1 = new JRadioButton("Candidate 1: ");
        JLabel candidate1Name = new JLabel("Sparky the Dog");
        candidate1Name.setForeground(Color.BLUE);
        candidate2 = new JRadioButton("Candidate 2: ");
        JLabel candidate2Name = new JLabel("Shady Sadie");
        candidate2Name.setForeground(Color.GREEN);
        candidate3 = new JRadioButton("Candidate 3: ");
        JLabel candidate3Name = new JLabel("R.I.P. McDaniels");
        candidate3Name.setForeground(Color.RED);
        candidate4 = new JRadioButton("Candidate 4: ");
        JLabel candidate4Name = new JLabel("Duke the Java™ Platform Mascot");
        candidate4Name.setForeground(Color.MAGENTA);

        candidateGroup = new ButtonGroup();
        candidateGroup.add(candidate1);
        candidateGroup.add(candidate2);
        candidateGroup.add(candidate3);
        candidateGroup.add(candidate4);

        JButton voteButton = new JButton("Vote");
        voteButton.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(6, 1));
        panel.add(heading);
        JPanel candidate1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        candidate1Panel.add(candidate1);
        candidate1Panel.add(candidate1Name);
        panel.add(candidate1Panel);
        JPanel candidate2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        candidate2Panel.add(candidate2);
        candidate2Panel.add(candidate2Name);
        panel.add(candidate2Panel);
        JPanel candidate3Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        candidate3Panel.add(candidate3);
        candidate3Panel.add(candidate3Name);
        panel.add(candidate3Panel);
        JPanel candidate4Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        candidate4Panel.add(candidate4);
        candidate4Panel.add(candidate4Name);
        panel.add(candidate4Panel);
        panel.add(voteButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JRadioButton selectedCandidate = null;
            if (candidate1.isSelected()) {
                selectedCandidate = candidate1;
            } else if (candidate2.isSelected()) {
                selectedCandidate = candidate2;
            } else if (candidate3.isSelected()) {
                selectedCandidate = candidate3;
            } else if (candidate4.isSelected()) {
                selectedCandidate = candidate4;
            }

            if (selectedCandidate != null) {
                JOptionPane.showMessageDialog(this, "You voted for: " + selectedCandidate.getText()
                + " " + ((JLabel) ((JPanel) selectedCandidate.getParent()).getComponent(1)).getText());
            } else {
                JOptionPane.showMessageDialog(this, "Please select a candidate.");
            }
        }
    }

    public static void main(String[] args) {
        new VotingDialog();
    }
}