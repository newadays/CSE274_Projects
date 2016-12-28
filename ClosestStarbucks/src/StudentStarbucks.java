import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/*
 * 1. I used a grid based data structure. What the grid does is it partitions up the entire world
 * into grid based on latitude and longitude. Then each grid square acts as a bucket and holds all of 
 * the latitude and longitude locations that reside within the grid square. 
 * 
 * 2. The worst case running time for build is going to be O(n), when n is the number of Starbucks
 * locations. This is the case because we have to look at each individual location to determine where 
 * it belongs in the grid.  
 * The worst case running time for get nearest would also be O(n) as well.  There could be an instance where 
 * all of the Starbucks locations are in one grid square, therefore the method would have to look at all of 
 * the items in the grid.
 * 
 * I fully understand this problem, but for whatever reason I can't get the error down as much as I would like to.
 * I need to work on my 3 other projects that are due the Tuesday this is due, so I am going to have to stop here.
 * If I had more time, I know for certain that I would be getting the error down even further.
 */
public class StudentStarbucks extends Starbucks {
	
	// grid[longitude][latitude]
	private double latPartition = 3; // Latitude (-90,90)
	private double lngPartition = 6; // Longitude (-180,180)
	Object[][] grid = new Object[(int)(180/latPartition)][(int)(360/lngPartition)];
	
