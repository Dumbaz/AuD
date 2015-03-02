/*
 * search.c
 *
 *  Created on: Mar 4, 2014
 *      Author: h4llow3en
 */

#include "../h/main.h"

/*
 * 0x10  16   // North
 * 0x20  32   // South
 * 0x30  48   // North and South
 * 0x40  64   // West
 * 0x50  80   // North and West
 * 0x60  96   // South and West
 * 0x70  112  // North, South and West
 * 0x80  128  // East
 * 0x90  144  // North and East
 * 0xA0  160  // South and East
 * 0xB0  176  // North, South and East
 * 0xC0  192  // West and East
 * 0xD0  208  // North, West and East
 * 0xE0  224  // South, West and East
 * 0xF0  240  // North, South, West and East
 */



short search(){
	////printf("SEARCHING\n");
	short nodes[4][3]={{0,0,0},{0,0,0},{0,0,0},{0,0,0}};
	short x = position[0];
	short y = position[1];
	//map[x][y+6][0] = Robot_GetIntersections(); //Kreuzung erhalten

	unsigned short intersection = mapIntersection(x, y + 6);

	//ecrobot_status_monitor("ist hier der kaefer");
	fillNodes(nodes, intersection/16); //Knoten füllen
	fillUsed(nodes); //existierende Knoten auf "besucht" testen
	map[x][y+6][1] = 2; //Kreuzung auf "besucht" stellen
	if (findWay(nodes) == 1){  //Weg finden
		return 1;
	}


	return 0;
}

short mapIntersection(short x, short y){
	map[x][y][0] = Robot_GetIntersections(); //Kreuzung erhalten
	//printf("Getting Intersection at Point %d;%d : %d\n", x, y-6, map[x][y][0]);
	//ecrobot_status_monitor("Ist hier ein Kaefer");
	return map[x][y][0];
}

short fillNodes(short nodes[][3], short intersection){
	//printf("FILLING\n");
	short xPos=position[0];
	switch(intersection){
	case 1: if(xPos != 0) nodes[0][0]= -1;
			//printf("North filled\n");      //North
			break;
	case 2: nodes[2][0]= 1;
			//printf("South filled\n");     //South
			break;
	case 3: if(xPos != 0) nodes[0][0]= -1;       //North and South
			    nodes[2][0]= 1;
			    //printf("North and South filled\n");
			break;
	case 4: nodes[3][1]= -1;      //West
			//printf("West filled\n");
			break;
	case 5: if(xPos != 0) nodes[0][0]= -1;       //North and West
    			nodes[3][1]= -1;
    			//printf("North and West filled\n");
    		break;
	case 6: nodes[2][0]= 1;      //South and West
				nodes[3][1]= -1;
				//printf("South and West filled\n");
			break;
	case 7: if(xPos != 0) nodes[0][0]= -1;       //North, West and South
				nodes[2][0]= 1;
				nodes[3][1]= -1;
				//printf("North and West and South filled\n");
			break;
	case 8: nodes[1][1]= 1; 		 //East
			//printf("East filled\n");

			break;
	case 9: if(xPos != 0) nodes[0][0]= -1;		 //North and East
				nodes[1][1]= 1;
				//printf("North and East filled\n");
			break;
	case 10: nodes[1][1]= 1;      //South and East
				nodes[2][0]= 1;
				//printf("South and East filled\n");

			break;
	case 11: if(xPos != 0) nodes[0][0]= -1;		 //North, South and East
				nodes[1][1]= 1;
				nodes[2][0]= 1;
				//printf("North and East and South filled\n");

			break;
	case 12: nodes[1][1]= 1;      //West and East
				nodes[3][1]= -1;
				//printf("West and East filled\n");
			break;
	case 13: if(xPos != 0) nodes[0][0]= -1;      //North, West and East
				nodes[1][1]= 1;
				nodes[3][1]= -1;
				//printf("North and West and East filled\n");
			break;
	case 14: nodes[1][1]= 1;      //South, West and East
				nodes[2][0]= 1;
				nodes[3][1]= -1;
				//printf("South and West and East filled\n");
			break;
	case 15: if(xPos != 0) nodes[0][0]= -1;      //All Directions
				nodes[1][1]= 1;
				nodes[2][0]= 1;
				nodes[3][1]= -1;
				//printf("All Directions\n");
	}
return 0;
}

