/*********************************************************************************************************************************************************************
 Name:
 ICS3U_Blackjack_David_Tan

 Description:
 Creating a playable blackjack game where the player is able to bet in a point system against the dealer with set rules.

 History:
 Nov.11.2016 / D. Tan / Created Blackjack
 Nov.14.2016 / D. Tan / Created Header
 Nov.16.2016 / D. Tan / Created paint
			Created mouseUp
			Modified Blackjack
 Nov.17.2016 / D. Tan / Modified paint
			Modified mouseUp
			Note - Worked on the title screen buttons and graphics
 Nov.21.2016 / D. Tan / Created deal
			Modified paint
			Note - Started working on dealing the card and drawing them
 Nov.22.2016 / D. Tan / Created bet
			Modified paint
			Modified mouseUp
			Note - Started creating the betting sytem/ visuals
 Nov.23.2016 / D. Tan / Modified paint
			Modified mouseUp
			Note - Still working on betting system
 Nov.24.2016 / D. Tan / Modified paint
			Modified mouseUp
			Note - Finished working on betting system
 Nov.28.2016 / D. Tan / Modified paint
			Modified mouseUp
			Note - Finished betting visuals and dealing/reseting buttons
 Nov.29.2016 / D. Tan / Created update
			Modified paint
			Note - Put everything in paint to update for better buffering of images and coded gameplay (actual blackjack game)
 Nov.30.2016 / D. Tan / Modified paint
			Modified mouseUp
			Note - Allowed for working hit button that created new cards and displayed to screen
 Nov.31.2016 / D. Tan / Created init
			Modified paint
			Note - Added images and allowed user to bust after 21 and also stop hitting
 Dec.01.2016 / D. Tan / Modified paint
			Note - Worked on dealer code. Seems hard to do, must work on this more and focus to fix it
 Dec.03.2016 / D. Tan / Modified paint
			Modified mouseUp
			Note - added better looking GUI for betting system to make it user friendly
 Dec.05.2016 / D. Tan / Created reset
			Modified paint
			Modified mouseUp
			Note - Coded huge portion of blackjack, improved GUI, finished dealer and added a reset button
 Dec.06.2016 / D. Tan / Created setBack
			Note - Did some bug fixing and finished commenting comments
			 
 ********************************************************************************************************************************************************************/
import java.awt.*;              // Used in graphics
import java.awt.event.*;        // For mouse stuff
import java.applet.*;           // For mouse/music stuff
import java.io.*;               // For input and output
import java.util.*;             // For date and time stuff
import java.lang.*;             // For point stuff

class BlackjackFinished extends Applet // The class of my whole game which extends of using applet
{

    boolean gTitleClicked = false;                           // A variable that checks if the the title buttons have been clicked clicked or not
    boolean gIsTitle = true;                                 // Boolean to check if the game is on the title screen
    boolean gBetScreenClickable = false;                     // A variable that checks if the bet screen is clickable or not
    boolean gGameScreenClickable = false;                    // A variable that checks if the game (actual blackjack) screen is clickable or not
    double gUserBalance = 75;                                // It is the int variable that holds the user's balance (starts at 75)
    double gBetBalance = 0;                                  // It is the int varaiable that holds the amount of money being bet
    Image gHeart;                                            // Creates an image varariable called gHeart
    Image gDiamond;                                          // Creates an image varariable called gDiamond
    Image gSpade;                                            // Creates an image varariable called gSpade
    Image gClub;                                             // Creates an image varariable called gClub
    Image gBlackCoin;                                        // Creates an image varariable called gBlackCoin
    Image gBackCard;                                         // Creates an image varariable called gBackCard
    Image gDealerChange;                                     // Creates an image varariable called gDealerChange
    boolean gUserStatusDeck[] = new boolean [53];            // Creates an array that is a boolean type which contains spots 0-53 spots
    boolean gDealerStatusDeck[] = new boolean [53];          // Creates an array that is a boolean type which contains spots 0-53 spots
    Font gCardNumberFont = new Font ("Courier New", 1, 25);  // Creates a font called gCardNumberFont which has the parameters (Font, type, size)
    String gSuitType = "none";                               // Creates a string variable alled gSuitType
    double gValuePicked;                                     // A real number that will hold the value of the card
    double gSuitPicked;                                      // A real number that will hold the suit of the card
    double gRandomNumber;                                    // A real number that will hold the random number generated for a card
    int gRandomInt;                                          // An int number that will hold the random number in int form
    int gValuePickedInt;                                     // Holds the gValuePicked in integer form
    String gValuePickedString;                               // Holds the gValuePicked in string from
    int gCardPosition = 0;                                   // Will hold the x position of a card
    int gUserCardHolder[] = new int [11];                    // Creates an array that holds 11 card spots (user)
    int gUserCardSlot = 0;                                   // Will hold the user's cardSlot number
    int gUserHandValue = 0;                                  // Holds the user's hand value
    int gDealerCardHolder[] = new int [11];                  // Creates an array that holds 11 card spots (dealer)
    int gDealerCardSlot = 0;                                 // Will hold the dealer's cardSlot number
    int gDealerHandValue = 0;                                // Holds the dealer's hand value
    boolean gStopDeal = false;                               // Used to check if you are going to stop dealing
    boolean gAce = false;                                    // Used to check if an gAce is in play
    boolean gAceDealer = false;                              // Used to check if dealer's gAce is in play
    boolean gDealerHand = false;                             // Used to check if it is the dealer's turn to draw
    boolean gDealingToDealer = false;                        // Used to check if you are dealing to the dealer
    boolean gCardOne = false;                                // Used to check if you are using the cardback
    boolean gReveal = false;                                 // Used to check if you are gRevealing dealer's card
    boolean gWinBet = false;                                 // Used to check if you won
    boolean gLoseBet = false;                                // Used to check if you lost
    boolean gTieBet = false;                                 // Used to check if you tied
    boolean gCanReset = false;                               // Used to check if you can reset the game
    int gCardYPostionChange = 0;                             // Used to change the position of the cards when the dealers card a pulled
    Color gLightGreenBackground = new Color (39, 169, 104);  // Creates a new color off the rgb parameter (red, green, blue) which is light green
    Font gGameFont = new Font ("Comic Sans MS", 1, 30);      // Used to create the font for the game using parameters (font, type, size)


