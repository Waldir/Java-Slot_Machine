/*
	Programmed by: Waldir Bolanos
	Date: 07/29/14
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class SlotMachine extends JFrame
{
	private JLabel ImageLabelOne; 	// Holds an image
	private JLabel ImageLabelTwo;	// Holds an image
	private JLabel ImageLabelTres; 	// Holds an image
	private JTextField fieldOne;
	private JTextField fieldTwo;
	private JTextField fieldTres;

	/**
	 * Constructor
	 */
	public SlotMachineTest( )
	{
		super( "Slot Machine" ); 							// Set the title bar text.
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 	// Specify what happens when the close button is clicked.

		// Create panels
		JPanel imagesPanel = new JPanel( new GridLayout( 1, 3 ) );
		JPanel numberPanel = new JPanel( new GridLayout( 2, 3 ) );
		JPanel buttonPanel = new JPanel( );

		// Create Button
		JButton Spin   = new JButton( "Spin" );
		JButton upBet  = new JButton( "Rise Bet" );
		JButton lowBet = new JButton( "Lower Bet" );
		JButton maxBet = new JButton( "Max Bet" );
		JButton reset  = new JButton( "Reset" );

         // Read the image file into an ImageIcon object.
		ImageIcon ImageOne  = new ImageIcon( "blank.png" );
		ImageIcon ImageTwo  = new ImageIcon( "blank.png" );
		ImageIcon ImageTres = new ImageIcon( "blank.png" );

		ImageLabelOne  = new JLabel("", ImageOne,  JLabel.LEFT );
		ImageLabelTwo  = new JLabel("", ImageTwo,  JLabel.CENTER );
		ImageLabelTres = new JLabel("", ImageTres, JLabel.RIGHT );

		fieldOne  = new JTextField( "3" );
		fieldTwo  = new JTextField( "0" );
		fieldTres = new JTextField( "100" );

		fieldOne.setEditable ( false );
		fieldTwo.setEditable ( false );
		fieldTres.setEditable( false );

		// Register an action listener with the buttons.
		Spin.addActionListener  ( new SpinButtonListener( ) );
		upBet.addActionListener ( new UpBetButtonListener( ) );
		lowBet.addActionListener( new LowBetButtonListener( ) );
		maxBet.addActionListener( new MaxBetButtonListener( ) );
		reset.addActionListener ( new ResetBetButtonListener( ) );

		buttonPanel.add( Spin );
		buttonPanel.add( upBet );
		buttonPanel.add( lowBet );
		buttonPanel.add( maxBet );
		buttonPanel.add( reset );

		numberPanel.add( new JLabel( "Bet: " ) );
		numberPanel.add( new JLabel( "Winnigs: " ) );
		numberPanel.add( new JLabel( "Balance: " ) );
		numberPanel.add( fieldOne );
		numberPanel.add( fieldTwo );
		numberPanel.add( fieldTres );

		imagesPanel.add( ImageLabelOne );
		imagesPanel.add( ImageLabelTwo );
		imagesPanel.add( ImageLabelTres );

		// Set Borders
		imagesPanel.setBorder ( BorderFactory.createTitledBorder( "Wheels" ) );
		numberPanel.setBorder ( BorderFactory.createTitledBorder( "Numbers" ) );

		// Add the panels to the content pane.
		add( imagesPanel, BorderLayout.NORTH );
		add( numberPanel, BorderLayout.CENTER );
		add( buttonPanel, BorderLayout.SOUTH );

		// Pack and display the window.
		pack( );
		setVisible( true );
	}

   /**
    * String to int
    */
	private int stringToInt ( String toConvert )
	{
		return Integer.parseInt( toConvert );
	}

   /**
    * Int to String
    */
	private String intToString ( int toConvert )
	{
		return Integer.toString( toConvert );
	}

   /**
    * updateTextField
    */
	private void updateTextField( int textField, int ammount )
	{
		int cuttentNumber;

		if( textField == 1 )
		{
			cuttentNumber = stringToInt( fieldOne.getText( ) );
			fieldOne.setText( intToString ( ammount ) );
		}
		else if ( textField == 2 )
		{
			cuttentNumber = stringToInt( fieldTwo.getText( ) );
			fieldTwo.setText( intToString ( ammount ) );
		}
		else if ( textField == 3 )
		{
			cuttentNumber = stringToInt( fieldTres.getText( ) );
			fieldTres.setText( intToString ( ammount ) );
		}
	}

   /**
    *  getCurrentBet
    *  @return The value of the current bet.
    */
	private int getCurrentBet ( )
	{
		return stringToInt( fieldOne.getText( ) );
	}

   /**
    *  getCurrentWinning
    *  @return The value of the current Winning.
    */
	private int getCurrentWinning ( )
	{
		return stringToInt( fieldTwo.getText( ) );
	}

   /**
    *  getCurrentBalance
    *  @return The value of the current balance.
    */
	private int getCurrentBalance ( )
	{
		return stringToInt( fieldTres.getText( ) );
	}

   /**
    * randomSlot generates a random slot
    * @return The value of a random image
    */
	public String randomSlot( )
	{
		String[] slots = { "Twilight.png", "Rainbow.png", "Apple.png", "Pinky.png", "Rarity.png", "Fluttershy.png", "blank.png" };
		int idx = new Random( ).nextInt( slots.length );
		String random = ( slots[idx] );
		return random;
	}

   /**
    * Private ResetBetButtonListener class that resets defaults
    */
	private class ResetBetButtonListener implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			updateTextField( 1, 3 );
			updateTextField( 2, 0 );
			updateTextField( 3, 100 );
		}
	}

   /**
    * Private UpBetButtonListener class that handles upBet
    */
	private class UpBetButtonListener implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			if( getCurrentBet( ) < getCurrentBalance( ) )
				updateTextField( 1, getCurrentBet( ) + 1 );  // Up current Bet
			else
				updateTextField( 1, getCurrentBalance( ) );  // Set current Bet to current balance
		}
	}

   /**
    * Private MaxBetButtonListener class that sets bet to current balance
    */
	private class MaxBetButtonListener implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
				updateTextField( 1, getCurrentBalance( ) );  // Set bet to current balance
		}
	}

   /**
    * Private LowBetButtonListener class that handles LowBet
    */
	private class LowBetButtonListener implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			if( getCurrentBet( ) > 1 )
				updateTextField( 1, getCurrentBet( ) - 1 );  // Low current Bet
			if( getCurrentBet( ) > getCurrentBalance( ) )
				updateTextField( 1, getCurrentBalance( ) );  // Set current Bet to current balance
		}
	}
   /**
    * Private SpinButtonListener class that handles spin
    */
	private class SpinButtonListener implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			if( getCurrentBalance( ) > 0 && getCurrentBet( ) <= getCurrentBalance( ) )
			{
				String randomOne  = randomSlot( );
				String randomTwo  = randomSlot( );
				String randomTres = randomSlot( );

				// Read the image file into an ImageIcon object.
				ImageIcon ImageOne  = new ImageIcon( randomOne );
				ImageIcon ImageTwo  = new ImageIcon( randomTwo );
				ImageIcon ImageTres = new ImageIcon( randomTres );

				// Display the image in the label.
				ImageLabelOne.setIcon ( ImageOne );
				ImageLabelTwo.setIcon ( ImageTwo );
				ImageLabelTres.setIcon( ImageTres );

				if ( randomOne.equals ( randomTwo ) && randomTres.equals( randomOne ) && !randomOne.equals ( "blank.png" ) )
				{
					// update to user wins 3 x bet
					updateTextField( 2, ( getCurrentBet( ) * 3 ) );  						// Update Current Winnings
					updateTextField( 3, ( getCurrentBet( ) * 3 ) + getCurrentBalance( ) ); 	// Update Current Balance (Win)
				}
				else if( ( randomOne.equals( randomTwo  ) && !randomOne.equals( "blank.png" ) )  ||
						 ( randomOne.equals( randomTres ) && !randomOne.equals( "blank.png" ) )  ||
						 ( randomTwo.equals( randomTres ) && !randomTwo.equals( "blank.png" ) ) )
				{
					// update to user wins 2 x bet
					updateTextField( 2, ( getCurrentBet( ) * 2 ) ); 						// Update Current Winnings
					updateTextField( 3, ( getCurrentBet( ) * 2 ) + getCurrentBalance( ) ); 	// Update Current Balance (Win)
				} else
				{
					updateTextField( 2, -getCurrentBet( ) ); 						// Update Current Winnings
					updateTextField( 3, getCurrentBalance( ) - getCurrentBet( ) ); 	// Update Balance after bet

					if( getCurrentBet( ) > getCurrentBalance( ) )
						updateTextField( 1, getCurrentBalance( ) ); 				// Update Current bet
				}
			} else
				JOptionPane.showMessageDialog( null, "Out of Mula!" );
		}
   }

   /**
    * Run the SlotMachineTest
    */
   public static void main( String[] args )
   {
	    new SlotMachine( );
   }
}
