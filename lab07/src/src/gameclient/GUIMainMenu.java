package gameclient;

import gameserver.Options;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUIMainMenu extends JPanel implements IOptionsProducer {
    IOptionsReceiver optionsReceiver;
    Options options;
    JButton start;

    public GUIMainMenu() {
        super();
        this.options = new Options();
        this.setLayout(new GridLayout(5, 1, 0, 50));
        JLabel titleScreen = new JLabel(new ImageIcon("assets/titlescreen200x60.png"));
        this.add(titleScreen);
        this.add(new InputPlayer());
        this.add(new InputMNK());
        this.add(new InputGravity());
        this.add(new StartButton());
    }

    @Override
    public void addOptionsListener(IOptionsReceiver listener) {
        this.optionsReceiver = listener;
    }

    @Override
    public void fireOptions(Options options) {
        this.optionsReceiver.listenOptions(options);
    }


    class InputPlayer extends JPanel implements ActionListener {
        InputPlayer() {
            super();
            this.setLayout(new GridLayout(1, 2));
            JLabel label= new JLabel("Choose the amount of players: ");
            JComboBox<String> box = new JComboBox<>(new String[]{"2", "3", "4"});
            box.addActionListener(this);
            this.add(label);
            this.add(box);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            @SuppressWarnings("unchecked")
            JComboBox<String> box = (JComboBox<String>) e.getSource();
            String selectedItem = (String) box.getSelectedItem();
            assert selectedItem != null;
            options.setPlayers(Integer.parseInt(selectedItem));
        }
    }


    class InputMNK extends JPanel implements ChangeListener {
        JLabel label;
        JSlider mSlider;
        JSlider nSlider;
        JSlider kSlider;

        InputMNK() {
            super();
            this.setLayout(new GridLayout(4, 1));

            ArrayList<JSlider> sliderList = new ArrayList<JSlider>();
            mSlider = new JSlider(3, 10, 3);
            sliderList.add(mSlider);
            nSlider = new JSlider(3, 10, 3);
            sliderList.add(nSlider);
            kSlider = new JSlider(3, 10, 3);
            sliderList.add(kSlider);

            label= new JLabel("Values for m, n, k: " + mSlider.getValue() + ", " + nSlider.getValue() + ", " + kSlider.getValue());
            this.add(label);

            for (JSlider slider: sliderList) {
                slider.setPaintTicks(true);
                slider.setMinorTickSpacing(10);
                slider.setPaintTrack(true);
                slider.setPaintLabels(true);
                slider.addChangeListener(this);
                this.add(slider);
            }
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            this.label.setText("Values for m, n, k: " + mSlider.getValue() + ", " + nSlider.getValue() + ", " + kSlider.getValue());
            if (e.getSource() == mSlider) {
                options.setM(mSlider.getValue());
            } else if (e.getSource() == nSlider) {
                options.setN(nSlider.getValue());
            } else {
                options.setK(kSlider.getValue());
            }
        }
    }


    class InputGravity extends JPanel implements ItemListener {
        InputGravity() {
            super();
            this.setLayout(new GridLayout(1, 2));
            JLabel label = new JLabel("Gravity mode: ");
            JToggleButton toggle = new JToggleButton();
            toggle.addItemListener(this);
            this.add(label);
            this.add(toggle);
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            boolean gravityMode = (e.getStateChange() == ItemEvent.SELECTED);
            options.setGravity(gravityMode);
        }
    }


    class StartButton extends JPanel implements ActionListener {
        JButton button;

        StartButton() {
            super();
            this.button = new JButton("Start");
            button.addActionListener(this);
            this.add(button);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            fireOptions(options);
        }
    }

}
