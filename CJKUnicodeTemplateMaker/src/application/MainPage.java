package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainPage {
	@FXML
	private TextField txtExtensionName;

	@FXML
	private TextField txtStart;

	@FXML
	private TextField txtEnd;

	@FXML
	private TextArea taText;

	private static final String TABLE_START = "{{hatnote|This list is a single Unicode block. Fo"
			+ "r an overview of all CJK ideograph blocks in Unicode, see [[CJK Unified Ideographs]].}}\n"
			+ "{| class=\"wikitable nounderlines\" style=\"borde"
			+ "r-collapse:collapse;font-size:large;text-align:center;font-family:'sim-ch_n5100'"
			+ ",unifont,LastResort,sans-serif;\"\n| colspan=\"17\" style=\"font-size:small;font"
			+ "-family:sans-serif\" | '''[[CJK Unified Ideographs Extension ";

	private static final String TABLE_MIDDLE = "]]'''{{ref label|U30000_as_of_Unicode_version|1"
			+ "}}{{ref label|U30000_grey|2}}<br />[https://www.unicode.org/charts/PDF/U30000."
			+ "pdf Official Unicode Consortium code chart] (PDF)\n|- style=\"font-size:small;"
			+ "font-family:sans-serif,serif\"\n| style=\"width:45pt\" | &nbsp; || style=\"wi"
			+ "dth:20pt\"  | '''0''' || style=\"width:20pt\"  | '''1''' || style=\"width:20"
			+ "pt\"  | '''2''' || style=\"width:20pt\"  | '''3''' || style=\"width:20pt\" "
			+ " | '''4''' || style=\"width:20pt\"  | '''5''' || style=\"width:20pt\"  | '"
			+ "''6''' || style=\"width:20pt\"  | '''7''' || style=\"width:20pt\"  | '''8'''"
			+ " || style=\"width:20pt\"  | '''9''' || style=\"width:20pt\"  | '''A''' || st"
			+ "yle=\"width:20pt\"  | '''B''' || style=\"width:20pt\"  | '''C''' || style=\""
			+ "width:20pt\"  | '''D''' || style=\"width:20pt\"  | '''E''' || style=\"width:20pt\"  | '''F'''\r\n" + "";

	private static final String TABLE_ENTRY_START = "|- lang=\"zh-Hani\"\n"
			+ "| style=\"font-size:small;font-family:sans-serif\" lang=\"mul\" | '''U+";

	private static final String TABLE_NOTES = "|-\n| colspan=\"17\" style=\"font-size"
			+ ":small;font-family:sans-serif;text-align:left\" | '''Notes'''\n"
			+ ":1.{{note|U2A700_as_of_Unicode_version}}As of Unicode version 14.0\n";

	private static final String TABLE_NOTES_GREYS = ":2.{{note|U2A700_grey}}Grey are"
			+ "as indicate non-assigned code points\r\n";

	public void execute() {
		StringBuilder strText = new StringBuilder(TABLE_START + txtExtensionName.getText() + TABLE_MIDDLE);
		int intStart = Integer.parseInt(txtStart.getText(), 16);
		int intEnd = Integer.parseInt(txtEnd.getText(), 16);
		boolean hasGreys = false;
		for (int i = intStart - (intStart % 16); i <= intEnd + ((intEnd % 16 == 15) ? 0 : 16 - (intEnd % 16));) {
			strText.append(TABLE_ENTRY_START);
			strText.append(Integer.toHexString(i / 16).toUpperCase());
			strText.append("x'''\n");
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 8; k++) {
					if (i >= intStart && i <= intEnd) {
						String symbol = new String(Character.toChars(i));
						strText.append("| [[wikt:");
						strText.append(symbol);
						strText.append('|');
						strText.append(symbol);
						strText.append("]] |");
					} else {
						strText.append("| title=\"Reserved\" style=\"background-color:#CCCCCC;\" ||");
						hasGreys = true;
					}
					i++;
				}
				strText.append('\n');
			}
		}
		strText.append(TABLE_NOTES);
		if (hasGreys) {
			strText.append(TABLE_NOTES_GREYS);
		}
		strText.append("|}");
		taText.setText(strText.toString().replace("]] |\n", "]]\n"));
	}
}
