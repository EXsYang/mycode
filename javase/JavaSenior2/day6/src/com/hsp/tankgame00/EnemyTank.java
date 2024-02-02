package com.hsp.tankgame00;

import java.util.Vector;

/**
 * @author yangda
 * @description:
 * @create 2022-11-21-12:19
 */
public class EnemyTank extends Tank {

    Vector<Shot> shots = new Vector<>();

    public EnemyTank(int x, int y) {
        super(x, y);


    }


}