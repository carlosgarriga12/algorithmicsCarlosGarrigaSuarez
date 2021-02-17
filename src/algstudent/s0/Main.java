package algstudent.s0;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		MatrixOperations mo = new MatrixOperations("exampleMatrix.txt");
		mo.travelPath(3, 0);
	}

}