    public void bet (int amount)  // Created a function that takes in an amount
    {
	gBetBalance = gBetBalance + amount; // Betbalance plus the amount bet
    } // End of Bet


    public void init ()  // A method that allows for the obtaining of pictures from file names
    {
	// Makes the variable name into the equivelant picture (like gHeart to gHeart photo)
	gHeart = getImage (getCodeBase (), "heartCard.jpg");
	gDiamond = getImage (getCodeBase (), "diamondCard.jpg");
	gSpade = getImage (getCodeBase (), "spadeCard.jpg");
	gClub = getImage (getCodeBase (), "clubCard.jpg");
	gBlackCoin = getImage (getCodeBase (), "BlackCoin.jpg");
	gBackCard = getImage (getCodeBase (), "BackCard.jpg");
	gDealerChange = getImage (getCodeBase (), "DealerChange.jpg");  // Makes gDealerChange into an image that displaces the rate of gain (3 to 2)
    } // End of init ()


    public void deal ()  // A method that deals a card
    {
	Graphics g = getGraphics ();                      // Gets graphics and allows for the use of commands (g.)
	double trueValue;                                 // A double that gets the card's number / 4 (This gets the cards placement in a deck numbered 1 = gAce, 2 = two, ... , 11 - Jack, 12 = Queen, 13 = queen
	int trueValueInt;                                 // Is the interger equivelant of trueValue
	gRandomNumber = (Math.random () * (52 - 1)) + 1;  // Creates a random number from 1 to 52
	gRandomInt = (int) Math.ceil (gRandomNumber);     // Makes randomNumberInt into the rounded up of number of gRandomNumber
	if (gUserStatusDeck [gRandomInt] == true && gDealingToDealer == false)  // If the card in the deck is already pulled and it is dealing to the user
	{
	    deal ();  // Rerun the deal method
	} // End if statement
	if (gUserStatusDeck [gRandomInt] == false && gDealingToDealer == false)  // If the card in the deck isn't pulled and it is dealing to the user
	{
	    gUserStatusDeck [gRandomInt] = true;                                 // Sets the card to be pulled
	    gUserCardHolder [gUserCardSlot] = gRandomInt;                        // User card slot one is now the random int
	    trueValue = gRandomInt;                                              // TrueValue is now the random integer number
	    trueValue = trueValue / 4;                                           // Divides trueValue by 4 (Used to find value of card)
	    trueValueInt = (int) Math.ceil (trueValue);                          // Rounds to an int

	    if (trueValueInt == 1)  // If the card placement in a deck is 1
	    {
		gAce = true;                                                     // You pull an gAce
		gUserHandValue = gUserHandValue + 11;                            // Add 11 to the user's hand
	    } // End if statement


	    if (trueValueInt > 1 && trueValueInt < 11)  // If the card placement in a deck is  2 to 10
	    {
		gUserHandValue = gUserHandValue + trueValueInt;  // Add the card placement number pulled to the user's hand value
	    } // End if statement

	    if (trueValueInt == 11)  // If the card placement in a deck is  11
	    {
		gUserHandValue = gUserHandValue + 10;  // Add 10 to the user's hand
	    } // End if statement

	    if (trueValueInt == 12)  // If the card placement in a deck is  12
	    {
		gUserHandValue = gUserHandValue + 10;  // Add 10 to the user's hand
	    } // End if statement
	    if (trueValueInt == 13)  // If the card placement in a deck is  12
	    {
		gUserHandValue = gUserHandValue + 10; // Add 10 to the user's hand
	    } // End if statement
	} // End if statement


	if (gDealingToDealer == true)  // If you are dealing to the dealer
	{
	    if (gDealerStatusDeck [gRandomInt] == true)  // If the dealer card is already pulled
	    {
		deal ();  // Re runs deal code
	    } // End if statement

	    else  // Anything else
	    {
		gDealerStatusDeck [gRandomInt] = true;             // Sets the card to be pulled
		gDealerCardHolder [gDealerCardSlot] = gRandomInt;  // Dealer card slot one is now the random int
		trueValue = gRandomInt;                            // TrueValue is now the random integer number
		trueValue = trueValue / 4;                         // Divides trueValue by 4 (Used to find value of card)
		trueValueInt = (int) Math.ceil (trueValue);        // Rounds to an int

		if (trueValueInt == 1)  // If the card placement in a deck is 1
		{
		    gAceDealer = true;                         // You pull an gAce
		    gDealerHandValue = gDealerHandValue + 11;  // Add 11 to the dealer's hand
		} // End if statement

		else if (trueValueInt > 1 && trueValueInt < 11)  // If the card placement in a deck is  2 to 10
		{
		    gDealerHandValue = gDealerHandValue + trueValueInt;  // Add the card placement number pulled to the dealer's hand value
		} // End if statement

		else if (trueValueInt == 11)  // If the card placement in a deck is 11
		{
		    gDealerHandValue = gDealerHandValue + 10; // Add 10 to the dealer's hand
		} // End if statement

		else if (trueValueInt == 12)  // If the card placement in a deck is 12
		{
		    gDealerHandValue = gDealerHandValue + 10;  // Add 10 to the dealer's hand
		}
		// End if statement
		else if (trueValueInt == 13)  // If the card placement in a deck is 13
		{
		    gDealerHandValue = gDealerHandValue + 10;  // Add 10 to the dealer's hand
		} // End if statement
	    } // End if statement
	} // End else statement
    } // Ends deal()


