/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author Jelena
 */
public interface Operation {

    public static final int LOGIN = 0;
    public static final int LOGOUT = 1;

    public static final int ADD_KUPAC = 2;
    public static final int DELETE_KUPAC = 3;
    public static final int UPDATE_KUPAC = 4;
    public static final int GET_ALL_KUPAC = 5;

    public static final int ADD_COKOLADA = 6;
    public static final int DELETE_COKOLADA = 7;
    public static final int UPDATE_COKOLADA = 8;
    public static final int GET_ALL_COKOLADA = 9;

    public static final int ADD_PORUDZBINA = 10;
    public static final int DELETE_PORUDZBINA = 11;
    public static final int UPDATE_PORUDZBINA = 12;
    public static final int GET_ALL_PORUDZBINA = 13;

    public static final int GET_ALL_VRSTA_COKOLADE = 14;

    public static final int GET_ALL_GRAD = 15;

}
