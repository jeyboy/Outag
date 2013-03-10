package jtag.reference;

import java.util.HashMap;
import java.util.Map;

public class Manufacturers {
    private static Map<Integer, Manufacturer> codeMap;
    private static Map<String, Manufacturer> nameMap;

    static {
        codeMap = new HashMap<Integer, Manufacturer>();
        for (Manufacturer script : Manufacturer.values())
            codeMap.put(script.code, script);

        nameMap = new HashMap<String, Manufacturer>();
        for (Manufacturer script : Manufacturer.values())
        	nameMap.put(script.name, script);
    }

    /** @param code
     * @return enum with this two letter code */
    public static Manufacturer getManufacturerByCode(int code) { return codeMap.get(code); }

    /** @param description
     * @return enum with this description */
    public static Manufacturer getCodeByName(String name) {
        return nameMap.get(name);
    }

    public static enum Manufacturer {
    	UNKNOW(0, "Unknown"),
    	SEQ_CIRCUITS(1, "Sequential Circuits"),
    	BIG_BRIAR(2, "Big Briar"),
    	OCTAVE(3, "Octave / Plateau"),
    	MOOG(4, "Moog"),
    	PASSPORT_DESIGNS(5, "Passport Designs"),
    	LIXICON(6, "Lexicon"),
    	KURZWEIL(7, "Kurzweil"),
    	FENDER(8, "Fender"),
    	GULBRANSEN(9, "Gulbransen"),
    	DELTA_LABS(10, "Delta Labs"),
    	SOUND_COMP(11, "Sound Comp."),
    	GENERAL_ELECTRO(12, "General Electro"),
    	TECHMAR(13, "Techmar"),
    	MATTHEWS_RESEARCH(14, "Matthews Research"),
    	OBERHEIM(16, "Oberheim"),
    	PAIA(17, "PAIA"),
    	SIMMONS(18, "Simmons"),
    	DIGIDESIGN(19, "DigiDesign"),
    	FAIRLIGHT(20, "Fairlight"),
    	JL_COOPER(21, "JL Cooper"),
    	LOWERY(22, "Lowery"),
    	LIN(23, "Lin"),
    	EMU(24, "Emu"),
    	PEAVEY(27, "Peavey"),
    	BON_TEMPI(32, "Bon Tempi"),
    	SIEL(33, "S.I.E.L."),
    	SYNTHEAXE(35, "SyntheAxe"),
    	HOHNER(36, "Hohner"),
    	CRUMAR(37, "Crumar"),
    	SOLTON(38, "Solton"),
    	JELLINGHAUS(39, "Jellinghaus Ms"),
    	CTS(40, "CTS"),
    	PPG(41, "PPG"),
    	ELKA(47, "Elka"),
    	CHEETAH(54, "Cheetah"),
    	WALDORF(62, "Waldorf"),
    	KAWAI(64, "Kawai"),
    	ROLAND(65, "Roland"),
    	KORA(66, "Korg"),
    	YAMAHA(67, "Yamaha"),
    	CASIO(68, "Casio"),
    	KAMIYA_STUDIO(70, "Kamiya Studio"),
    	AKAI(71, "Akai"),
    	VICTOR(72, "Victor"),
    	FUJITSU(75, "Fujitsu"),
    	SONY(76, "Sony"),
    	TEAC(78, "Teac"),
    	MATSUSHITA(80, "Matsushita"),
    	FOSTEX(81, "Fostex"),
    	ZOOM(82, "Zoom"),
    	MATSUSHITA2(84, "Matsushita"),
    	SUZUKI(85, "Suzuki"),
    	FUJI_SOUND(86, "Fuji Sound"),
    	ACOUSTIC_TECHNICAL_LABORATORY(87, "Acoustic Technical Laboratory");    	

        private int code;
        private String name;

        Manufacturer(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() { return code; }

        public String getName() { return name; }

        public String toString() { return getName(); }
    }    	
}