/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;

/**
 *
 * @author Nikola
 */
public class Operacija implements Serializable {

    public static final int LOGIN = 1;
    public static final int VRATI_TIMOVE = 2;
    public static final int VRATI_KLIJENTE = 3;
    public static final int VRATI_HAKATONE = 4;
    public static final int VRATI_TIMOVE_ZA_HAKATON = 5;
    public static final int VRATI_CLANOVE_ZA_TIM = 6;
    public static final int SACUVAJ_OCENE = 7;
    public static final int VRATI_OCENU_ZA_SUDIJU_I_TIM = 8;
    public static final int KREIRAJ_SUDIJU = 9;
    public static final int KREIRAJ_HAKATON = 10;
    public static final int KREIRAJ_MENTORA = 11;
    public static final int VRATI_SUDIJE = 12;
    public static final int VRATI_HAKATONE_ZA_PRETRAGU = 13;
    public static final int VRATI_SUDIJE_ZA_PRETRAGU = 14;
    public static final int VRATI_MENTORE_ZA_PRETRAGU = 15;
    public static final int VRATI_TIMOVE_ZA_PRETRAGU = 16;
    public static final int VRATI_MENTORE = 17;
    public static final int UBACI_SUDIJE_U_HAKATON = 18;
    public static final int VRATI_HAKATONE_ZA_SUDIJU = 19;
    public static final int VRATI_VEZE_SUDIJE_HAKATONA = 20;
    public static final int OBRISI_VEZE_SUDIJE_HAKATONA = 21;
    public static final int OBRISI_SUDIJU = 22;
    public static final int IZMENI_SUDIJU = 23;
    public static final int VRATI_CLANOVE = 24;
    public static final int OBRISI_CLANA = 25;
    public static final int KREIRAJ_TIM = 26;
    public static final int KREIRAJ_CLANA = 27;
    public static final int VRATI_POSLEDNJI_TIM = 28;
    public static final int VRATI_POSLEDNJI_HAKATON = 29;
    public static final int VRATI_TIMOVE_ZA_MENTORA = 30;
    public static final int OBRISI_MENTORA = 31;
    public static final int OBRISI_TIM = 32;
    public static final int VRATI_CLANOVE_ZA_PRETRAGU = 33;
    public static final int VRATI_SUDIJA_ZA_USERNAME = 34;
    public static final int VRATI_VEZE_SVE = 35;
    public static final int VRATI_SUDIJE_ZA_HAKATON = 36;

    public static final int GASENJE = 55;

}
