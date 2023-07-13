package edu.iastate.cs228.hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * This class reconstructs a message that is  archived with a binary-tree-based algorithm
 * from an '.arch' file. It builds a binary tree from an encoded string and stores those
 * characters in the binary tree leaves. It contains seven fields: staticCharIdx,
 * encodeString, encodingString, MsgTreeFile, left, right, and
 * payloadCharacter.
 * 
 *  @author Nick McCullough
 *  
 * Ex.
 * ---------------------- 
 * Character | Encoding
 * ---------------------- 
 *    a      |    0
 *    !      |    100
 *    d      |    1010 
 *    c      |    1011
 *    r      |    110
 *    b      |    111
 *    
 * With the above encoding, the bit string:
 * 10110101011101101010100 is parsed as
 * 1011|0|1010|111|0|110|1010|100
 * which is decoded as: "cadbard!"
 * 
 */

public class MsgTree
{
	/*
	 * Character that this node in the tree represents
	 */
	public char payloadCharacter;

	/*
	 * Left subtree, a '0' in the encoded message
	 */
	public MsgTree left;

	/*
	 * Right subtree, a '1' in the encoded message
	 */
	public MsgTree right;

	/*
	 * Index used to iterate over the characters of the encoded message string
	 */
	private static int staticCharIdx = 0;

	/*
	 * String placeholder of the encoded message
	 */
	private static String encodeString;

	/*
	 * String representation of the binary encoding for the message
	 */
	private static String encodingString;

	/*
	 * File that contains the encoded message
	 */
	private static String MsgTreeFile;

	/*
	 * Initialize a boolean to track if the file is the correct type, ".arch"
	 */
	private static boolean incorrectFileType = false;

	/**
	 * MsgTree constructor that initializes payloadChar with a given
	 * payloadCharacter.
	 *
	 * @param payloadChar character to set as the payload.
	 */
	public MsgTree (char payloadChar)
	{
		this.payloadCharacter = payloadChar;
	}

	/**
	 * MsgTree Constructor that constructs a MsgTree object using an encoded string.
	 * It initializes payloadChar, then updates the left and right MsgTree based on
	 * the static index and encoded string. If a node (left or right) is internal
	 * (payload '^'), it creates a new MsgTree using the encoded string.
	 * 
	 * @param encodingString The encoded string used to build the MsgTree.
	 */
	public MsgTree (String encodingString)
	{
		this.payloadCharacter = encodingString.charAt(staticCharIdx); // set payloadChar to current char
		staticCharIdx++; // increment to next char
		this.left = new MsgTree(encodingString.charAt(staticCharIdx)); // new MsgTree for left node
		if (this.left.payloadCharacter == '^')
		{
			this.left = new MsgTree(encodingString); // create a new MsgTree with encodingString
		}

		staticCharIdx++; // increment to next char
		this.right = new MsgTree(encodingString.charAt(staticCharIdx)); // new MsgTree for right node
		if (this.right.payloadCharacter == '^')
		{
			this.right = new MsgTree(encodingString); // create a new MsgTree with encodingString
		}
	}

	/**
	 * This main method prompts the user for the name of an input file, confirms the
	 * file has the .arch extension and exists, then decodes the message from the
	 * file. It builds a binary tree from the encoded string in the file, prints the
	 * character codes, decodes the message and prints the decoded message along
	 * with statistics about it.
	 * 
	 * @param args An array of command-line arguments for the application.
	 * @throws FileNotFoundException if the file does not exist or is not found.
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner inputScanner = new Scanner(System.in); // new scanner object
		System.out.print("Please enter filename to decode: ");

		// base case file checking
		try
		{
			MsgTreeFile = inputScanner.nextLine().trim(); // set file and trim
			File newFile = new File(MsgTreeFile); // new file ojbect
			if (!newFile.getAbsolutePath().endsWith(".arch")) // bad file name verification
			{
				incorrectFileType = true;
				inputScanner.close(); // close scanner
				throw new FileNotFoundException(); // exception
			}
		} catch (Exception e)
		{
			if (incorrectFileType) // file found, but incorrect type
			{
				inputScanner.close();
				throw new FileNotFoundException(" File '" + MsgTreeFile
						+ "' is incorrect file type. Must have .arch extension needed per specifications.");
			}
			else // file doesn't exist
			{
				inputScanner.close();
				throw new FileNotFoundException(" File '" + MsgTreeFile + "' does not exist. ");
			}
		}

		inputScanner.close();
		createEncodingStringsFromFile();
		MsgTree mainMsgTree = new MsgTree(encodeString); // new MsgTree using encodeString

		System.out.println("character   \t       code: \n----------------------------");
		printCodes(mainMsgTree, ""); // print the character codes, per spec

		System.out.println("----------------------------\nMESSAGE:");
		String decodedMessage = decode(mainMsgTree, encodingString); // decode message, per spec

		System.out.println(decodedMessage); // print decoded message
		statistics(encodingString, decodedMessage); // call statistics method
	}

	/**
	 * Constructs strings from the file input, assigning to encodeString and
	 * encodingString. It reads lines from the file, processes them, and handles
	 * their formatting using a StringBuilder.
	 *
	 * @throws FileNotFoundException if the input file doesn't exist.
	 */
	private static void createEncodingStringsFromFile( ) throws FileNotFoundException
	{
		try (Scanner fileScanner = new Scanner(new File(MsgTreeFile))) // try to scan file
		{
			encodeString = fileScanner.nextLine(); // first line, assign to encodeString
			StringBuilder buildAString = new StringBuilder(encodeString);
			String fileString = fileScanner.nextLine(); // second line, fileString

			for (int x = 0; x < fileString.length(); x++) // iterate thru second line
			{
				if (fileString.charAt(x) != '1' && fileString.charAt(x) != '0')
				{
					// incorrect character checking
					buildAString.append("\n").append(fileString);
					encodingString = fileScanner.nextLine();
					break; // break loop
				}
				else
				{
					encodingString = fileString; // assigns '1' and '0' to encodingString
				}
			}
			encodeString = buildAString.toString();

		} catch (FileNotFoundException e) // exception handling
		{
			throw new FileNotFoundException(" The file you chose '" + MsgTreeFile + "' does not exist.");
		}
	}