    public void update (Graphics g)  // Method that outputs the graphics
    {
	Color titleBackgroundColor = new Color (224, 224, 224);  // Creates a variable that holds a new color (gray)
	Color startButtonColor = new Color (20, 203, 51);        // Creates a variable that holds a new color (green)
	Color quitButtonColor = new Color (204, 0, 0);           // Creates a variable that holds a new color (red)
	Font titleButtonFont = new Font ("Courier New", 1, 60);  // Creates a new font in Courier New
	Font titleFont = new Font ("Comic Sans MS", 1, 90);      // Creates a new font in Comic Sans MS

	if (gIsTitle == true)  // If you are on the titleScreen
	{
	    this.setSize (1000, 600);                           // Set applet size
	    g.setColor (titleBackgroundColor);                  // Set color to gray
	    g.fillRect (0, 0, 1000, 600);                       // Draws a rectangle that covers the screen with the parameters (x, y, width, height)
	    g.setColor (startButtonColor);                      // Set color to future objects green
	    g.fillRect (400, 200, 200, 100);                    // Creates a filled rectangle with the parameters (x,y,width,height);
	    g.setColor (quitButtonColor);                       // Set color to future objects red
	    g.fillRect (400, 325, 200, 100);                    // Creates a filled rectangle with the parameters (x,y,width,height);
	    g.setColor (Color.black);                           // Set color to black
	    g.setFont (titleButtonFont);                        // Set font to titleButtonFont
	    g.drawString ("START", 410, 265);                   // Draw string that uses paremeters (containted text, x, y position)
	    g.drawString ("QUIT", 420, 390);                    // Draw string that uses paremeters (containted text, x, y position)
	    g.setFont (titleFont);                              // Set font to titleFont
	    g.drawString ("Blackjack", 300, 120);               // Draw string that uses paremeters (containted text, x, y position)
	    g.drawImage (gBlackCoin, 125, 30, 125, 125, this);  // Draw an image with the parameters (image variable, x , y, width, height, this)
	    g.drawImage (gBlackCoin, 750, 30, 125, 125, this);  // Draw an image with the parameters (image variable, x , y, width, height, this)
	} // End if statement
	if (gBetScreenClickable == true)  // If the betScreen is clickable then
	{
	    Color betOneColor = new Color (0, 102, 51);             // Creates a variable that holds a new color using the RGB
	    Color betFiveColor = new Color (255, 128, 0);           // Creates a variable that holds a new color using the RGB
	    Color betTwentyFiveColor = new Color (203, 0, 0);       // Creates a variable that holds a new color using the RGB
	    Color betFiftyColor = new Color (204, 204, 0);          // Creates a variable that holds a new color using the RGB
	    Color darkGray = new Color (96, 96, 96);                // Creates a variable that holds a new color using the RGB
	    Font betFont = new Font ("Arial", 1, 80);               // Creates a new font in Ariel
	    Font DealReset = new Font ("Arial", 1, 60);             // Creates a new font in Ariel
	    g.setColor (gLightGreenBackground);                     // Sets color choice to light green
	    g.fillRect (0, 0, 1000, 600);                           // Draws a rectangle that covers the screen with the parameters (x, y, width, height)
	    g.setColor (Color.black);                               // Set to chosen color
	    g.drawImage (gDealerChange, 150, 215, 700, 150, this);  // Draw an image with the parameters (image variable, x , y, width, height, this)
	    g.setFont (gGameFont);                                  // Sent to chosen font
	    g.drawString ("User Funds: " + gUserBalance, 20, 570);  // Draw string that uses paremeters (containted text plus the user's balance, x, y position)
	    g.drawString ("Bet Balance: " + gBetBalance, 20, 520);  // Draw string that uses paremeters (containted text plus the bet's blance, x, y position)
	    g.setColor (betOneColor);                               // Set to chosen custom color
	    g.fillRect (10, 100, 180, 100);                         // Draws a filled Rectangle with the parameters (x,y,width,height)
	    g.setColor (betFiveColor);                              // Set to chosen custom color
	    g.fillRect (210, 100, 180, 100);                        // Draws a filled Rectangle with the parameters (x,y,width,height)
	    g.setColor (betTwentyFiveColor);                        // Set to chosen custom color
	    g.fillRect (410, 100, 180, 100);                        // Draws a filled Rectangle with the parameters (x,y,width,height).
	    g.setColor (betFiftyColor);                             // Set to chosen custom color
	    g.fillRect (610, 100, 180, 100);                        // Draws a filled Rectangle with the parameters (x,y,width,height).
	    g.setColor (Color.black);                               // Set to chosen color
	    g.fillRect (810, 100, 180, 100);                        // Draws a filled Rectangle with the parameters (x,y,width,height).
	    g.setColor (Color.white);                               // Set to chosen color
	    g.fillRect (510, 360, 180, 100);                        // Reset Box graphic
	    g.fillRect (310, 360, 180, 100);                        // Deal Box graphic
	    g.setFont (betFont);                                    // Set to custom font

	    // Draw string that uses paremeters (containted text, x, y position)
	    g.drawString ("1", 80, 180);
	    g.drawString ("5", 280, 180);
	    g.drawString ("25", 455, 180);
	    g.drawString ("50", 655, 180);
	    g.drawString ("100", 835, 180);

	    g.setFont (DealReset);                      // Set to custom font
	    g.setColor (darkGray);                      // Set to custom font
	    g.drawString ("Deal", 330, 430);            // Draw string that uses paremeters (containted text plus the bet's blance, x, y position)
	    g.drawString ("Reset", 520, 430);           // Draw string that uses paremeters (containted text plus the bet's blance, x, y position)
	    g.setColor (Color.black);                   // Set to chosen color
	    g.drawString ("PLACE YOUR BETS", 200, 65);  // Draw string that uses paremeters (containted text plus the bet's blance, x, y position)
	} // End if statement

	if (gGameScreenClickable == true) // If the game screen is clickable
	{
	    g.setColor (gLightGreenBackground);                     // Set to light green color
	    g.fillRect (0, 0, 1000, 600);                           // Draws a rectangle that covers the screen with the parameters (x, y, width, height)
	    g.drawImage (gDealerChange, 150, 215, 700, 150, this);  // Draw an image with the parameters (image variable, x , y, width, height, this)
	    g.setColor (Color.green);                               // Set to chosen color green
	    g.fillRect (325, 270, 150, 75);                         // Draws the graphic for the hit box
	    g.setColor (Color.red);                                 // Set to chosen color red
	    g.fillRect (500, 270, 150, 75);                         // Draws the graphic for the stand box
	    g.setFont (gGameFont);                                  // Set to custom font
	    g.setColor (Color.black);                               // Set to chosen color

	    // Draw string that uses paremeters (containted text, x, y position)
	    g.drawString ("Hit", 375, 320);
	    g.drawString ("Stand", 520, 320);
	    g.drawString ("User Hand: " + gUserHandValue, 20, 380);

	    gCardPosition = 0;          // Sets card position to 0

	    if (gDealerHandValue > 21)  // Checks to see if the dealer's hand is greater than 21
	    {
		if (gAceDealer == true)  // If there is an ace that is still true
		{
		    g.setColor (gLightGreenBackground);                         // Set to chosen color
		    g.drawString ("Dealer Hand: " + gDealerHandValue, 20, 30);  // Covers old text
		    gDealerHandValue = gDealerHandValue - 10;                   // Minus the dealer hand value
		    g.setColor (Color.black);                                   // Set to chosen color
		    g.drawString ("Dealer Hand: " + gDealerHandValue, 20, 30);  // Draws new dealer hand value with outputed text
		    gAceDealer = false;                                         // Ace is now false
		    for (int i = 0 ; i < 11 ; i++)                              // For loop from 0 - 11
		    {
			if (gDealerHandValue < 17)  // If the dealer's hand value is less than 17
			{
			    gDealingToDealer = true;                // Dealing to dealer is true
			    repaint ();                             // Repaints the screen
			    gDealerCardSlot = gDealerCardSlot + 1;  // Card slot increases
			    deal ();                                // Deal again
			} // End if statement
		    } // End for loop
		} // End if statement
		else  // If else
		{
		    g.setFont (gCardNumberFont);  // Set to custom font

		    // Draws text telling the dealer has busted and that the user won
		    g.drawString ("DEALER BUST", 400, 50);
		    g.drawString ("YOU WIN", 750, 50);

		    gStopDeal = true;  // Stop dealing is true
		    gWinBet = true;    // gWinBet is true
		} // End else
	    } // End if statement


	    g.setFont (gCardNumberFont);  // Set to custom font
	    if (gCardOne == true)         // If it is the gCardOne for the dealer
	    {
		g.drawImage (gBackCard, 10, 50, 155, 200, this);  // Draw the back of the card
		gCardPosition = gCardPosition + 70;               // Add plus 70 to x co-ordinate
	    } // End if statement

	    if (gReveal == false)  // If dealer isn't gRevealing
	    {
		for (int i = 1 ; i < 11 ; i++)  // Loops from 1-11 that deals all cards but the first
		{
		    if (gDealerHand == true)  // If it is dealing to dealer's hand
		    {
			gSuitPicked = gDealerCardHolder [i];               // Suitpicked becomes the dealer's number in card spot i
			gValuePicked = gDealerCardHolder [i];              // Valuepicked becomes the dealer's number in card spot i
			gCardYPostionChange = -350;                        // CardYPositionChange is equal to minus - 350
			gSuitPicked = gSuitPicked % 4;                     // Suit picked value divided by 4 to get the module which will find the suit
			gValuePicked = gValuePicked / 4;                   // Divides gValuePicked by 4 (Used to find value of card)
			gValuePickedInt = (int) Math.ceil (gValuePicked);  // Rounds the value picked to an int

			if (gValuePicked > 0)  // If valuePick is greater than 0 then
			{
			    g.setColor (Color.white);                                              // Set color to white
			    g.fillRect (10 + gCardPosition, 400 + gCardYPostionChange, 150, 199);  // Draw a white rectangle for the card's background

			    if (gSuitPicked == 0)  // If there is a remainder of 0
			    {
				gSuitType = "Heart";                                                                  // SuitType is a gHeart
				g.drawImage (gHeart, 35 + gCardPosition, 450 + gCardYPostionChange, 100, 100, this);  // Draws a gHeart
			    } // End if statement

			    else if (gSuitPicked == 1)  // If there is a remainder of 1
			    {

				gSuitType = "Diamond";                                                                  // SuitType is a gDiamond
				g.drawImage (gDiamond, 35 + gCardPosition, 450 + gCardYPostionChange, 100, 100, this);  // Draws a gDiamond
			    } // End else if statement

			    else if (gSuitPicked == 2)  // If there is a remainder of 2
			    {

				gSuitType = "Spade";                                                                  // SuitType is a gSpade
				g.drawImage (gSpade, 35 + gCardPosition, 450 + gCardYPostionChange, 100, 100, this);  // Draws a gSpade
			    } // End else if statement
			    else if (gSuitPicked == 3)  // If there is a remainder of 3
			    {
				gSuitType = "Club";                                                                  // SuitType is a gClub
				g.drawImage (gClub, 35 + gCardPosition, 450 + gCardYPostionChange, 100, 100, this);  // Draws a gClub
			    } // End else if statement
			    if (gSuitType == "Heart" || gSuitType == "Diamond")  // If gSuitType is a Heart and Diamond
			    {
				g.setColor (Color.red);  // Sets color to red (future strings, shapes)
			    } // End if statement

			    else if (gSuitType == "Spade" || gSuitType == "Club")  // If gSuitType is a Spade or Club
			    {
				g.setColor (Color.black);                                          // Sets color to black (future strings, shapes)
			    } // End else if statement
			    g.drawRect (10 + gCardPosition, 400 + gCardYPostionChange, 150, 199);  // Draws border for the card
			} // End if statement
			gValuePickedString = Integer.toString (gValuePickedInt);                   // ValuePickedString is the gValuePickedInt ************ << MIGHTVE USED
			if (gValuePickedInt == 1)                                                  // If the value picked is 1
			{
			    g.drawString ("A", 16 + gCardPosition, 425 + gCardYPostionChange);     // Draws an "A"
			    g.drawString ("A", 138 + gCardPosition, 590 + gCardYPostionChange);    // Draws an "A"
			} // End if statement

			else if (gValuePickedInt > 1 && gValuePickedInt < 11)  // If the value picked is 2 - 11 draws the equivalent text to that number
			{
			    g.drawString (gValuePickedString, 16 + gCardPosition, 425 + gCardYPostionChange);   // Draws the gValuePickedInt as a string to the screen
			    g.drawString (gValuePickedString, 138 + gCardPosition, 590 + gCardYPostionChange);  // Draws the gValuePickedInt as a string to the screen
			} // End else if statement

			else if (gValuePickedInt == 11)  // If the value picked is 11
			{
			    g.drawString ("J", 16 + gCardPosition, 425 + gCardYPostionChange);   // Draws an "J"
			    g.drawString ("J", 138 + gCardPosition, 590 + gCardYPostionChange);  // Draws an "J"
			} // End else if statement

			else if (gValuePickedInt == 12)  // If the value picked is 12
			{
			    g.drawString ("Q", 16 + gCardPosition, 425 + gCardYPostionChange);   // Draws an "Q"
			    g.drawString ("Q", 138 + gCardPosition, 590 + gCardYPostionChange);  // Draws an "Q"
			} // End else if statement

			else if (gValuePickedInt == 13)  // If the value picked is 13
			{
			    g.drawString ("K", 16 + gCardPosition, 425 + gCardYPostionChange);   // Draws an "K"
			    g.drawString ("K", 138 + gCardPosition, 590 + gCardYPostionChange);  // Draws an "K"
			} // End else if statement
		    } // End if statement
		    gCardPosition = gCardPosition + 70;                                          // CardPosition is added with 70
		} // End for loop
	    } // End if statement

	    if (gReveal == true)  // If dealer's card are being gRevealed
	    {
		g.setFont (gGameFont);                                      // Set to custom font
		g.drawString ("Dealer Hand: " + gDealerHandValue, 20, 30);  // Draws text with dealer's hand value
		g.setFont (gCardNumberFont);                                // Set to custom font
		if (gUserHandValue < 22 && gDealerHandValue < 22)           // If the both hands are less than 21
		{
		    if (gUserHandValue > gDealerHandValue)  // If user's hand is greater than the dealer's hand value
		    {
			// Creates a black text saying you win, making gWinBet equal true and stopping of deals
			g.setColor (Color.black);
			g.drawString ("YOU WIN", 750, 50);
			gWinBet = true;
			gStopDeal = true;
		    } // End if statement
		    else if (gDealerHandValue > gUserHandValue)  // If dealer's hand is greater than the user's hand value
		    {
			// Creates a black text saying you lose, making gLoseBet equal true and stopping of deals
			g.setColor (Color.black);
			g.drawString ("YOU LOSE", 750, 50);
			gLoseBet = true;
			gStopDeal = true;
		    } // End else if statement
		    else if (gDealerHandValue == gUserHandValue)  // If dealer's hand is the same as the user's hand value
		    {
			// Creates a black text saying you tie, making gTieBet equal true and stopping of deals
			g.setColor (Color.black);
			g.drawString ("YOU TIE (PUSH)", 750, 50);
			gTieBet = true;
			gStopDeal = true;
		    } // End else if statement
		} // End if statement
		for (int i = 0 ; i < 11 ; i++) // Run from 0 - 11 loop and deals all cards but the first
		{

		    if (gDealerHand == true)  // If it is dealing to dealer's hand
		    {
			gSuitPicked = gDealerCardHolder [i];               // Suitpicked becomes the dealer's number in card spot i
			gValuePicked = gDealerCardHolder [i];              // Valuepicked becomes the dealer's number in card spot i
			gCardYPostionChange = -350;                        // CardYPositionChange is equal to minus - 350
			gSuitPicked = gSuitPicked % 4;                     // Suit picked value divided by 4 to get the module which will find the suit
			gValuePicked = gValuePicked / 4;                   // Divides gValuePicked by 4 (Used to find value of card)
			gValuePickedInt = (int) Math.ceil (gValuePicked);  // Rounds the value picked to an int

			if (gValuePicked > 0)  // If valuePick is greater than 0 then
			{
			    g.setColor (Color.white);                                              // Set color to white
			    g.fillRect (10 + gCardPosition, 400 + gCardYPostionChange, 150, 199);  // Draw a white rectangle for the card's background

			    if (gSuitPicked == 0)  // If there is a remainder of 0
			    {
				gSuitType = "Heart";                                                                  // SuitType is a gHeart
				g.drawImage (gHeart, 35 + gCardPosition, 450 + gCardYPostionChange, 100, 100, this);  // Draws a gHeart
			    } // End if statement

			    else if (gSuitPicked == 1)  // If there is a remainder of 1
			    {

				gSuitType = "Diamond";                                                                  // SuitType is a gDiamond
				g.drawImage (gDiamond, 35 + gCardPosition, 450 + gCardYPostionChange, 100, 100, this);  // Draws a gDiamond
			    } // End else if statement

			    else if (gSuitPicked == 2)  // If there is a remainder of 2
			    {

				gSuitType = "Spade";                                                                  // SuitType is a gSpade
				g.drawImage (gSpade, 35 + gCardPosition, 450 + gCardYPostionChange, 100, 100, this);  // Draws a gSpade
			    } // End else if statement
			    else if (gSuitPicked == 3)  // If there is a remainder of 3
			    {
				gSuitType = "Club";                                                                  // SuitType is a gClub
				g.drawImage (gClub, 35 + gCardPosition, 450 + gCardYPostionChange, 100, 100, this);  // Draws a gClub
			    } // End else if statement
			    if (gSuitType == "Heart" || gSuitType == "Diamond")  // If gSuitType is a Heart and Diamond
			    {
				g.setColor (Color.red);  // Sets color to red (future strings, shapes)
			    } // End if statement

			    else if (gSuitType == "Spade" || gSuitType == "Club")  // If gSuitType is a Spade or Club
			    {
				g.setColor (Color.black);                                          // Sets color to black (future strings, shapes)
			    } // End else if statement
			    g.drawRect (10 + gCardPosition, 400 + gCardYPostionChange, 150, 199);  // Draws border for the card
			} // End if statement
			gValuePickedString = Integer.toString (gValuePickedInt);                   // ValuePickedString is the gValuePickedInt ************ << MIGHTVE USED
			if (gValuePickedInt == 1)                                                  // If the value picked is 1
			{
			    g.drawString ("A", 16 + gCardPosition, 425 + gCardYPostionChange);     // Draws an "A"
			    g.drawString ("A", 138 + gCardPosition, 590 + gCardYPostionChange);    // Draws an "A"
			} // End if statement

			else if (gValuePickedInt > 1 && gValuePickedInt < 11)  // If the value picked is 2 - 11 draws the equivalent text to that number
			{
			    g.drawString (gValuePickedString, 16 + gCardPosition, 425 + gCardYPostionChange);   // Draws the gValuePickedInt as a string to the screen
			    g.drawString (gValuePickedString, 138 + gCardPosition, 590 + gCardYPostionChange);  // Draws the gValuePickedInt as a string to the screen
			} // End else if statement

			else if (gValuePickedInt == 11)  // If the value picked is 11
			{
			    g.drawString ("J", 16 + gCardPosition, 425 + gCardYPostionChange);   // Draws an "J"
			    g.drawString ("J", 138 + gCardPosition, 590 + gCardYPostionChange);  // Draws an "J"
			} // End else if statement

			else if (gValuePickedInt == 12)  // If the value picked is 12
			{
			    g.drawString ("Q", 16 + gCardPosition, 425 + gCardYPostionChange);   // Draws an "Q"
			    g.drawString ("Q", 138 + gCardPosition, 590 + gCardYPostionChange);  // Draws an "Q"
			} // End else if statement

			else if (gValuePickedInt == 13)  // If the value picked is 13
			{
			    g.drawString ("K", 16 + gCardPosition, 425 + gCardYPostionChange);   // Draws an "K"
			    g.drawString ("K", 138 + gCardPosition, 590 + gCardYPostionChange);  // Draws an "K"
			} // End else if statement
		    } // End if statement
		    gCardPosition = gCardPosition + 70;                                          // CardPosition is added with 70
		} // End for loop

	    }
	    gCardPosition = 0;              // Card position is back to 0
	    for (int i = 0 ; i < 11 ; i++)  // Run from 0 - 11 loop and deals all cards but the first
	    {
		// This huge block of code is a repeat of the dealer's code for creating a card but with using the user's card
		gSuitPicked = gUserCardHolder [i];
		gValuePicked = gUserCardHolder [i];

		gSuitPicked = gSuitPicked % 4;
		gValuePicked = gValuePicked / 4;
		gValuePickedInt = (int) Math.ceil (gValuePicked);

		gCardYPostionChange = 0;  // cardYPositionChange is now 0

		if (gValuePicked > 0)
		{
		    g.setColor (Color.white);
		    g.fillRect (10 + gCardPosition, 400 + gCardYPostionChange, 150, 199);
		    if (gSuitPicked == 0)
		    {
			gSuitType = "Heart";
			g.drawImage (gHeart, 35 + gCardPosition, 450 + gCardYPostionChange, 100, 100, this);
		    } // End if statement
		    else if (gSuitPicked == 1)
		    {

			gSuitType = "Diamond";
			g.drawImage (gDiamond, 35 + gCardPosition, 450 + gCardYPostionChange, 100, 100, this);
		    } // End else if statement
		    else if (gSuitPicked == 2)
		    {

			gSuitType = "Spade";
			g.drawImage (gSpade, 35 + gCardPosition, 450 + gCardYPostionChange, 100, 100, this);
		    } // End else if statement
		    else if (gSuitPicked == 3)
		    {

			gSuitType = "Club";
			g.drawImage (gClub, 35 + gCardPosition, 450 + gCardYPostionChange, 100, 100, this);
		    } // End else if statement
		    if (gSuitType == "Heart" || gSuitType == "Diamond")
		    {
			g.setColor (Color.red);
		    } // End if statement
		    else if (gSuitType == "Spade" || gSuitType == "Club")
		    {
			g.setColor (Color.black);
		    } // End if statement
		    g.drawRect (10 + gCardPosition, 400 + gCardYPostionChange, 150, 199);

		} // End if statement
		gValuePickedString = Integer.toString (gValuePickedInt);

		if (gValuePickedInt == 1)
		{
		    g.drawString ("A", 16 + gCardPosition, 425 + gCardYPostionChange);
		    g.drawString ("A", 138 + gCardPosition, 590 + gCardYPostionChange);

		} // End if statement
		else if (gValuePickedInt > 1 && gValuePickedInt < 10)
		{
		    g.drawString (gValuePickedString, 16 + gCardPosition, 425 + gCardYPostionChange);
		    g.drawString (gValuePickedString, 138 + gCardPosition, 590 + gCardYPostionChange);

		} // End else if statement
		else if (gValuePickedInt == 10)
		{
		    g.drawString (gValuePickedString, 16 + gCardPosition, 425 + gCardYPostionChange);
		    g.drawString (gValuePickedString, 123 + gCardPosition, 590 + gCardYPostionChange);

		} // End else if statement
		else if (gValuePickedInt == 11)
		{
		    g.drawString ("J", 16 + gCardPosition, 425 + gCardYPostionChange);
		    g.drawString ("J", 138 + gCardPosition, 590 + gCardYPostionChange);


		} // End else if statement
		else if (gValuePickedInt == 12)
		{
		    g.drawString ("Q", 16 + gCardPosition, 425 + gCardYPostionChange);
		    g.drawString ("Q", 138 + gCardPosition, 590 + gCardYPostionChange);

		} // End else if statement
		else if (gValuePickedInt == 13)
		{
		    g.drawString ("K", 16 + gCardPosition, 425 + gCardYPostionChange);
		    g.drawString ("K", 138 + gCardPosition, 590 + gCardYPostionChange);

		} // End else if statement

		gCardPosition = gCardPosition + 70;
	    } // End for loop

	    // End of repeat

	    if (gUserHandValue > 21)  // If the user's hand value is greater than 21
	    {
		if (gAce == true)  // If the user has an ace that is still true (worth 11)
		{

		    g.setColor (gLightGreenBackground);                      // Set color to light green
		    g.setFont (gGameFont);                                   // Set to gGameFont
		    g.drawString ("User Hand: " + gUserHandValue, 20, 380);  // Draws over old text and hand value
		    gUserHandValue = gUserHandValue - 10;                    // Minuses 10 to the user's hand value
		    g.setColor (Color.black);                                // Set color the black
		    g.drawString ("User Hand: " + gUserHandValue, 20, 380);  // Draws text and the new user's hand value
		    gAce = false;                                            // Ace is now false
		} // End if statement

		else  // If not anything else
		{

		    g.setColor (Color.black);             // Set color to black
		    g.drawString ("YOU BUST", 400, 50);   // Draw you bust text
		    g.drawString ("YOU LOSE", 750, 50);   // Draw you lose text
		    gStopDeal = true;                     // Stops dealing is now true
		    gLoseBet = true;                      // Lose bet is now true
		} // End else

	    } // End if statement
	    if (gWinBet == true)  // If the user wins the bet
	    {
		setBack();
		g.setColor (Color.gray);                         // Set color to gray
		g.fillRect (725, 270, 150, 75);                  // Draws the graphic for the hit box
		g.setColor (Color.black);                        // Set color to black
		g.setFont (gCardNumberFont);                     // Set cardFont
		g.drawString ("Return to", 730, 307);            // Draws menu text line one
		g.drawString ("menu", 770, 323);                 // Draw menu text line two
		gCanReset = true;                                // You gCanReset is now true
		gWinBet = false;                                 // gWinBet is false
		
	    } // End if statement

	    if (gLoseBet == true)  // If the user loses the bet
	    {

		setBack();
		g.setColor (Color.gray);                    // Set color to gray
		g.fillRect (725, 270, 150, 75);             // Draws the graphic for the hit box
		g.setColor (Color.black);                   // Set color to black
		g.setFont (gCardNumberFont);                // Set cardFont
		g.drawString ("Return to", 730, 307);       // Draws menu text line one
		g.drawString ("menu", 770, 323);            // Draw menu text line two
		gCanReset = true;                           // You gCanReset is now true
		gLoseBet = false;                           // gLoseBet is false
		 
	    } // End if statement

	    if (gTieBet == true)  // If the user ties the bet
	    {
		g.setColor (Color.gray);                // Set color to gray
		g.fillRect (725, 270, 150, 75);         // Draws the graphic for the hit box
		g.setColor (Color.black);               // Set color to black
		g.setFont (gCardNumberFont);            // Set cardFont
		g.drawString ("Return to", 730, 307);   // Draws menu text line one
		g.drawString ("menu", 770, 323);        // Draw menu text line two
		gCanReset = true;                       // You gCanReset is now true
		gTieBet = false;                        // gTieBet is false
		 
	    } // End if statement
	} // End for statement
    } // End of update ()


