/** A main class for the Closest Pair algorithms
 *  Programming assignment for
 *  CSI403 Algorithms and Data Structures
 *  University at Albany - SUNY
 *  
 * Instructions: Implement methods: 
 * 1) getCPBruteForce()
 * 2) getCPDivideAndConquer()
 * As discussed in class and in the assignment part (a)
 */
package closestpair;

import java.util.HashSet;
import java.util.Arrays;


public class ClosestPair {
	
	/** TODO: IMPLEMENT 
	 *  A brute force method for the closest pair
	 *  @returns an array of exactly the two closest points
	 *  IMPORTANT: DO NOT CHANGE THIS METHOD SIGNATURE,
	 *  ONLY THE BODY WILL BE CONSIDERED FOR GRADING
	 */
	//http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Text/Brute-force.html
	public static Point[] getCPBruteForce (Point[] pts)  {
	int n = pts.length;
	double min = 1000000;
	Point [] pt2 = new Point[2]; //creating a new array to hold the closest points
	for(int i = 0; i < n; i++) {
		for(int j = i+1; j < n; j++) {
			if(pts[i].dist(pts[j]) < min) {
				min = pts[i].dist(pts[j]);
				pt2[0] = pts [i]; //setting the points to the new array.
				pt2[1] = pts[j];
				
			}
		}
	}
		 return pt2;
	
	}
	
	/** A driver for the Divide-And-Conquer method for the closest pair
	 *  takes unsorted array of points, sorts them and invokes 
	 *  the recursive method you are required to implement
	 *  
	 *  @returns an array of exactly the two closest points
	 *  IMPORTANT: DO NOT CHANGE THIS METHOD
	 */
	
	public static Point[] getCPDivideAndConquer(Point[] pts) {
		Point[] ptsX = Point.sortByX(pts); 
		Point[] ptsY = Point.sortByY(pts);
	
	
		return getCPDivideAndConquer(ptsX, ptsY);
		}
		
		
	
	
	/** TODO: IMPLEMENT 
	 *  A Divide-And-Conquer method for the closest pair
	 *  takes as input the points sorted by increasing x
	 *  and y coordinates in ptsX and ptsY respectively
	 *  @returns an array of exactly the two closest points.
	 *  IMPORTANT: DO NOT CHANGE THIS METHOD SIGNATURE,
	 *  ONLY THE BODY WILL BE CONSIDERED FOR GRADING
	 */
	//https://www.tutorialspoint.com/How-to-divide-an-array-into-half-in-java#:~:text=Using%20the%20copyOfRange()%20method,2%20to%20length%20to%20other.
	public static Point[] getCPDivideAndConquer(Point[] ptsX, Point[] ptsY) {
		
		Point [] newpt = new Point[2];
	if(ptsX.length <= 4 && ptsY.length <= 4) {
			int i = 0;
			Point [] twovarArray = new Point[2];
			twovarArray[0] = ptsX[i];
			twovarArray[1] = ptsY[i];
			return getCPBruteForce(ptsX);
			} 
		
		int mid = ptsX.length / 2; //cuts the array size in half for indexing later on
		int two = ptsX.length;
		
		Point[] first = Arrays.copyOfRange(ptsX, 0, mid); //copies ranges of array
														//and creates 2 new ones
		Point[] second = Arrays.copyOfRange(ptsX, mid, two);
		Point[] firstY = Arrays.copyOfRange(ptsY, 0, mid); //copies ranges of array
														//and creates 2 new ones
Point[] secondY = Arrays.copyOfRange(ptsY, mid, two);
		for(int i = 0; i < first.length - 1 && i < firstY.length ; i++ ) { //i checks points up till the halfway mark of the array
			double min = 100000;
			for(int j = 0; j < second.length - 1 && j < secondY.length - 1; j++) { //j will check from mid to end
				if(first[i].dist(second[j]) < min) {
					min = first[i].dist(second[j]); //puts in points into distance form and then gets min
					newpt[0] = first[i];
					newpt[1] = second[j];
				}
				else if(firstY[i].dist(secondY[j]) < min){
					min = firstY[i].dist(secondY[j]);
					newpt[0] = firstY[i];
					newpt[1] = secondY[j];
				}
				
				
		 if (first[i].dist(second[j]).equals(firstY[i].dist(secondY[j])) == false){
			
					return(getCPDivideAndConquer(newpt)); // if distances are not equal it will run again
				}
			}
		}

		return newpt;
		
	}
}