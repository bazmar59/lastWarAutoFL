package bazmar.lastwar.autofl.utils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class LastLinesReader {
	public static List<String> readLastLines(File file, int numLines) throws IOException {
		List<String> result = new ArrayList<>();
		try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
			long fileLength = file.length() - 1;
			int lines = 0;
			StringBuilder builder = new StringBuilder();

			for (long pointer = fileLength; pointer >= 0; pointer--) {
				raf.seek(pointer);
				char c = (char) raf.read();
				if (c == '\n') {
					lines++;
					if (lines == numLines + 1) {
						break;
					}
					result.add(builder.reverse().toString());
					builder = new StringBuilder();
				} else {
					builder.append(c);
				}
			}
			result.add(builder.reverse().toString());
		}
		return result;
	}
}