    public void setBack ()  // Method to calculate new user fund
    {
	if (gWinBet == true)  // If the user wins the bet
	{
	    gUserBalance = gUserBalance + gBetBalance / 2;   // Adds half of the balance to user funds (3 to 2)
	} // End if

	if (gLoseBet == true)  // If the user loses the bet
	{
	    gUserBalance = gUserBalance - gBetBalance;  // Minuses user bet from user balance
	} // End if

    } // End setBack()


    public void reset ()  // Method to reset the game
    {
	// Resets all variables that were changed back to original state
	gGameScreenClickable = false;
	gIsTitle = true;
	gTitleClicked = false;
	gDealerHandValue = 0;
	gUserHandValue = 0;
	gCardYPostionChange = 0;
	gUserCardSlot = 0;
	gDealerCardSlot = 0;
	gCardPosition = 0;
	gStopDeal = false;
	gAce = false;
	gAceDealer = false;
	gDealerHand = false;
	gDealingToDealer = false;
	gCardOne = false;
	gReveal = false;
	gBetBalance = 0;
	for (int i = 0 ; i < 53 ; i++)  // Loop from 0 - 53
	{
	    gUserStatusDeck [i] = false;  // Sets gUserStatusDeck back to false

	} // End of for loop
	for (int i = 0 ; i < 53 ; i++)  // Loop from 0 - 53
	{
	    gDealerStatusDeck [i] = false;  // Sets gDealerStatusDeck back to false


	} // End of for loop
	for (int i = 0 ; i < 11 ; i++)  // Loop from 0 - 53
	{
	    gDealerCardHolder [i] = 0;  // Sets gDealerCardHolder to 0

	} // End of for loop
	for (int i = 0 ; i < 11 ; i++)  // Loop from 0 - 53
	{
	    gUserCardHolder [i] = 0;  // Sets gUserCardHolder to 0

	} // End of for loop

    } // End reset ()


