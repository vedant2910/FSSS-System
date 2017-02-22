package fsss_package;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class ExcelExporter {
	public ExcelExporter() {
	}

	public void exportTable(JTable table, File file) throws IOException {
		TableModel model = table.getModel();
		FileWriter out = new FileWriter(file);

		for (int i = 0; i < model.getColumnCount(); i++) {
			out.write(model.getColumnName(i) + "\t");
		}
		out.write("\n");

		for (int i = 0; i < model.getRowCount(); i++) {
			for (int j = 0; j < model.getColumnCount(); j++) {
				if (j == 0) {
					out.write(model.getValueAt(i, j).toString() + "\t");
				} else {
					out.write("\"" + model.getValueAt(i, j).toString() + "\t"
							+ "\"");
				}
			}
			out.write("\n");
		}
		out.close();
		System.out.println("write to " + file);
	}
}

class ExcelOpener {
	public ExcelOpener() {
	}

	public void openTable(File file) throws IOException {
		Runtime run = Runtime.getRuntime();

		String lcOSName = System.getProperty("os.name").toLowerCase();
		boolean MAC_OS_X = lcOSName.startsWith("mac os x");
		if (MAC_OS_X) {
			run.exec("open " + file);
		} else {
			run.exec("cmd.exe /c start " + file);
		}
		System.out.println(file + " opened");
	}
}