	/**
	 * Recursively prints binary codes for characters in a MsgTree. If root is null,
	 * it returns. For nodes with character payloads, it prints the character (or
	 * newline escape sequence) and its binary code. It then recursively processes
	 * left subtree (appending '0' to code) and right subtree (appending '1' to
	 * code).
	 *
	 * @param codes the root of the MsgTree.
	 * @param msg   the accumulated binary code for the current node.
	 */
	public static void printCodes(MsgTree codes, String msg)
	{
		if (codes == null) // base case recursion checking
		{
			return;
		}
		char payload = codes.payloadCharacter;

		if (payload != '^')
		{
			if (payload == '\n') // if the payload is a newline character
			{
				System.out.print("\\" + "n" + "\t\t\t"); // escape + tab print sequence
			}
			else
			{
				System.out.print(codes.payloadCharacter + "\t\t\t"); // else print the character payload + tabs
			}
			System.out.println(msg); // print the msg String, the binary codes
		}
		printCodes(codes.left, msg + "0"); // recursively call left subtree, appending '0'
		printCodes(codes.right, msg + "1"); // recursively call right subtree, appending '1'
	}

	/**
	 * This method decodes a binary message using a MsgTree. It scans each bit in
	 * the input message, traverses the tree (left for '0', right for '1'),
	 * constructing the decoded message from leaf node characters.
	 *
	 * @param codes   a MsgTree for message decoding.
	 * @param message a binary string to be decoded.
	 */
	public static String decode(MsgTree codes, String message)
	{
		StringBuilder decodedMessage = new StringBuilder();
		MsgTree currentNode = codes; // current node at root of MsgTree

		for (int i = 0; i < message.length(); i++) // iterate through binary message
		{
			// if current character is '0', move to left child, if '1', move to right child
			currentNode = message.charAt(i) == '0' ? currentNode.left : currentNode.right;

			if (currentNode.left == null || currentNode.right == null) // leaf node checking
			{
				decodedMessage.append(currentNode.payloadCharacter);
				currentNode = codes; // post decoding, reset back to the root node
			}
		}

		if (currentNode != codes)
		{

			decodedMessage.append(currentNode.payloadCharacter); // append last char to the decoded message
		}
		return decodedMessage.toString(); // return the decoded message as a string
	}

	/**
	 * This method prints statistical information about the given encoded and
	 * decoded strings.
	 *
	 * @param encodingString the binary string generated by the encoding process.
	 * @param decodedString  the string decoded from the encodingString.
	 */
	public static void statistics(String encodingString, String decodedString)
	{
		System.out.println();
		System.out.println("STATISTICS:");

		// average number of bits per character is calculated by dividing length of
		// encoded string by length of decoded string
		System.out.printf("Avg bits/char:       \t%.1f%n", encodingString.length() / (double) decodedString.length());
		System.out.println("Total Characters:  \t" + decodedString.length());

		// space saving percentage calculated by subtracting ratio of decoded string
		// length to encoded string length from 1 and multiplying by 100
		System.out.printf("Space Savings:     \t%.1f%%%n",
				(1d - decodedString.length() / (double) encodingString.length()) * 100);
	}

}