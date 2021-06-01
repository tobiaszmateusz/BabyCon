
package com.example.babycon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class Fabb extends Activity {




    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fab);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        int wiek_=(int) b.get("wiek");
        String wzrost_=(String) b.get("wzrost");
        String plec=(String) b.get("plec");
        int wzrost__=Integer.parseInt(wzrost_);
        int mont=checkMonth(wiek_);
        int plecnumber = 999;

        String comp = new String("chłopak");

// Evaluates to true
        if(comp.equals(plec))
        {
            plecnumber=0;
        }else{plecnumber=1;}



    }
    public int checkMonth(int wiek)
    {
        return wiek/30;
    }

    public int siatkaCentyl(int wzost,int waga, int wiek, int plecnumber)
    {

        int[][] siatkacentylwzrostCH = new int[23][7];
        siatkacentylwzrostCH[0][0] = 46;
        siatkacentylwzrostCH[0][1] = 48;
        siatkacentylwzrostCH[0][2] = 49;
        siatkacentylwzrostCH[0][3] = 50;
        siatkacentylwzrostCH[0][4] = 51;
        siatkacentylwzrostCH[0][5] = 52;
        siatkacentylwzrostCH[0][6] = 53;
        //
        siatkacentylwzrostCH[1][0] = 51;
        siatkacentylwzrostCH[1][1] = 52;
        siatkacentylwzrostCH[1][2] = 53;
        siatkacentylwzrostCH[1][3] = 54;
        siatkacentylwzrostCH[1][4] = 55;
        siatkacentylwzrostCH[1][5] = 56;
        siatkacentylwzrostCH[1][6] = 57;
        //
        siatkacentylwzrostCH[2][0] = 54;
        siatkacentylwzrostCH[2][1] = 56;
        siatkacentylwzrostCH[2][2] = 57;
        siatkacentylwzrostCH[2][3] = 58;
        siatkacentylwzrostCH[2][4] = 59;
        siatkacentylwzrostCH[2][5] = 61;
        siatkacentylwzrostCH[2][6] = 62;
        //
        siatkacentylwzrostCH[3][0] = 57;
        siatkacentylwzrostCH[3][1] = 58;
        siatkacentylwzrostCH[3][2] = 60;
        siatkacentylwzrostCH[3][3] = 61;
        siatkacentylwzrostCH[3][4] = 66;
        siatkacentylwzrostCH[3][5] = 64;
        siatkacentylwzrostCH[3][6] = 65;
        //
        siatkacentylwzrostCH[4][0] = 60;
        siatkacentylwzrostCH[4][1] = 61;
        siatkacentylwzrostCH[4][2] = 62;
        siatkacentylwzrostCH[4][3] = 63;
        siatkacentylwzrostCH[4][4] = 65;
        siatkacentylwzrostCH[4][5] = 67;
        siatkacentylwzrostCH[4][6] = 68;
        //
        siatkacentylwzrostCH[5][0] = 62;
        siatkacentylwzrostCH[5][1] = 63;
        siatkacentylwzrostCH[5][2] = 64;
        siatkacentylwzrostCH[5][3] = 66;
        siatkacentylwzrostCH[5][4] = 67;
        siatkacentylwzrostCH[5][5] = 68;
        siatkacentylwzrostCH[5][6] = 70;
        //
        siatkacentylwzrostCH[6][0] = 64;
        siatkacentylwzrostCH[6][1] = 65;
        siatkacentylwzrostCH[6][2] = 66;
        siatkacentylwzrostCH[6][3] = 67;
        siatkacentylwzrostCH[6][4] = 68;
        siatkacentylwzrostCH[6][5] = 70;
        siatkacentylwzrostCH[6][6] = 72;
        //
        siatkacentylwzrostCH[7][0] = 65;
        siatkacentylwzrostCH[7][1] = 67;
        siatkacentylwzrostCH[7][2] = 68;
        siatkacentylwzrostCH[7][3] = 69;
        siatkacentylwzrostCH[7][4] = 71;
        siatkacentylwzrostCH[7][5] = 72;
        siatkacentylwzrostCH[7][6] = 73;
        //
        siatkacentylwzrostCH[8][0] = 67;
        siatkacentylwzrostCH[8][1] = 68;
        siatkacentylwzrostCH[8][2] = 69;
        siatkacentylwzrostCH[8][3] = 71;
        siatkacentylwzrostCH[8][4] = 72;
        siatkacentylwzrostCH[8][5] = 73;
        siatkacentylwzrostCH[8][6] = 74;
        //
        siatkacentylwzrostCH[9][0] = 68;
        siatkacentylwzrostCH[9][1] = 69;
        siatkacentylwzrostCH[9][2] = 71;
        siatkacentylwzrostCH[9][3] = 72;
        siatkacentylwzrostCH[9][4] = 73;
        siatkacentylwzrostCH[9][5] = 75;
        siatkacentylwzrostCH[9][6] = 76;
        //
        siatkacentylwzrostCH[10][0] = 68;
        siatkacentylwzrostCH[10][1] = 70;
        siatkacentylwzrostCH[10][2] = 72;
        siatkacentylwzrostCH[10][3] = 73;
        siatkacentylwzrostCH[10][4] = 75;
        siatkacentylwzrostCH[10][5] = 76;
        siatkacentylwzrostCH[10][6] = 77;
        //
        siatkacentylwzrostCH[11][0] = 70;
        siatkacentylwzrostCH[11][1] = 71;
        siatkacentylwzrostCH[11][2] = 73;
        siatkacentylwzrostCH[11][3] = 74;
        siatkacentylwzrostCH[11][4] = 76;
        siatkacentylwzrostCH[11][5] = 78;
        siatkacentylwzrostCH[11][6] = 79;
        //
        siatkacentylwzrostCH[12][0] = 71;
        siatkacentylwzrostCH[12][1] = 73;
        siatkacentylwzrostCH[12][2] = 74;
        siatkacentylwzrostCH[12][3] = 76;
        siatkacentylwzrostCH[12][4] = 77;
        siatkacentylwzrostCH[12][5] = 79;
        siatkacentylwzrostCH[12][6] = 80;
        //
        siatkacentylwzrostCH[13][0] = 72;
        siatkacentylwzrostCH[13][1] = 74;
        siatkacentylwzrostCH[13][2] = 75;
        siatkacentylwzrostCH[13][3] = 77;
        siatkacentylwzrostCH[13][4] = 79;
        siatkacentylwzrostCH[13][5] = 80;
        siatkacentylwzrostCH[13][6] = 82;
        //
        siatkacentylwzrostCH[14][0] = 74;
        siatkacentylwzrostCH[14][1] = 75;
        siatkacentylwzrostCH[14][2] = 76;
        siatkacentylwzrostCH[14][3] = 78;
        siatkacentylwzrostCH[14][4] = 80;
        siatkacentylwzrostCH[14][5] = 81;
        siatkacentylwzrostCH[14][6] = 83;
        //
        siatkacentylwzrostCH[15][0] = 75;
        siatkacentylwzrostCH[15][1] = 76;
        siatkacentylwzrostCH[15][2] = 77;
        siatkacentylwzrostCH[15][3] = 79;
        siatkacentylwzrostCH[15][4] = 81;
        siatkacentylwzrostCH[15][5] = 82;
        siatkacentylwzrostCH[15][6] = 84;
        //
        siatkacentylwzrostCH[16][0] = 76;
        siatkacentylwzrostCH[16][1] = 77;
        siatkacentylwzrostCH[16][2] = 78;
        siatkacentylwzrostCH[16][3] = 80;
        siatkacentylwzrostCH[16][4] = 82;
        siatkacentylwzrostCH[16][5] = 83;
        siatkacentylwzrostCH[16][6] = 85;
        //
        siatkacentylwzrostCH[17][0] = 76;
        siatkacentylwzrostCH[17][1] = 78;
        siatkacentylwzrostCH[17][2] = 80;
        siatkacentylwzrostCH[17][3] = 81;
        siatkacentylwzrostCH[17][4] = 83;
        siatkacentylwzrostCH[17][5] = 85;
        siatkacentylwzrostCH[17][6] = 86;
        //
        siatkacentylwzrostCH[18][0] = 77;
        siatkacentylwzrostCH[18][1] = 79;
        siatkacentylwzrostCH[18][2] = 81;
        siatkacentylwzrostCH[18][3] = 82;
        siatkacentylwzrostCH[18][4] = 84;
        siatkacentylwzrostCH[18][5] = 86;
        siatkacentylwzrostCH[18][6] = 87;
        //
        siatkacentylwzrostCH[19][0] = 78;
        siatkacentylwzrostCH[19][1] = 80;
        siatkacentylwzrostCH[19][2] = 82;
        siatkacentylwzrostCH[19][3] = 84;
        siatkacentylwzrostCH[19][4] = 85;
        siatkacentylwzrostCH[19][5] = 87;
        siatkacentylwzrostCH[19][6] = 88;
        //
        siatkacentylwzrostCH[20][0] = 79;
        siatkacentylwzrostCH[20][1] = 81;
        siatkacentylwzrostCH[20][2] = 83;
        siatkacentylwzrostCH[20][3] = 84;
        siatkacentylwzrostCH[20][4] = 86;
        siatkacentylwzrostCH[20][5] = 88;
        siatkacentylwzrostCH[20][6] = 90;
        //
        siatkacentylwzrostCH[21][0] = 80;
        siatkacentylwzrostCH[21][1] = 82;
        siatkacentylwzrostCH[21][2] = 83;
        siatkacentylwzrostCH[21][3] = 85;
        siatkacentylwzrostCH[21][4] = 87;
        siatkacentylwzrostCH[21][5] = 89;
        siatkacentylwzrostCH[21][6] = 91;
        //
        siatkacentylwzrostCH[22][0] = 81;
        siatkacentylwzrostCH[22][1] = 83;
        siatkacentylwzrostCH[22][2] = 84;
        siatkacentylwzrostCH[22][3] = 86;
        siatkacentylwzrostCH[22][4] = 87;
        siatkacentylwzrostCH[22][5] = 90;
        siatkacentylwzrostCH[22][6] = 92;
        //
        siatkacentylwzrostCH[23][0] = 82;
        siatkacentylwzrostCH[23][1] = 84;
        siatkacentylwzrostCH[23][2] = 85;
        siatkacentylwzrostCH[23][3] = 87;
        siatkacentylwzrostCH[23][4] = 89;
        siatkacentylwzrostCH[23][5] = 91;
        siatkacentylwzrostCH[23][6] = 93;
        //
        siatkacentylwzrostCH[24][0] = 83;
        siatkacentylwzrostCH[24][1] = 84;
        siatkacentylwzrostCH[24][2] = 86;
        siatkacentylwzrostCH[24][3] = 88;
        siatkacentylwzrostCH[24][4] = 90;
        siatkacentylwzrostCH[24][5] = 92;
        siatkacentylwzrostCH[24][6] = 94;
/////////////////////////////////////////////////

        int[][] siatkacentylwzrostDZ = new int[23][7];
        siatkacentylwzrostDZ[0][0] = 46;
        siatkacentylwzrostDZ[0][1] = 47;
        siatkacentylwzrostDZ[0][2] = 48;
        siatkacentylwzrostDZ[0][3] = 49;
        siatkacentylwzrostDZ[0][4] = 51;
        siatkacentylwzrostDZ[0][5] = 52;
        siatkacentylwzrostDZ[0][6] = 53;
        //
        siatkacentylwzrostDZ[1][0] = 50;
        siatkacentylwzrostDZ[1][1] = 51;
        siatkacentylwzrostDZ[1][2] = 52;
        siatkacentylwzrostDZ[1][3] = 53;
        siatkacentylwzrostDZ[1][4] = 55;
        siatkacentylwzrostDZ[1][5] = 56;
        siatkacentylwzrostDZ[1][6] = 57;
        //
        siatkacentylwzrostDZ[2][0] = 53;
        siatkacentylwzrostDZ[2][1] = 54;
        siatkacentylwzrostDZ[2][2] = 56;
        siatkacentylwzrostDZ[2][3] = 57;
        siatkacentylwzrostDZ[2][4] = 58;
        siatkacentylwzrostDZ[2][5] = 59;
        siatkacentylwzrostDZ[2][6] = 61;
        //
        siatkacentylwzrostDZ[3][0] = 56;
        siatkacentylwzrostDZ[3][1] = 57;
        siatkacentylwzrostDZ[3][2] = 58;
        siatkacentylwzrostDZ[3][3] = 60;
        siatkacentylwzrostDZ[3][4] = 61;
        siatkacentylwzrostDZ[3][5] = 62;
        siatkacentylwzrostDZ[3][6] = 63;
        //
        siatkacentylwzrostDZ[4][0] = 57;
        siatkacentylwzrostDZ[4][1] = 59;
        siatkacentylwzrostDZ[4][2] = 61;
        siatkacentylwzrostDZ[4][3] = 62;
        siatkacentylwzrostDZ[4][4] = 64;
        siatkacentylwzrostDZ[4][5] = 65;
        siatkacentylwzrostDZ[4][6] = 66;
        //
        siatkacentylwzrostDZ[5][0] = 60;
        siatkacentylwzrostDZ[5][1] = 61;
        siatkacentylwzrostDZ[5][2] = 63;
        siatkacentylwzrostDZ[5][3] = 64;
        siatkacentylwzrostDZ[5][4] = 66;
        siatkacentylwzrostDZ[5][5] = 67;
        siatkacentylwzrostDZ[5][6] = 68;
        //
        siatkacentylwzrostDZ[6][0] = 62;
        siatkacentylwzrostDZ[6][1] = 63;
        siatkacentylwzrostDZ[6][2] = 64;
        siatkacentylwzrostDZ[6][3] = 66;
        siatkacentylwzrostDZ[6][4] = 67;
        siatkacentylwzrostDZ[6][5] = 69;
        siatkacentylwzrostDZ[6][6] = 70;
        //
        siatkacentylwzrostDZ[7][0] = 63;
        siatkacentylwzrostDZ[7][1] = 64;
        siatkacentylwzrostDZ[7][2] = 66;
        siatkacentylwzrostDZ[7][3] = 67;
        siatkacentylwzrostDZ[7][4] = 69;
        siatkacentylwzrostDZ[7][5] = 70;
        siatkacentylwzrostDZ[7][6] = 72;
        //
        siatkacentylwzrostDZ[8][0] = 64;
        siatkacentylwzrostDZ[8][1] = 66;
        siatkacentylwzrostDZ[8][2] = 67;
        siatkacentylwzrostDZ[8][3] = 69;
        siatkacentylwzrostDZ[8][4] = 70;
        siatkacentylwzrostDZ[8][5] = 72;
        siatkacentylwzrostDZ[8][6] = 73;
        //
        siatkacentylwzrostDZ[9][0] = 66;
        siatkacentylwzrostDZ[9][1] = 67;
        siatkacentylwzrostDZ[9][2] = 69;
        siatkacentylwzrostDZ[9][3] = 70;
        siatkacentylwzrostDZ[9][4] = 72;
        siatkacentylwzrostDZ[9][5] = 73;
        siatkacentylwzrostDZ[9][6] = 75;
        //
        siatkacentylwzrostDZ[10][0] = 67;
        siatkacentylwzrostDZ[10][1] = 68;
        siatkacentylwzrostDZ[10][2] = 70;
        siatkacentylwzrostDZ[10][3] = 72;
        siatkacentylwzrostDZ[10][4] = 73;
        siatkacentylwzrostDZ[10][5] = 75;
        siatkacentylwzrostDZ[10][6] = 76;
        //
        siatkacentylwzrostDZ[11][0] = 68;
        siatkacentylwzrostDZ[11][1] = 69;
        siatkacentylwzrostDZ[11][2] = 71;
        siatkacentylwzrostDZ[11][3] = 73;
        siatkacentylwzrostDZ[11][4] = 75;
        siatkacentylwzrostDZ[11][5] = 76;
        siatkacentylwzrostDZ[11][6] = 77;
        //
        siatkacentylwzrostDZ[12][0] = 69;
        siatkacentylwzrostDZ[12][1] = 71;
        siatkacentylwzrostDZ[12][2] = 72;
        siatkacentylwzrostDZ[12][3] = 74;
        siatkacentylwzrostDZ[12][4] = 76;
        siatkacentylwzrostDZ[12][5] = 77;
        siatkacentylwzrostDZ[12][6] = 79;
        //
        siatkacentylwzrostDZ[13][0] = 70;
        siatkacentylwzrostDZ[13][1] = 72;
        siatkacentylwzrostDZ[13][2] = 74;
        siatkacentylwzrostDZ[13][3] = 75;
        siatkacentylwzrostDZ[13][4] = 77;
        siatkacentylwzrostDZ[13][5] = 79;
        siatkacentylwzrostDZ[13][6] = 80;
        //
        siatkacentylwzrostDZ[14][0] = 71;
        siatkacentylwzrostDZ[14][1] = 73;
        siatkacentylwzrostDZ[14][2] = 75;
        siatkacentylwzrostDZ[14][3] = 76;
        siatkacentylwzrostDZ[14][4] = 78;
        siatkacentylwzrostDZ[14][5] = 80;
        siatkacentylwzrostDZ[14][6] = 81;
        //
        siatkacentylwzrostDZ[15][0] = 72;
        siatkacentylwzrostDZ[15][1] = 74;
        siatkacentylwzrostDZ[15][2] = 76;
        siatkacentylwzrostDZ[15][3] = 77;
        siatkacentylwzrostDZ[15][4] = 79;
        siatkacentylwzrostDZ[15][5] = 81;
        siatkacentylwzrostDZ[15][6] = 83;
        //
        siatkacentylwzrostDZ[16][0] = 74;
        siatkacentylwzrostDZ[16][1] = 75;
        siatkacentylwzrostDZ[16][2] = 77;
        siatkacentylwzrostDZ[16][3] = 79;
        siatkacentylwzrostDZ[16][4] = 81;
        siatkacentylwzrostDZ[16][5] = 83;
        siatkacentylwzrostDZ[16][6] = 84;
        //
        siatkacentylwzrostDZ[17][0] = 75;
        siatkacentylwzrostDZ[17][1] = 76;
        siatkacentylwzrostDZ[17][2] = 78;
        siatkacentylwzrostDZ[17][3] = 80;
        siatkacentylwzrostDZ[17][4] = 82;
        siatkacentylwzrostDZ[17][5] = 84;
        siatkacentylwzrostDZ[17][6] = 85;
        //
        siatkacentylwzrostDZ[18][0] = 76;
        siatkacentylwzrostDZ[18][1] = 77;
        siatkacentylwzrostDZ[18][2] = 79;
        siatkacentylwzrostDZ[18][3] = 81;
        siatkacentylwzrostDZ[18][4] = 83;
        siatkacentylwzrostDZ[18][5] = 84;
        siatkacentylwzrostDZ[18][6] = 86;
        //
        siatkacentylwzrostDZ[19][0] = 77;
        siatkacentylwzrostDZ[19][1] = 78;
        siatkacentylwzrostDZ[19][2] = 80;
        siatkacentylwzrostDZ[19][3] = 82;
        siatkacentylwzrostDZ[19][4] = 84;
        siatkacentylwzrostDZ[19][5] = 86;
        siatkacentylwzrostDZ[19][6] = 87;
        //
        siatkacentylwzrostDZ[20][0] = 77;
        siatkacentylwzrostDZ[20][1] = 79;
        siatkacentylwzrostDZ[20][2] = 81;
        siatkacentylwzrostDZ[20][3] = 83;
        siatkacentylwzrostDZ[20][4] = 85;
        siatkacentylwzrostDZ[20][5] = 87;
        siatkacentylwzrostDZ[20][6] = 88;
        //
        siatkacentylwzrostDZ[21][0] = 78;
        siatkacentylwzrostDZ[21][1] = 80;
        siatkacentylwzrostDZ[21][2] = 82;
        siatkacentylwzrostDZ[21][3] = 84;
        siatkacentylwzrostDZ[21][4] = 86;
        siatkacentylwzrostDZ[21][5] = 88;
        siatkacentylwzrostDZ[21][6] = 89;
        //
        siatkacentylwzrostDZ[22][0] = 79;
        siatkacentylwzrostDZ[22][1] = 81;
        siatkacentylwzrostDZ[22][2] = 83;
        siatkacentylwzrostDZ[22][3] = 85;
        siatkacentylwzrostDZ[22][4] = 86;
        siatkacentylwzrostDZ[22][5] = 89;
        siatkacentylwzrostDZ[22][6] = 90;
        //
        siatkacentylwzrostDZ[23][0] = 80;
        siatkacentylwzrostDZ[23][1] = 82;
        siatkacentylwzrostDZ[23][2] = 84;
        siatkacentylwzrostDZ[23][3] = 86;
        siatkacentylwzrostDZ[23][4] = 88;
        siatkacentylwzrostDZ[23][5] = 90;
        siatkacentylwzrostDZ[23][6] = 92;
        //
        siatkacentylwzrostDZ[24][0] = 81;
        siatkacentylwzrostDZ[24][1] = 83;
        siatkacentylwzrostDZ[24][2] = 84;
        siatkacentylwzrostDZ[24][3] = 87;
        siatkacentylwzrostDZ[24][4] = 89;
        siatkacentylwzrostDZ[24][5] = 91;
        siatkacentylwzrostDZ[24][6] = 93;

        ///////////////////////////////////////
        double[][] siatkacentylwagaCH = new double[23][7];
        siatkacentylwagaCH[0][0] = 2.5;
        siatkacentylwagaCH[0][1] = 2.9;
        siatkacentylwagaCH[0][2] = 3.0;
        siatkacentylwagaCH[0][3] = 3.3;
        siatkacentylwagaCH[0][4] = 3.7;
        siatkacentylwagaCH[0][5] = 4.0;
        siatkacentylwagaCH[0][6] = 4.2;
        //
        siatkacentylwagaCH[1][0] = 3.5;
        siatkacentylwagaCH[1][1] = 3.8;
        siatkacentylwagaCH[1][2] = 4.2;
        siatkacentylwagaCH[1][3] = 4.6;
        siatkacentylwagaCH[1][4] = 5.0;
        siatkacentylwagaCH[1][5] = 5.3;
        siatkacentylwagaCH[1][6] = 5.7;
        //
        siatkacentylwagaCH[2][0] = 4.3;
        siatkacentylwagaCH[2][1] = 4.7;
        siatkacentylwagaCH[2][2] = 5.1;
        siatkacentylwagaCH[2][3] = 5.6;
        siatkacentylwagaCH[2][4] = 6.1;
        siatkacentylwagaCH[2][5] = 6.6;
        siatkacentylwagaCH[2][6] = 7.0;
        //
        siatkacentylwagaCH[3][0] = 5.0;
        siatkacentylwagaCH[3][1] = 5.4;
        siatkacentylwagaCH[3][2] = 5.9;
        siatkacentylwagaCH[3][3] = 6.4;
        siatkacentylwagaCH[3][4] = 6.9;
        siatkacentylwagaCH[3][5] = 7.4;
        siatkacentylwagaCH[3][6] = 8.0;
        //
        siatkacentylwagaCH[4][0] = 5.5;
        siatkacentylwagaCH[4][1] = 6.0;
        siatkacentylwagaCH[4][2] = 6.5;
        siatkacentylwagaCH[4][3] = 7.0;
        siatkacentylwagaCH[4][4] = 7.5;
        siatkacentylwagaCH[4][5] = 8.0;
        siatkacentylwagaCH[4][6] = 8.5;
        //
        siatkacentylwagaCH[5][0] = 6.0;
        siatkacentylwagaCH[5][1] = 6.5;
        siatkacentylwagaCH[5][2] = 7.0;
        siatkacentylwagaCH[5][3] = 7.5;
        siatkacentylwagaCH[5][4] = 8.0;
        siatkacentylwagaCH[5][5] = 8.5;
        siatkacentylwagaCH[5][6] = 9.2;
        //
        siatkacentylwagaCH[6][0] = 6.4;
        siatkacentylwagaCH[6][1] = 6.9;
        siatkacentylwagaCH[6][2] = 7.4;
        siatkacentylwagaCH[6][3] = 7.9;
        siatkacentylwagaCH[6][4] = 8.6;
        siatkacentylwagaCH[6][5] = 9.1;
        siatkacentylwagaCH[6][6] = 9.7;
        //
        siatkacentylwagaCH[7][0] = 6.9;
        siatkacentylwagaCH[7][1] = 7.1;
        siatkacentylwagaCH[7][2] = 7.8;
        siatkacentylwagaCH[7][3] = 8.3;
        siatkacentylwagaCH[7][4] = 9.0;
        siatkacentylwagaCH[7][5] = 9.5;
        siatkacentylwagaCH[7][6] = 10.1;
        //
        siatkacentylwagaCH[8][0] = 7.0;
        siatkacentylwagaCH[8][1] = 7.5;
        siatkacentylwagaCH[8][2] = 8.0;
        siatkacentylwagaCH[8][3] = 8.5;
        siatkacentylwagaCH[8][4] = 9.4;
        siatkacentylwagaCH[8][5] = 9.9;
        siatkacentylwagaCH[8][6] = 10.5;
        //
        siatkacentylwagaCH[9][0] = 7.3;
        siatkacentylwagaCH[9][1] = 7.7;
        siatkacentylwagaCH[9][2] = 8.3;
        siatkacentylwagaCH[9][3] = 8.9;
        siatkacentylwagaCH[9][4] = 9.6;
        siatkacentylwagaCH[9][5] = 10.2;
        siatkacentylwagaCH[9][6] = 10.9;
        //
        siatkacentylwagaCH[10][0] = 7.5;
        siatkacentylwagaCH[10][1] = 8.0;
        siatkacentylwagaCH[10][2] = 8.5;
        siatkacentylwagaCH[10][3] = 9.2;
        siatkacentylwagaCH[10][4] = 9.8;
        siatkacentylwagaCH[10][5] = 10.5;
        siatkacentylwagaCH[10][6] = 11.2;
        //
        siatkacentylwagaCH[11][0] = 7.7;
        siatkacentylwagaCH[11][1] = 8.2;
        siatkacentylwagaCH[11][2] = 8.8;
        siatkacentylwagaCH[11][3] = 9.5;
        siatkacentylwagaCH[11][4] = 10.2;
        siatkacentylwagaCH[11][5] = 10.8;
        siatkacentylwagaCH[11][6] = 11.5;
        //
        siatkacentylwagaCH[12][0] = 7.9;
        siatkacentylwagaCH[12][1] = 8.4;
        siatkacentylwagaCH[12][2] = 9.0;
        siatkacentylwagaCH[12][3] = 9.7;
        siatkacentylwagaCH[12][4] = 10.4;
        siatkacentylwagaCH[12][5] = 11.1;
        siatkacentylwagaCH[12][6] = 11.8;
        //
        siatkacentylwagaCH[13][0] = 8.0;
        siatkacentylwagaCH[13][1] = 8.6;
        siatkacentylwagaCH[13][2] = 9.2;
        siatkacentylwagaCH[13][3] = 9.8;
        siatkacentylwagaCH[13][4] = 10.7;
        siatkacentylwagaCH[13][5] = 11.4;
        siatkacentylwagaCH[13][6] = 12.1;
        //
        siatkacentylwagaCH[14][0] = 8.2;
        siatkacentylwagaCH[14][1] = 8.8;
        siatkacentylwagaCH[14][2] = 9.4;
        siatkacentylwagaCH[14][3] = 10.1;
        siatkacentylwagaCH[14][4] = 10.8;
        siatkacentylwagaCH[14][5] = 11.7;
        siatkacentylwagaCH[14][6] = 12.5;
        //
        siatkacentylwagaCH[15][0] = 8.4;
        siatkacentylwagaCH[15][1] = 9.0;
        siatkacentylwagaCH[15][2] = 9.6;
        siatkacentylwagaCH[15][3] = 10.3;
        siatkacentylwagaCH[15][4] = 11.1;
        siatkacentylwagaCH[15][5] = 11.9;
        siatkacentylwagaCH[15][6] = 12.7;
        //
        siatkacentylwagaCH[16][0] = 8.5;
        siatkacentylwagaCH[16][1] = 9.2;
        siatkacentylwagaCH[16][2] = 9.8;
        siatkacentylwagaCH[16][3] = 10.5;
        siatkacentylwagaCH[16][4] = 11.3;
        siatkacentylwagaCH[16][5] = 12.2;
        siatkacentylwagaCH[16][6] = 13.0;
        //
        siatkacentylwagaCH[17][0] = 8.7;
        siatkacentylwagaCH[17][1] = 9.4;
        siatkacentylwagaCH[17][2] = 10.0;
        siatkacentylwagaCH[17][3] = 10.7;
        siatkacentylwagaCH[17][4] = 11.6;
        siatkacentylwagaCH[17][5] = 12.4;
        siatkacentylwagaCH[17][6] = 13.2;
        //
        siatkacentylwagaCH[18][0] = 8.9;
        siatkacentylwagaCH[18][1] = 9.5;
        siatkacentylwagaCH[18][2] = 10.1;
        siatkacentylwagaCH[18][3] = 10.8;
        siatkacentylwagaCH[18][4] = 11.7;
        siatkacentylwagaCH[18][5] = 12.6;
        siatkacentylwagaCH[18][6] = 13.5;
        //
        siatkacentylwagaCH[19][0] = 9.0;
        siatkacentylwagaCH[19][1] = 9.7;
        siatkacentylwagaCH[19][2] = 10.3;
        siatkacentylwagaCH[19][3] = 11.5;
        siatkacentylwagaCH[19][4] = 12.0;
        siatkacentylwagaCH[19][5] = 12.8;
        siatkacentylwagaCH[19][6] = 13.7;
        //
        siatkacentylwagaCH[20][0] = 9.2;
        siatkacentylwagaCH[20][1] = 9.8;
        siatkacentylwagaCH[20][2] = 10.5;
        siatkacentylwagaCH[20][3] = 11.3;
        siatkacentylwagaCH[20][4] = 12.3;
        siatkacentylwagaCH[20][5] = 13.2;
        siatkacentylwagaCH[20][6] = 14.0;
        //
        siatkacentylwagaCH[21][0] = 9.4;
        siatkacentylwagaCH[21][1] = 10.0;
        siatkacentylwagaCH[21][2] = 10.7;
        siatkacentylwagaCH[21][3] = 11.5;
        siatkacentylwagaCH[21][4] = 12.5;
        siatkacentylwagaCH[21][5] = 13.4;
        siatkacentylwagaCH[21][6] = 14.3;
        //
        siatkacentylwagaCH[22][0] = 9.5;
        siatkacentylwagaCH[22][1] = 10.2;
        siatkacentylwagaCH[22][2] = 10.9;
        siatkacentylwagaCH[22][3] = 11.8;
        siatkacentylwagaCH[22][4] = 12.7;
        siatkacentylwagaCH[22][5] = 13.6;
        siatkacentylwagaCH[22][6] = 14.5;
        //
        siatkacentylwagaCH[23][0] = 9.7;
        siatkacentylwagaCH[23][1] = 10.4;
        siatkacentylwagaCH[23][2] = 11.1;
        siatkacentylwagaCH[23][3] = 12.0;
        siatkacentylwagaCH[23][4] = 12.9;
        siatkacentylwagaCH[23][5] = 13.8;
        siatkacentylwagaCH[23][6] = 14.8;
        //
        siatkacentylwagaCH[24][0] = 9.8;
        siatkacentylwagaCH[24][1] = 10.8;
        siatkacentylwagaCH[24][2] = 11.3;
        siatkacentylwagaCH[24][3] = 12.3;
        siatkacentylwagaCH[24][4] = 13.1;
        siatkacentylwagaCH[24][5] = 14.1;
        siatkacentylwagaCH[24][6] = 15.1;

        ///////////////////////////////////////
        double[][] siatkacentylwagaDZ = new double[23][7];
        siatkacentylwagaDZ[0][0] = 2.4;
        siatkacentylwagaDZ[0][1] = 2.8;
        siatkacentylwagaDZ[0][2] = 2.9;
        siatkacentylwagaDZ[0][3] = 3.2;
        siatkacentylwagaDZ[0][4] = 3.6;
        siatkacentylwagaDZ[0][5] = 3.8;
        siatkacentylwagaDZ[0][6] = 4.2;
        //
        siatkacentylwagaDZ[1][0] = 3.2;
        siatkacentylwagaDZ[1][1] = 3.5;
        siatkacentylwagaDZ[1][2] = 3.8;
        siatkacentylwagaDZ[1][3] = 4.2;
        siatkacentylwagaDZ[1][4] = 4.7;
        siatkacentylwagaDZ[1][5] = 5.0;
        siatkacentylwagaDZ[1][6] = 5.5;
        //
        siatkacentylwagaDZ[2][0] = 4.0;
        siatkacentylwagaDZ[2][1] = 4.3;
        siatkacentylwagaDZ[2][2] = 4.7;
        siatkacentylwagaDZ[2][3] = 5.1;
        siatkacentylwagaDZ[2][4] = 5.6;
        siatkacentylwagaDZ[2][5] = 6.0;
        siatkacentylwagaDZ[2][6] = 6.5;
        //
        siatkacentylwagaDZ[3][0] = 4.6;
        siatkacentylwagaDZ[3][1] = 5.0;
        siatkacentylwagaDZ[3][2] = 5.4;
        siatkacentylwagaDZ[3][3] = 5.8;
        siatkacentylwagaDZ[3][4] = 6.4;
        siatkacentylwagaDZ[3][5] = 6.8;
        siatkacentylwagaDZ[3][6] = 7.5;
        //
        siatkacentylwagaDZ[4][0] = 5.0;
        siatkacentylwagaDZ[4][1] = 5.5;
        siatkacentylwagaDZ[4][2] = 5.9;
        siatkacentylwagaDZ[4][3] = 6.4;
        siatkacentylwagaDZ[4][4] = 7.0;
        siatkacentylwagaDZ[4][5] = 7.5;
        siatkacentylwagaDZ[4][6] = 8.1;
        //
        siatkacentylwagaDZ[5][0] = 5.5;
        siatkacentylwagaDZ[5][1] = 5.8;
        siatkacentylwagaDZ[5][2] = 6.4;
        siatkacentylwagaDZ[5][3] = 6.9;
        siatkacentylwagaDZ[5][4] = 7.5;
        siatkacentylwagaDZ[5][5] = 8.0;
        siatkacentylwagaDZ[5][6] = 8.7;
        //
        siatkacentylwagaDZ[6][0] = 5.8;
        siatkacentylwagaDZ[6][1] = 6.3;
        siatkacentylwagaDZ[6][2] = 6.7;
        siatkacentylwagaDZ[6][3] = 7.3;
        siatkacentylwagaDZ[6][4] = 8.0;
        siatkacentylwagaDZ[6][5] = 8.5;
        siatkacentylwagaDZ[6][6] = 9.2;
        //
        siatkacentylwagaDZ[7][0] = 6.1;
        siatkacentylwagaDZ[7][1] = 6.6;
        siatkacentylwagaDZ[7][2] = 7.0;
        siatkacentylwagaDZ[7][3] = 7.6;
        siatkacentylwagaDZ[7][4] = 8.4;
        siatkacentylwagaDZ[7][5] = 8.9;
        siatkacentylwagaDZ[7][6] = 9.6;
        //
        siatkacentylwagaDZ[8][0] = 6.4;
        siatkacentylwagaDZ[8][1] = 6.8;
        siatkacentylwagaDZ[8][2] = 7.4;
        siatkacentylwagaDZ[8][3] = 7.9;
        siatkacentylwagaDZ[8][4] = 8.6;
        siatkacentylwagaDZ[8][5] = 9.3;
        siatkacentylwagaDZ[8][6] = 10.0;
        //
        siatkacentylwagaDZ[9][0] = 6.6;
        siatkacentylwagaDZ[9][1] = 7.0;
        siatkacentylwagaDZ[9][2] = 7.5;
        siatkacentylwagaDZ[9][3] = 8.3;
        siatkacentylwagaDZ[9][4] = 8.9;
        siatkacentylwagaDZ[9][5] = 9.6;
        siatkacentylwagaDZ[9][6] = 10.4;
        //
        siatkacentylwagaDZ[10][0] = 6.8;
        siatkacentylwagaDZ[10][1] = 7.3;
        siatkacentylwagaDZ[10][2] = 7.8;
        siatkacentylwagaDZ[10][3] = 8.5;
        siatkacentylwagaDZ[10][4] = 9.2;
        siatkacentylwagaDZ[10][5] = 9.8;
        siatkacentylwagaDZ[10][6] = 10.8;
        //
        siatkacentylwagaDZ[11][0] = 7.0;
        siatkacentylwagaDZ[11][1] = 7.5;
        siatkacentylwagaDZ[11][2] = 8.0;
        siatkacentylwagaDZ[11][3] = 8.7;
        siatkacentylwagaDZ[11][4] = 9.5;
        siatkacentylwagaDZ[11][5] = 10.2;
        siatkacentylwagaDZ[11][6] = 11.0;
        //
        siatkacentylwagaDZ[12][0] = 7.2;
        siatkacentylwagaDZ[12][1] = 7.7;
        siatkacentylwagaDZ[12][2] = 8.2;
        siatkacentylwagaDZ[12][3] = 8.9;
        siatkacentylwagaDZ[12][4] = 9.7;
        siatkacentylwagaDZ[12][5] = 10.5;
        siatkacentylwagaDZ[12][6] = 11.3;
        //
        siatkacentylwagaDZ[13][0] = 7.3;
        siatkacentylwagaDZ[13][1] = 7.8;
        siatkacentylwagaDZ[13][2] = 8.4;
        siatkacentylwagaDZ[13][3] = 9.2;
        siatkacentylwagaDZ[13][4] = 9.9;
        siatkacentylwagaDZ[13][5] = 10.7;
        siatkacentylwagaDZ[13][6] = 11.6;
        //
        siatkacentylwagaDZ[14][0] = 7.5;
        siatkacentylwagaDZ[14][1] = 8.0;
        siatkacentylwagaDZ[14][2] = 8.5;
        siatkacentylwagaDZ[14][3] = 9.4;
        siatkacentylwagaDZ[14][4] = 10.2;
        siatkacentylwagaDZ[14][5] = 11.0;
        siatkacentylwagaDZ[14][6] = 11.9;
        //
        siatkacentylwagaDZ[15][0] = 7.8;
        siatkacentylwagaDZ[15][1] = 8.3;
        siatkacentylwagaDZ[15][2] = 8.8;
        siatkacentylwagaDZ[15][3] = 9.6;
        siatkacentylwagaDZ[15][4] = 10.5;
        siatkacentylwagaDZ[15][5] = 11.3;
        siatkacentylwagaDZ[15][6] = 12.2;
        //
        siatkacentylwagaDZ[16][0] = 7.8;
        siatkacentylwagaDZ[16][1] = 8.4;
        siatkacentylwagaDZ[16][2] = 9.0;
        siatkacentylwagaDZ[16][3] = 9.8;
        siatkacentylwagaDZ[16][4] = 10.7;
        siatkacentylwagaDZ[16][5] = 11.5;
        siatkacentylwagaDZ[16][6] = 12.5;
        //
        siatkacentylwagaDZ[17][0] = 8.0;
        siatkacentylwagaDZ[17][1] = 8.5;
        siatkacentylwagaDZ[17][2] = 9.3;
        siatkacentylwagaDZ[17][3] = 10.0;
        siatkacentylwagaDZ[17][4] = 10.9;
        siatkacentylwagaDZ[17][5] = 11.8;
        siatkacentylwagaDZ[17][6] = 12.7;
        //
        siatkacentylwagaDZ[18][0] = 8.2;
        siatkacentylwagaDZ[18][1] = 8.7;
        siatkacentylwagaDZ[18][2] = 9.4;
        siatkacentylwagaDZ[18][3] = 10.3;
        siatkacentylwagaDZ[18][4] = 11.2;
        siatkacentylwagaDZ[18][5] = 12.0;
        siatkacentylwagaDZ[18][6] = 13.0;
        //
        siatkacentylwagaDZ[19][0] = 8.4;
        siatkacentylwagaDZ[19][1] = 9.0;
        siatkacentylwagaDZ[19][2] = 9.6;
        siatkacentylwagaDZ[19][3] = 10.5;
        siatkacentylwagaDZ[19][4] = 11.4;
        siatkacentylwagaDZ[19][5] = 12.3;
        siatkacentylwagaDZ[19][6] = 13.3;
        //
        siatkacentylwagaDZ[20][0] = 8.5;
        siatkacentylwagaDZ[20][1] = 9.2;
        siatkacentylwagaDZ[20][2] = 9.8;
        siatkacentylwagaDZ[20][3] = 10.6;
        siatkacentylwagaDZ[20][4] = 11.6;
        siatkacentylwagaDZ[20][5] = 12.6;
        siatkacentylwagaDZ[20][6] = 13.6;
        //
        siatkacentylwagaDZ[21][0] = 8.7;
        siatkacentylwagaDZ[21][1] = 9.3;
        siatkacentylwagaDZ[21][2] = 10.0;
        siatkacentylwagaDZ[21][3] = 10.8;
        siatkacentylwagaDZ[21][4] = 11.8;
        siatkacentylwagaDZ[21][5] = 12.8;
        siatkacentylwagaDZ[21][6] = 13.8;
        //
        siatkacentylwagaDZ[22][0] = 8.9;
        siatkacentylwagaDZ[22][1] = 9.5;
        siatkacentylwagaDZ[22][2] = 10.2;
        siatkacentylwagaDZ[22][3] = 11.0;
        siatkacentylwagaDZ[22][4] = 12.0;
        siatkacentylwagaDZ[22][5] = 13.0;
        siatkacentylwagaDZ[22][6] = 14.0;
        //
        siatkacentylwagaDZ[23][0] = 9.0;
        siatkacentylwagaDZ[23][1] = 9.6;
        siatkacentylwagaDZ[23][2] = 10.4;
        siatkacentylwagaDZ[23][3] = 11.4;
        siatkacentylwagaDZ[23][4] = 12.3;
        siatkacentylwagaDZ[23][5] = 13.3;
        siatkacentylwagaDZ[23][6] = 14.4;
        //
        siatkacentylwagaDZ[24][0] = 9.2;
        siatkacentylwagaDZ[24][1] = 9.8;
        siatkacentylwagaDZ[24][2] = 10.7;
        siatkacentylwagaDZ[24][3] = 11.6;
        siatkacentylwagaDZ[24][4] = 12.6;
        siatkacentylwagaDZ[24][5] = 13.6;
        siatkacentylwagaDZ[24][6] = 14.6;

/*        if(plecnumber==0)
        {
            for(int i=0; i<siatkacentylwzrostCH[wiek].length;i++)
            {
                siatkacentylwzrostCH[wiek][];
            }
        }else
            for(int i=0; i<siatkacentylwzrostCH[wiek].length;i++)
            {
                siatkacentylwzrostDZ[wiek][];
            }*/
        return 2;
    }
}