    public void paint (Graphics g)  // Method used to paint screen
    {
	update (g); // Runs update (helps buffer objects better)
    } // End of paint ()


    public boolean mouseUp (Event e, int x, int y)  // The mouseUp method (where you let go of the mouse at)
    {


	Graphics g = getGraphics ();                                             // Allows you to access graphic (g.)
	g.setFont (gGameFont);                                                   // Set custom font
	if (gTitleClicked == false && x > 400 && x < 600 && y > 200 && y < 300)  // If gTitleClicked is false and x is between about 400 to 600 and the y is between 200 to 300
	{
	    repaint ();                  // Repaints the screen (refreshes screen)
	    gIsTitle = false;            // Makes gIsTitle now false
	    gBetScreenClickable = true;  // Makes bet screen clickable
	    gTitleClicked = true;        // Title is now clicked (true)
	} // End if statement

	if (gTitleClicked == false && x > 400 && x < 600 && y > 325 && y < 425)  // If gTitleClicked is false and x is between about 400 to 600 and the y is between 325 to 425
	{
	    System.exit (0);       // Applet exits
	    gTitleClicked = true;  // gTitleClicked is now true
	} // End if statement
	if (gBetScreenClickable == true && x > 10 && x < 180 && y > 100 && y < 200)   // If gTitleClicked is false and x is between about 10 to 180 and the y is between 100 to 200
	{
	    g.setColor (gLightGreenBackground);                     // sets color to the light green background color
	    g.drawString ("Bet Balance: " + gBetBalance, 20, 520);  // Outputs betbalance and output description text
	    bet (1);                                                // Run bet with the amount 1
	    g.setColor (Color.black);                               // Set Color to black
	    g.drawString ("Bet Balance: " + gBetBalance, 20, 520);  // Outputs betbalance and output description text
	} // End if statement
	if (gBetScreenClickable == true && x > 210 && x < 390 && y > 100 && y < 200)  //If the gameScreen is clicked and is between 210 to 390 for the x and 100 to 200 for the y
	{
	    g.setColor (gLightGreenBackground);                     // Sets color to the light green background color
	    g.drawString ("Bet Balance: " + gBetBalance, 20, 520);  // Outputs betbalance and output description text
	    bet (5);                                                // Run bet with the amount 5
	    g.setColor (Color.black);                               // Set Color to black
	    g.drawString ("Bet Balance: " + gBetBalance, 20, 520);  // Outputs betbalance and output description text


	} // End if statement
	if (gBetScreenClickable == true && x > 410 && x < 590 && y > 100 && y < 200)  //If the gameScreen is clicked and is between 410 to 590 for the x and 100 to 200 for the y
	{
	    g.setColor (gLightGreenBackground);                     // Sets color to the light green background color
	    g.drawString ("Bet Balance: " + gBetBalance, 20, 520);  // Outputs betbalance and output description text
	    bet (25);                                               // Run bet with the amount 25
	    g.setColor (Color.black);                               // Set Color to black
	    g.drawString ("Bet Balance: " + gBetBalance, 20, 520);  // Outputs betbalance and output description text
	} // End if statement
	if (gBetScreenClickable == true && x > 610 && x < 790 && y > 100 && y < 200)  //If the gameScreen is clicked and is between 610 to 790 for the x and 100 to 200 for the y
	{
	    g.setColor (gLightGreenBackground);                     // Sets color to the light green background color
	    g.drawString ("Bet Balance: " + gBetBalance, 20, 520);  // Outputs betbalance and output description text
	    bet (50);                                               // Run bet with the amount 50
	    g.setColor (Color.black);                               // Set Color to black
	    g.drawString ("Bet Balance: " + gBetBalance, 20, 520);  // Outputs betbalance and output description text

	} // End if statement
	if (gBetScreenClickable == true && x > 810 && x < 990 && y > 100 && y < 200)  //If the gameScreen is clicked and is between 810 to 990 for the x and 100 to 200 for the y
	{
	    g.setColor (gLightGreenBackground);                     // Sets color to the light green background color
	    g.drawString ("Bet Balance: " + gBetBalance, 20, 520);  // Outputs betbalance and output description text
	    bet (100);                                              // Run bet with the amount 100
	    g.setColor (Color.black);                               // Set Color to black
	    g.drawString ("Bet Balance: " + gBetBalance, 20, 520);  // Outputs betbalance and output description text
	} // End if statement


	if (gBetScreenClickable == true && x > 510 && x < 690 && y > 360 && y < 460)  // Reset Button (if the betScreen is clickable and x is between about 510 to 690 and y is about 360 to 460)
	{
	    g.setColor (gLightGreenBackground);                     // Sets color to the light green background color
	    g.drawString ("Bet Balance: " + gBetBalance, 20, 520);  // Outputs betbalance and output description text
	    gBetBalance = 0;                                        // Set gBetBalance back to 0
	    g.setColor (Color.black);                               // Set Color to black
	    g.drawString ("Bet Balance: " + gBetBalance, 20, 520);  // Outputs betbalance and output description text
	    g.setColor (gLightGreenBackground);                     // Sets color to the light green background color
	    g.fillRect (330, 500, 800, 300);                        // Draws a filled Rectangle with the parameters (x,y,width,height).
	} // End if statement

	if (gBetScreenClickable == true && x > 310 && x < 490 && y > 360 && y < 460)  //Deal Button (if the betScreen is clickable and x is between about 310 to 490 and y is about 360 to 460)
	{
	    if (gBetBalance > gUserBalance)  // If the betBalnce is greater than the gUserBalance
	    {
		g.setColor (gLightGreenBackground);                              // Sets color to the light green background color
		g.fillRect (330, 500, 800, 300);                                 // Draws a filled Rectangle with the parameters (x,y,width,height).
		g.setColor (Color.black);                                        // Set Color to black
		g.drawString ("Not enough user funds to place bet", 350, 550);   // Outputs the certain text

	    } // End if statement
	    else if (gBetBalance == 0)  // If the gBetBalance equals 0
	    {
		g.setColor (gLightGreenBackground);                           // Sets color to the light green background color
		g.fillRect (330, 500, 800, 300);                              // Draws a filled Rectangle with the parameters (x,y,width,height).
		g.setColor (Color.black);                                     // Set Color to black
		g.drawString ("Place at least 1 dollar to play", 350, 550);   // Outputs the certain text

	    } // End else if statement
	    else // If requirements aren't met then
	    {
		gGameScreenClickable = true;            // The gGameScreenClickable equals true (You can now click on the game screen)
		gBetScreenClickable = false;            // The betScreenClickabe equals false (You can't click on the bet screen)
		repaint ();                             // Paints the screen again
		deal ();                                // Runs deal method
		repaint ();                             // Paints the screen again
		gUserCardSlot = gUserCardSlot + 1;      // gUserCardSlot increases by 1
		deal ();                                // Runs deal method
		repaint ();                             // Paints the screen again
		gDealerHand = true;                     // The gDealerHand is now true (start dealing dealers)
		gDealingToDealer = true;                // Now dealing to dealer
		gCardOne = true;                        // Cardback is now done
		deal ();                                // Runs deal method
		repaint ();                             // Paints the screen again
		gDealerCardSlot = gDealerCardSlot + 1;  // gUserCardSlot increases by 1
		deal ();                                // Runs deal method

	    } // End else statement
	} // End if statement

	if (gCanReset == true && x > 725 && x < 875 && y > 270 && y < 345)  // If you click the return to menu button (can click the reset button and x is between about 725 to 875 and y to 270 345)
	{
	    reset ();    // Runs reset method
	    repaint ();  // Paints the screen again
	}

	if (gStopDeal == false && gGameScreenClickable == true && x > 325 && x < 475 && y > 270 && y < 345)  // Hit Button (if game screen is clickable and x is between around 325 to 476 and y is about 270 to 345)
	{

	    repaint ();                         // Paints the screen again
	    gDealingToDealer = false;           // Deal to dealer is false
	    gUserCardSlot = gUserCardSlot + 1;  // The gUserCardSlot is added plus one
	    deal ();                            // Runs deal method
	}
	if (gStopDeal == false && gGameScreenClickable == true && x > 500 && x < 650 && y > 270 && y < 345)  //Stand Button (if game screen is clickable and x is between around 500 to 650 and y is about 270 to 345)
	{
	    for (int i = 0 ; i < 11 ; i++)  // For loop that runs from 0 to 11

		{
		    if (gDealerHandValue < 17)  // If the gDealerHandValue is less than 17
		    {
			gDealingToDealer = true;                // Makes gDealingToDealer into true
			repaint ();                             // Paints the screen again
			gDealerCardSlot = gDealerCardSlot + 1;  // The gDealerCardSlot is added plus one
			deal ();                                // Runs deal method
		    } // End if statement
		} // End for loop

	    repaint ();        // Paints the screen again
	    gReveal = true;    // Reveal is now true (gReveal dealer's cards)
	    gCardOne = false;  // Cardback is now gone

	}

	return true;  // Returns true for the mouse
    } // End of mouseUp ()
} //End of Blackjack ()


