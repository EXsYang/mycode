package com.hsp.tankgame06;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

/**
 * @author yangda
 * @description: 用于记录相关信息，和文件交互
 * @create 2022-12-01-11:44
 */
public class Recorder {
    //定义变量，记录我方击毁敌人坦克数
    private static int allEnemyTankNum = 0;

    //定义IO对象，准备写数据到文件中
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;

    private static FileReader fr = null;
    private static BufferedReader br = null;
    private static Vector<Node> nodes =null;


    private static String recordFile = "e:/myRecord.txt";
    private static Vector<EnemyTank> enemyTanks =null;

    //增加一个方法，当游戏退出时，将allEnemyTankNum 保存到 recordFile
    public static void  keepRecord(){
        try {
            fw = new FileWriter(recordFile);
            bw = new BufferedWriter(fw);
            bw.write(allEnemyTankNum+ "\r\n");//写入int类型会有乱码，这里相当于转换类型
//            bw.write(allEnemyTankNum);//写入int类型会有乱码
//            bw.newLine();
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive){
                    bw.write(enemyTank.getX()+" " + enemyTank.getY()+ " " + enemyTank.getDirect() + "\n");
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }

    public static Vector<Node> getNodes() {
        return nodes;
    }

    public static void readFirstRoundGame(){//读取上局游戏的方法，得到击杀数和存活坦克坐标方向
        nodes = new Vector<>();//放在这，只读取一次
        String readLen = "";
        try {
            fr = new FileReader(recordFile);
            br = new BufferedReader(fr);
            while ((readLen = br.readLine()) != null){//拿到上局游戏保存的信息

                String[] r = readLen.split(" ");
                if(r.length == 1){//直接读一次也行，第二次读取直接从第二行开始
                    allEnemyTankNum = Integer.parseInt(r[0]);
                }

                if (r.length > 1){
//          nodes = new Vector<>();//相当于每次都新建一个集合，最后只读取到最后新建的nodes,导致只有一个敌方坦克
                    nodes.add(new Node(Integer.parseInt(r[0]),Integer.parseInt(r[1]),Integer.parseInt(r[2])));
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    //当我方坦克击毁一个敌人坦克，就应当 allEnemyTankNum++
    public static void addAllEnemyTankNum(){
        Recorder.allEnemyTankNum++;
    }
}