	//Latitude = up and down 
	//Longitude = side to side
	@Override
	public void build(StarbucksLocation[] allLocations) {
		// TODO Auto-generated method stub
		StarbucksLocation loc;
		int lat, lng;
		int count = 0;
		// Total locations: 7675
		for(int i = 0; i<allLocations.length; i++) {
			loc = allLocations[i];
			
			lat = (int) ((loc.lat/latPartition) + (((180/latPartition)/2)-1));
			lng = (int) ((loc.lng/lngPartition) + (((360/lngPartition)/2)-1));
			
			
			if(grid[lng][lat] == null) {
				grid[lng][lat] = new ArrayList<StarbucksLocation>();
			}
			if(((ArrayList<StarbucksLocation>)grid[lng][lat]).size() == 0) {
				// Deep Copy
				StarbucksLocation sb = new StarbucksLocation(loc.city, loc.address, loc.lng, loc.lat);
				((ArrayList<StarbucksLocation>)grid[lng][lat]).add(sb);
				//count++;
			} else {
				ArrayList<StarbucksLocation> bucket = (ArrayList<StarbucksLocation>)grid[lng][lat];
				boolean close = false;
				for(StarbucksLocation sb : bucket) {
					if((Math.abs(sb.lat-loc.lat) < 0.00001) && (Math.abs(sb.lng-loc.lng) < 0.00001)) {
						close = true;
						break;
					}
				}
				if(!close) {
					// Deep Copy
					StarbucksLocation sb = new StarbucksLocation(loc.city, loc.address, loc.lng, loc.lat);
					((ArrayList<StarbucksLocation>)grid[lng][lat]).add(sb);
					//count++;
				}
			}
			//count++;
		}
		System.out.println(count);
	}
	//Latitude = up and down by 10 degrees
	//Longitude = side to side by 20 degrees
	// grid[longitude][latitude]
	@Override
	public StarbucksLocation getNearest(double lng, double lat) {
		// TODO Auto-generated method stub
		int latAryIndx = (int)((lat/latPartition)+(((180/latPartition)/2)-1));
		int lngAryIndx = (int)((lng/lngPartition)+(((360/lngPartition)/2)-1));
		int side = 1;
		int sideNum = 0;
		int squareSize = 1;
		int latU,latD,lngR,lngL;
		int curLat,curLng;
		int bucketsSeen = 1;
		int totalBuckets = grid.length * grid[0].length;
		double d = Double.MAX_VALUE;
		
		boolean found = false;
		ArrayList<StarbucksLocation> bucket = (ArrayList<StarbucksLocation>)grid[lngAryIndx][latAryIndx];
		StarbucksLocation closestStarbucks = null;
		if(bucket != null) { // Found a location, search squares to the side
			squareSize = 3;
			sideNum = 2;
			for(StarbucksLocation sb : bucket) {
				if(Starbucks.distance(lng, lat, sb.lng, sb.lat) < d) {
					d = Starbucks.distance(lng, lat, sb.lng, sb.lat);
					closestStarbucks = sb;
				}
			}
			curLat = (int)((latAryIndx-(((180/latPartition)/2)-1))*latPartition);
			curLng = (int)((lngAryIndx-(((360/lngPartition)/2)-1))*lngPartition);
			latU = curLat;
			while(Starbucks.distance(curLng, curLat, curLng, latU) < d) {
				latU += latPartition;
				if(latU > 90) {
					latU = -90;
				}
			}
			curLat -= latPartition;
			if(curLat < -90) {
				curLat = 90;
			}
			latD = (int)(curLat-latPartition);
			while(Starbucks.distance(curLng, curLat, curLng, latU) < d) {
				latD -= latPartition;
				if(latD <= -90) {
					latD = 90;
				}
			}
			lngR = curLng;
			while(Starbucks.distance(curLng, curLat, curLng, latU) < d) {
				lngR += lngPartition;
				if(lngR > 180) {
					lngR = -180;
				}
			}
			curLng -= lngPartition;
			if(curLng < -180) {
				curLng = 180;
			}
			lngL =  (int)(curLng-lngPartition);
			while(Starbucks.distance(curLng, curLat, curLng, latU) < d) {
				lngL -= lngPartition;
				if(lngL < -180) {
					lngL = 180;
				}
			}
			latU = (int)((latU/latPartition)+(((180/latPartition)/2)-1));
			latD = (int)((latD/latPartition)+(((180/latPartition)/2)-1));
			lngR = (int)((lngR/lngPartition)+(((360/lngPartition)/2)-1));
			lngL = (int)((lngL/lngPartition)+(((360/lngPartition)/2)-1));
			for(int i = lngL; i<= lngR; i++) {
				if(i>=(360/lngPartition)) {
					i = 0;
				} else if(i<0){
					i = (int)(360/lngPartition)-1;
				}
				if(grid[i] != null) {
					for(int j = latD; j <= latU; j++) {
						if(j >= (180/latPartition)) {
							j = 0;
						}
						bucket = (ArrayList<StarbucksLocation>)grid[i][j];
						if(bucket != null) {
							for(StarbucksLocation sb : bucket) {
								if(Starbucks.distance(lng, lat, sb.lng, sb.lat) < d) {
									d = Starbucks.distance(lng, lat, sb.lng, sb.lat);
									closestStarbucks = sb;
								}
							}
						}
					}
				}
			}
				
		} else { // No Locations found in bucket
			boolean done = false;
			latAryIndx--;
			lngAryIndx++;
			if(latAryIndx==-1) 			{latAryIndx = grid[0].length-1;}
			if(lngAryIndx>=grid.length) 	{lng = 0;}
			squareSize = 3;
			sideNum = 2;
			
			// grid[longitude][latitude]
			while(!done) {
				
				switch(side) {  // grid[longitude][latitude]
				case 1: 
					latAryIndx++;
					if(latAryIndx < 0) {
						latAryIndx = grid[0].length-1;
					} else if(latAryIndx >= grid[0].length-1) {
						latAryIndx = 0;
					}
					bucket = (ArrayList<StarbucksLocation>)grid[lngAryIndx][latAryIndx];
					if(bucket != null) {
						found = true;
						for(StarbucksLocation sb : bucket) {
							if(Starbucks.distance(lng, lat, sb.lng, sb.lat) < d) {
								d = Starbucks.distance(lng, lat, sb.lng, sb.lat);
								closestStarbucks = sb;
							}
						}
					}
					sideNum--;
					break;
				case 2: 
					lngAryIndx--;
					if(lngAryIndx < 0) {
						lngAryIndx = grid.length-1;
					} else if (lngAryIndx >= grid.length) {
						lngAryIndx = 0;
					}
					bucket = (ArrayList<StarbucksLocation>)grid[lngAryIndx][latAryIndx];
					if(bucket != null) {
						found = true;
						for(StarbucksLocation sb : bucket) {
							if(Starbucks.distance(lng, lat, sb.lng, sb.lat)< d) {
								d = Starbucks.distance(lng, lat, sb.lng, sb.lat);
								closestStarbucks = sb;
							}
						}
					}
					sideNum--;
					break;
				case 3:
					latAryIndx--;
					if(latAryIndx < 0) {
						latAryIndx = grid[0].length-1;
					} else if(latAryIndx >= grid[0].length-1) {
						latAryIndx = 0;
					}
					bucket = (ArrayList<StarbucksLocation>)grid[lngAryIndx][latAryIndx];
					if(bucket != null) {
						found = true;
						for(StarbucksLocation sb : bucket) {
							if(Starbucks.distance(lng, lat, sb.lng, sb.lat)< d) {
								d = Starbucks.distance(lng, lat, sb.lng, sb.lat);
								closestStarbucks = sb;
							}
						}
					}
					sideNum--;
					break;
				case 4:
					lngAryIndx++;
					if(lngAryIndx < 0) {
						lngAryIndx = grid.length-1;
					} else if (lngAryIndx >= grid.length) {
						lngAryIndx = 0;
					}
					bucket = (ArrayList<StarbucksLocation>)grid[lngAryIndx][latAryIndx];
					if(bucket != null) {
						found = true;
						for(StarbucksLocation sb : bucket) {
							if(Starbucks.distance(lng, lat, sb.lng, sb.lat)< d) {
								d = Starbucks.distance(lng, lat, sb.lng, sb.lat);
								closestStarbucks = sb;
							}
						}
					}
					sideNum--;
					break;
				}
				bucketsSeen++;
				if(sideNum == 0) {
					sideNum = squareSize-1;
					side++;
				}
				if(bucketsSeen == squareSize*squareSize) {
					squareSize += 2;
					if(found) {
						done = true;
					}
					latAryIndx--;
					lngAryIndx++;
					if(latAryIndx<0) 			{latAryIndx = grid[0].length-1;}
					if(lngAryIndx>=grid.length) 	{lng = 0;}
					side = 1;
					sideNum = squareSize-1;
				}
				if(bucketsSeen == totalBuckets) {
					return null;
				}
				}
			curLat = (int)((lat/latPartition)+(((180/latPartition)/2)-1));
			curLng = (int)((lng/lngPartition)+(((360/lngPartition)/2)-1));
			latU = curLat;
			while(Starbucks.distance(curLng, curLat, curLng, latU) < d) {
				latU += latPartition;
				if(latU > 90) {
					latU = -90;
				}
			}
			curLat -= latPartition;
			if(curLat < -90) {
				curLat = 90;
			}
			latD = curLat;
			while(Starbucks.distance(curLng, curLat, curLng, latU) < d) {
				latD -= latPartition;
				if(latD <= -90) {
					latD = 90;
				}
			}
			lngR = curLng;
			while(Starbucks.distance(curLng, curLat, curLng, latU) < d) {
				lngR += lngPartition;
				if(lngR > 180) {
					lngR = -180;
				}
			}
			curLng -= lngPartition;
			if(curLng < -180) {
				curLng = 180;
			}
			lngL =  curLng;
			while(Starbucks.distance(curLng, curLat, curLng, latU) < d) {
				lngL -= lngPartition;
				if(lngL < -180) {
					lngL = 180;
				}
			}
			latU = (int)((latU/latPartition)+(((180/latPartition)/2)-1));
			latD = (int)((latD/latPartition)+(((180/latPartition)/2)-1));
			lngR = (int)((lngR/lngPartition)+(((360/lngPartition)/2)-1));
			lngL = (int)((lngL/lngPartition)+(((360/lngPartition)/2)-1));
			for(int i = lngL; i<= lngR; i++) {
				if(i>=(360/lngPartition)) {
					i = 0;
				} else if(i<0){
					i = (int)(360/lngPartition)-1;
				}
				if(grid[i] != null) {
					for(int j = latD; j <= latU; j++) {
						if(j > (180/latPartition)) {
							j = 0;
						}
						bucket = (ArrayList<StarbucksLocation>)grid[i][j];
						if(bucket != null) {
							for(StarbucksLocation sb : bucket) {
								if(Starbucks.distance(lng, lat, sb.lng, sb.lat) < d) {
									d = Starbucks.distance(lng, lat, sb.lng, sb.lat);
									closestStarbucks = sb;
								}
							}
						}
					}
				}
			}
				
				
			}
		// Deep Copy
		StarbucksLocation rtn = new StarbucksLocation(closestStarbucks.city,
				closestStarbucks.address, closestStarbucks.lng, closestStarbucks.lat);
		return rtn;
	}
	
}