short fillUsed(short nodes[][3]){
	//printf("USED\n");
	short x = position[0];
	short y = position[1] + 6;
	if(map[x-1][y][1]==2){ //Teste auf "besucht"?
		nodes[0][2]=1; //Setze auf "gefunden"
	} else if((nodes[0][0] != 0 || nodes[0][1] != 0) && map[x][y][1] != 2){
		setNeighborNode(x-1,y, 2); //Setze Verbindung beim Nachbar
	}
	if(map[x][y+1][1]==2){ //Teste auf "besucht"?
		nodes[1][2]=1; //Setze auf "gefunden"
	} else if((nodes[1][0] != 0 || nodes[1][1] != 0) && map[x][y][1] != 2){
		setNeighborNode(x, y+1, 3); //Setze Verbindung beim Nachbar
	}
	if(map[x+1][y][1]==2){ //Teste auf "besucht"?
		nodes[2][2]=1; //Setze auf "gefunden"
	} else if((nodes[2][0] != 0 || nodes[2][1] != 0) && map[x][y][1] != 2){
		setNeighborNode(x+1, y, 0); //Setze Verbindung beim Nachbar
	}
	if(map[x][y-1][1]==2){ //Teste auf "besucht"?
		nodes[3][2]=1; //Setze auf "gefunden"
	} else if((nodes[3][0] != 0 || nodes[3][1] != 0) && map[x][y][1] != 2){
		setNeighborNode(x, y-1, 1); //Setze Verbindung beim Nachbar
	}



	return 0;
}

short setNeighborNode(short x, short y, short direction){
	//printf("NEIGHBOR\n");
	switch(direction){
	case 0: map[x][y][0] = map[x][y][0] + 16;  //Nachbar im Norden
		break;
	case 1: map[x][y][0] = map[x][y][0] + 128; //Nacahbar im Osten
		break;
	case 2: map[x][y][0] = map[x][y][0] + 32;  //Nachbar im Süden
		break;
	case 3: map[x][y][0] = map[x][y][0] + 64;  //Nachbar im Westen
		break;
	}
	map[x][y][1] = 1;

	return 0;
}

short findWay(short nodes[][3]){
	//printf("FIND WAY\n");
	short x = position[0];
	short y = position[1];
	short r, i;
	i = 0;
	r = rand() % 4; //0;

	for (i=0;i<4;i++){
		if ((nodes[r][0] != 0 || nodes[r][1] != 0) && nodes[r][2] != 1){
			if (Robot_Move(x + nodes[r][0], y + nodes[r][1]) == 2){
					tokenCount++;//Move! :-D
				}
				position[0] = x + nodes[r][0];
				position[1] = y + nodes[r][1];
				if (tokenCount == 3){
					map[x][y + 6][1] = 2;
				}
				//printf("position= %d;%d\n", position[0],position[1]);
				return 0;
		}
		r = (r + 1) %4;
	}

	/*if(((nodes[0][2] == 1 && nodes[1][2] == 1) && nodes[2][2] ==1) && nodes[3][2] ==1){
		return 1;
	} else if(nodes[0][0] != 0 && nodes[0][2] != 1){ //Existiert dieser?
		Robot_Move(x + nodes[0][0], y + nodes[0][1]);//Move! :-D
		position[0] = x + nodes[0][0];
		position[1] = y + nodes[0][1];
		//printf("position= %d;%d\n", position[0],position[1]);
		return 0;
	} else if(nodes[1][1] != 0 && nodes[1][2] != 1){ //Existiert dieser?
		Robot_Move(x + nodes[1][0], y + nodes[1][1]);//Move! :-D
		position[0] = x + nodes[1][0];
		position[1] = y + nodes[1][1];
		//printf("position= %d;%d\n", position[0],position[1]);
		return 0;
	} else if(nodes[2][0] && nodes[2][2] != 1){ //Existiert dieser?
		Robot_Move(x + nodes[2][0], y + nodes[2][1]);//Move! :-D
		position[0] = x + nodes[2][0];
		position[1] = y + nodes[2][1];
		//printf("position= %d;%d\n", position[0],position[1]);
		return 0;
	} else if(nodes[3][1] != 0 && nodes[3][2] != 1){ //Existiert dieser?
		Robot_Move(x + nodes[3][0], y + nodes[3][1]);//Move! :-D
		position[0] = x + nodes[3][0];
		position[1] = y + nodes[3][1];
		//printf("position= %d;%d\n", position[0],position[1]);
		return 0;
	}*/
	//printf("RETURN 1\n");
	return 1;
}
